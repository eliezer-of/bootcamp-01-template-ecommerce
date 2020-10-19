package com.br.ecommerce.controller;

import com.br.ecommerce.model.Produto;
import com.br.ecommerce.model.Usuario;
import com.br.ecommerce.repository.UsuarioRepository;
import com.br.ecommerce.requests.ImagensRequest;
import com.br.ecommerce.requests.ProdutoRequest;
import com.br.ecommerce.security.UserService;
import com.br.ecommerce.utils.Uploader;
import com.br.ecommerce.utils.UploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api")
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    Uploader uploaderFake;

    @PostMapping(value = "/produto")
    @Transactional
    public ResponseEntity<?> inserir (@Valid @RequestBody ProdutoRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(UserService.authenticated().getUsername());

        Produto produto = request.toModel(manager, usuario);
        manager.persist(produto);

        return  ResponseEntity.created(uriComponentsBuilder.path("/api/produto/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }

    @PostMapping(value = "produto/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens (@PathVariable("id") Long id, @Valid ImagensRequest request,
                                               UriComponentsBuilder uriComponentsBuilder) {

        Set<String> linksImagens = uploaderFake.enviar(request.getImagens());
        Produto produto = manager.find(Produto.class, id);
        produto.associarImagens(linksImagens);

        manager.merge(produto);

        return  ResponseEntity.created(uriComponentsBuilder.path("/api/produto/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }

}
