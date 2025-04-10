package com.albert.paynow.Integration.repository;

import com.albert.paynow.Integration.model.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentItemRepository extends JpaRepository<PaymentItem, Long> {
}
