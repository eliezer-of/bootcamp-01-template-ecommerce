package com.br.ecommerce.config;

import com.br.ecommerce.service.email.EmailService;
import com.br.ecommerce.service.email.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
