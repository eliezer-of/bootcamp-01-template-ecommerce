package com.br.ecommerce.controller;

import com.br.ecommerce.model.Categoria;
import com.br.ecommerce.requests.CategoriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    @Autowired
    EntityManager manager;

    @PostMapping(value = "")
    @Transactional
    public ResponseEntity<?> inserir (@Valid @RequestBody CategoriaRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {

        Categoria categoria = request.toModel();
        manager.persist(categoria);

        return  ResponseEntity.created(uriComponentsBuilder.path("api/categoria/{id}").
                buildAndExpand(categoria.getId()).toUri()).build();
    }
}
