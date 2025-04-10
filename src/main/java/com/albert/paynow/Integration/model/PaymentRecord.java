package com.albert.paynow.Integration.model;

import jakarta.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_records")
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private String email;
    private String cartDescription;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    private String status;
    private String pollUrl;
    private String redirectUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentRecord")
    private List<PaymentItem> items = new ArrayList<PaymentItem>();

    // Constructors
    public PaymentRecord() {
        this.createdAt = new Date();
    }

    public PaymentRecord(String invoiceNumber, String email, String cartDescription,
                         BigDecimal amount, String status, String pollUrl,
                         String redirectUrl) {
        this();
        this.invoiceNumber = invoiceNumber;
        this.email = email;
        this.cartDescription = cartDescription;
        this.amount = amount;
        this.status = status;
        this.pollUrl = pollUrl;
        this.redirectUrl = redirectUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCartDescription() {
        return cartDescription;
    }

    public void setCartDescription(String cartDescription) {
        this.cartDescription = cartDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPollUrl() {
        return pollUrl;
    }

    public void setPollUrl(String pollUrl) {
        this.pollUrl = pollUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<PaymentItem> getItems() {
        return items;
    }

    public void setItems(List<PaymentItem> items) {
        this.items = items;
    }

    // Helper method
    public void addItem(PaymentItem item) {
        items.add(item);
        item.setPaymentRecord(this);
    }
}
