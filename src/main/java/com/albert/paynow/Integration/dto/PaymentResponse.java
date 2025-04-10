package com.albert.paynow.Integration.dto;

public class PaymentResponse {
    private String redirectUrl;
    private String pollUrl;
    private String message;

    public PaymentResponse() {
    }

    public PaymentResponse(String redirectUrl, String pollUrl, String message) {
        this.redirectUrl = redirectUrl;
        this.pollUrl = pollUrl;
        this.message = message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getPollUrl() {
        return pollUrl;
    }

    public void setPollUrl(String pollUrl) {
        this.pollUrl = pollUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}