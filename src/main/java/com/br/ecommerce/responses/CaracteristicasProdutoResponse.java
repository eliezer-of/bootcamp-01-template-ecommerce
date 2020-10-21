package com.br.ecommerce.responses;

import com.br.ecommerce.model.CaracteristicasProduto;

import java.util.HashSet;
import java.util.Set;

public class CaracteristicasProdutoResponse {

    Set<CaracteristicasProduto> caracteristicas = new HashSet<>();

    public CaracteristicasProdutoResponse(Set<CaracteristicasProduto> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }
}
