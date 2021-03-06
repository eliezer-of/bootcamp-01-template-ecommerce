package com.br.ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public Double media() {
        Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());

        double media = notas.stream().mapToInt(nota -> nota).average().orElse(0.0);
        double mediaTratada = new BigDecimal(media).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        return mediaTratada;
    }

    public int total() {
        return this.opinioes.size();
    }
}
