package com.br.ecommerce.service.email;

import com.br.ecommerce.model.Compra;
import com.br.ecommerce.model.Pergunta;
import org.springframework.mail.SimpleMailMessage;

import java.net.URI;

public interface EmailService {

    void enviarEmail(SimpleMailMessage mensagem);

    void enviarEmailNovaPergunta(Pergunta pergunta, URI link);

    void enviarEmailCompraRealizada(Compra compra);
}
