package com.albert.paynow.Integration.dto;


import java.util.List;

public class PaymentRequest {
    private String invoiceNumber;
    private String email;
    private String cartDescription;
    private List<PaymentItemDto> items;

    // Constructors
    public PaymentRequest() {
    }

    public PaymentRequest(String invoiceNumber, String email, String cartDescription, List<PaymentItemDto> items) {
        this.invoiceNumber = invoiceNumber;
        this.email = email;
        this.cartDescription = cartDescription;
        this.items = items;
    }

    // Getters and Setters
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

    public List<PaymentItemDto> getItems() {
        return items;
    }

    public void setItems(List<PaymentItemDto> items) {
        this.items = items;
    }

    public static class PaymentItemDto {
        private String name;
        private double price;

        // Constructors
        public PaymentItemDto() {
        }

        public PaymentItemDto(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}