package com.br.ecommerce.enums;

import com.br.ecommerce.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public enum GatewayPagamento {

    pagseguro{
        @Override
        public ResponseEntity<?> criarUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {

            UriComponents url = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId());
            URI link = URI.create("pagseguro.com/" + compra.getId() + "?redirectUrl=" + url);

            return ResponseEntity.created(link).build();
        }
    },
    paypal{
        @Override
        public ResponseEntity<?> criarUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {

            UriComponents url = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId());
            URI link = URI.create("paypal.com/" + compra.getId() + "?redirectUrl=" + url);

            return ResponseEntity.created(link).build();
        }
    };

    public abstract ResponseEntity<?> criarUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
