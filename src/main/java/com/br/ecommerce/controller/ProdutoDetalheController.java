package com.br.ecommerce.controller;

import com.br.ecommerce.model.Produto;
import com.br.ecommerce.responses.ProdutoDetalheResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/api")
public class ProdutoDetalheController {

    @PersistenceContext
    EntityManager manager;

    @GetMapping(value = "produtos/{id}")
    @Transactional
    public ResponseEntity<?> buscar (@PathVariable("id") Long id) {

        Produto produto =  manager.find(Produto.class, id );

        if(produto == null) {
            return ResponseEntity.notFound().build();
        }

        ProdutoDetalheResponse response = new ProdutoDetalheResponse(produto);

        return ResponseEntity.ok(response);
    }

}
