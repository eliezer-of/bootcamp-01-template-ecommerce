package com.br.ecommerce.controller;

import com.br.ecommerce.model.Compra;
import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import com.br.ecommerce.repository.UsuarioRepository;
import com.br.ecommerce.requests.CompraRequest;
import com.br.ecommerce.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CompraController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "/compras")
    @Transactional
    public String inserir (@Valid @RequestBody CompraRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {

        Optional<Usuario> usuarioComprador = usuarioRepository.findByEmail(UserService.authenticated().getUsername());

        Produto produto = manager.find(Produto.class, request.getIdProduto());
        int quantidade = request.getQuantidade();

        boolean estoqueFoiAbatido = produto.abaterEstoque(request.getQuantidade());

        if (estoqueFoiAbatido) {
            Compra compra = new Compra(produto, quantidade, usuarioComprador);
            manager.persist(compra);
            return compra.toString();
        }

        return "To do";

    }

}
