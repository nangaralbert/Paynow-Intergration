package com.albert.paynow.Integration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentWebController {

    @GetMapping("/payment")
    public String paymentPage() {
        return "payment.html";
    }
}