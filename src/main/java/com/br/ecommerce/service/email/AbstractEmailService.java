package com.br.ecommerce.service.email;

import com.br.ecommerce.model.Pergunta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.net.URI;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void enviarEmailNovaPergunta(Pergunta pergunta, URI link) {
        SimpleMailMessage sm = preparNovaPerguntaEmail(pergunta, link);
        enviarEmail(sm);
    }

    protected SimpleMailMessage preparNovaPerguntaEmail(Pergunta pergunta,URI link) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getDonoProduto().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Nova pergunta para o produto id: "+ pergunta.getProduto().getId() + " - " + pergunta.getProduto().getNome());
        sm.setText(pergunta.getTitulo() + " - Visualizar o produto: " + link);
        return sm;
    }
}
