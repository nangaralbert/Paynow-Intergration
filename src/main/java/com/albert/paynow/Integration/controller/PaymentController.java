package com.albert.paynow.Integration.controller;

import com.albert.paynow.Integration.dto.PaymentRequest;
import com.albert.paynow.Integration.dto.PaymentResponse;
import com.albert.paynow.Integration.dto.PaymentStatus;
import com.albert.paynow.Integration.model.PaymentRecord;
import com.albert.paynow.Integration.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:63342")
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public PaymentResponse initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.initiatePayment(paymentRequest);
    }

    @GetMapping("/status")
    public ResponseEntity<PaymentStatus> checkPaymentStatus(
            @RequestParam("pollUrl") String pollUrl) {
        try {
            PaymentStatus status = paymentService.checkPaymentStatus(pollUrl);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PaymentStatus(false, "Error: " + e.getMessage(), 0));
        }
    }

}