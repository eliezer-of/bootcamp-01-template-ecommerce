package com.br.ecommerce.service.email;

import com.br.ecommerce.model.Pergunta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void enviarEmailNovaPergunta(Pergunta pergunta) {
        SimpleMailMessage sm = preparNovaPerguntaEmail(pergunta);
        enviarEmail(sm);
    }

    protected SimpleMailMessage preparNovaPerguntaEmail(Pergunta pergunta) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getDonoProduto().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Nova pergunta para o produto id: "+ pergunta.getProduto().getId() + " - " + pergunta.getProduto().getNome());
        sm.setText(pergunta.getTitulo());
        return sm;
    }
}
