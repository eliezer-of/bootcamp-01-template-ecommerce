package com.br.ecommerce.controller;

import com.br.ecommerce.model.Opiniao;
import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import com.br.ecommerce.repository.UsuarioRepository;
import com.br.ecommerce.requests.OpiniaoRequest;
import com.br.ecommerce.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ProdutoOpiniaoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "produto/{id}/opiniao")
    @Transactional
    public ResponseEntity<?> adicionarOpiniao (@PathVariable("id") Long id, @Valid @RequestBody OpiniaoRequest request,
                                  UriComponentsBuilder uriComponentsBuilder) {

        Optional<Usuario> usuarioOptnante = usuarioRepository.findByEmail(UserService.authenticated().getUsername());
        Produto produto = manager.find(Produto.class, id);

        Opiniao opiniao = request.toModel(produto, usuarioOptnante);
        manager.persist(opiniao);

        return ResponseEntity.created(uriComponentsBuilder.path("/api/produto/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }

}
