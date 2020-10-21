package com.br.ecommerce.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void enviarEmail(SimpleMailMessage mensagem) {
        LOG.info("Simulando envio de email...");
        LOG.info(mensagem.toString());
        LOG.info("Email enviado");
    }

}
