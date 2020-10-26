package com.br.ecommerce.model;

import com.br.ecommerce.requests.CaracteristicasProdutoRequest;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicasProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagemProdutos = new HashSet<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario donoDoProduto;

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @Deprecated
    public Produto() {

    }

    public Produto(@NotBlank String nome,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive Integer quantidade,
                   @Valid @Size(min = 3) Collection<CaracteristicasProdutoRequest> caracteristicas,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria,@NotNull @Valid Optional<Usuario> usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.donoDoProduto = usuario.get();

        this.caracteristicas.addAll(caracteristicas.stream().map(
                caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));

        Assert.isTrue(this.caracteristicas.size() >= 3, "É necessário ter no mínimo 3 caracteristicas");
    }

    public Long getId() {
        return id;
    }

    public Usuario getDonoDoProduto() {
        return donoDoProduto;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public SortedSet<Pergunta> getPerguntas() {
        return perguntas;
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }

    public Set<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagemProdutos() {
        return imagemProdutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", imagemProdutos=" + imagemProdutos +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", donoDoProduto=" + donoDoProduto +
                '}';
    }

    public void associarImagens(Set<String> linksImagens) {

       Set<ImagemProduto> imagens =  linksImagens.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

       this.imagemProdutos.addAll(imagens);
    }

    public boolean pertenceAoUsuario(@NotNull Optional<Usuario> usuario) {
        Assert.isTrue(usuario.isPresent(), "Usuário não pode ser nulo!");
        return this.donoDoProduto.equals(usuario.get());
    }



    public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicasProduto,T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto,T> funcaoMapeadora) {
        return this.imagemProdutos.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora)
                .collect(Collectors.toCollection(TreeSet :: new));
    }

    public boolean abaterEstoque(@Positive  int quantidade) {
        Assert.isTrue(quantidade > 0 , "Qauntidade deve ser positiva para alterar o estoque!");

        if (quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
            return true;
        }

        return false;
    }
}
