package com.albert.paynow.Integration.repository;

import com.albert.paynow.Integration.model.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {
    Optional<PaymentRecord> findByInvoiceNumber(String invoiceNumber);
    Optional<PaymentRecord> findByPollUrl(String pollUrl);
}
