package com.albert.paynow.Integration.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payment_items")
public class PaymentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "payment_record_id")
    private PaymentRecord paymentRecord;

    // Constructors
    public PaymentItem() {
    }

    public PaymentItem(String name, BigDecimal price, PaymentRecord paymentRecord) {
        this.name = name;
        this.price = price;
        this.paymentRecord = paymentRecord;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PaymentRecord getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(PaymentRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    @Override
    public String toString() {
        return "PaymentItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
