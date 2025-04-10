package com.albert.paynow.Integration.config;

import zw.co.paynow.core.Paynow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaynowConfig {

    @Value("${paynow.integration.id}")
    private String integrationId;

    @Value("${paynow.return.url}")
    private String returnUrl;

    @Value("${paynow.integration.key}")
    private String integrationKey;

    @Bean
    public Paynow paynow() {
        Paynow paynow = new Paynow(integrationId, integrationKey);
        paynow.setReturnUrl(returnUrl);
        return paynow;
    }


}