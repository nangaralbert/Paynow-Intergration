package com.albert.paynow.Integration.service;



import com.albert.paynow.Integration.dto.PaymentRequest;
import com.albert.paynow.Integration.dto.PaymentResponse;
import com.albert.paynow.Integration.dto.PaymentStatus;
import com.albert.paynow.Integration.model.PaymentItem;
import com.albert.paynow.Integration.model.PaymentRecord;
import com.albert.paynow.Integration.repository.PaymentRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;

import java.math.BigDecimal;

@Service
@Transactional
public class PaymentService {
    private final Paynow paynow;
    private final PaymentRecordRepository paymentRecordRepository;

    public PaymentService(Paynow paynow, PaymentRecordRepository paymentRecordRepository) {
        this.paynow = paynow;
        this.paymentRecordRepository = paymentRecordRepository;
    }

    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) {
        // Create and save payment record
        PaymentRecord record = new PaymentRecord();
        record.setInvoiceNumber(paymentRequest.getInvoiceNumber());
        record.setEmail(paymentRequest.getEmail());
        record.setCartDescription(paymentRequest.getCartDescription());

        // Calculate total amount
        BigDecimal totalAmount = paymentRequest.getItems().stream()
                .map(item -> BigDecimal.valueOf(item.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        record.setAmount(totalAmount);

        // Create Paynow payment
        Payment payment = paynow.createPayment(
                paymentRequest.getInvoiceNumber(),
                paymentRequest.getEmail() != null ? paymentRequest.getEmail() : ""
        );

        // Add items
        paymentRequest.getItems().forEach(item -> {
            payment.add(item.getName(), item.getPrice());

            // Save payment items
            PaymentItem paymentItem = new PaymentItem();
            paymentItem.setName(item.getName());
            paymentItem.setPrice(BigDecimal.valueOf(item.getPrice()));
            paymentItem.setPaymentRecord(record);
            record.getItems().add(paymentItem);
        });

        // Initiate payment
        WebInitResponse response = paynow.send(payment);

        if (response.success()) {
            // Update record with Paynow details
            record.setStatus("INITIATED");
            record.setPollUrl(response.pollUrl());
            record.setRedirectUrl(response.redirectURL());

            // Save to database
            paymentRecordRepository.save(record);

            return new PaymentResponse(
                    response.redirectURL(),
                    response.pollUrl(),
                    "Payment initiated successfully"
            );
        } else {
            record.setStatus("FAILED");
            paymentRecordRepository.save(record);
            throw new RuntimeException("Failed to initiate payment");
        }
    }

    @Transactional
    public PaymentStatus checkPaymentStatus(String pollUrl) {
        StatusResponse status = paynow.pollTransaction(pollUrl);

        // Update payment record
        paymentRecordRepository.findByPollUrl(pollUrl).ifPresent(record -> {
            record.setStatus(status.paid() ? "PAID" : "FAILED");
            if (status.paid()) {
                record.setAmount(BigDecimal.valueOf(status.getAmount().doubleValue()));
            }
            paymentRecordRepository.save(record);
        });

        return new PaymentStatus(
                status.paid(),
                status.getStatus().name(),
                status.getAmount().doubleValue()
        );
    }
}