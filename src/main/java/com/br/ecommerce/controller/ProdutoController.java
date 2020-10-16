package com.br.ecommerce.controller;

import com.br.ecommerce.model.Produto;
import com.br.ecommerce.requests.ProdutoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping(value = "/produto")
    @Transactional
    public ResponseEntity<?> inserir (@Valid @RequestBody ProdutoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Produto produto = request.toModel(manager);
        manager.persist(produto);

        return  ResponseEntity.created(uriComponentsBuilder.path("/api/produto/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }

}
