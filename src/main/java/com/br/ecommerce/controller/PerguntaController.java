package com.br.ecommerce.controller;

import com.br.ecommerce.model.Pergunta;
import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import com.br.ecommerce.repository.UsuarioRepository;
import com.br.ecommerce.requests.PerguntaRequest;
import com.br.ecommerce.security.UserService;
import com.br.ecommerce.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class PerguntaController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmailService emailService;

    @PostMapping(value = "produtos/{id}/pergunta")
    @Transactional
    public ResponseEntity<?> adicionarPergunta (@PathVariable("id") Long id,
                                                @Valid @RequestBody PerguntaRequest request,
                                                UriComponentsBuilder uriComponentsBuilder) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(UserService.authenticated().getUsername());
        Produto produto = manager.find(Produto.class, id);

        Pergunta pergunta = request.toModel(produto, usuario);
        manager.persist(pergunta);

        URI link = uriComponentsBuilder.path("/api/produto/{id}").buildAndExpand(produto.getId()).toUri();

        emailService.enviarEmailNovaPergunta(pergunta , link);

        return ResponseEntity.created(link).build();
    }

}
