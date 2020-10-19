package com.br.ecommerce.controller;

import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import com.br.ecommerce.repository.UsuarioRepository;
import com.br.ecommerce.requests.ImagensRequest;
import com.br.ecommerce.requests.ProdutoRequest;
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
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "/produto")
    @Transactional
    public ResponseEntity<?> inserir (@Valid @RequestBody ProdutoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(UserService.authenticated().getUsername());

        Produto produto = request.toModel(manager, usuario);
        manager.persist(produto);

        return  ResponseEntity.created(uriComponentsBuilder.path("/api/produto/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }

    @PostMapping(value = "produto/{id}/imagens")
    public void adicionarImagens (@PathVariable("id") Long id, @Valid ImagensRequest request) {

    }

}
