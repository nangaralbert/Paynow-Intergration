package com.albert.paynow.Integration.dto;
import java.math.BigDecimal;

public class PaymentStatus {
    private boolean paid;
    private String status;
    private double amount; // Can remain as double since we're converting

    public PaymentStatus() {
    }

    // Option 1: Keep double and convert from BigDecimal
    public PaymentStatus(boolean paid, String status, double amount) {
        this.paid = paid;
        this.status = status;
        this.amount = amount;
    }

    // Option 2: Or change to use BigDecimal directly (recommended for monetary values)
    public PaymentStatus(boolean paid, String status, BigDecimal amount) {
        this.paid = paid;
        this.status = status;
        this.amount = amount.doubleValue();
    }

    // Getters and setters
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Additional setter for BigDecimal
    public void setAmount(BigDecimal amount) {
        this.amount = amount.doubleValue();
    }
}