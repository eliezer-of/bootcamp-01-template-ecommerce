package com.br.ecommerce.service.email;

import com.br.ecommerce.model.Pergunta;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviarEmail(SimpleMailMessage mensagem);

    void enviarEmailNovaPergunta(Pergunta pergunta);
}
