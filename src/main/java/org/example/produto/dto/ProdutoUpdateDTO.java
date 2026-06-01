package org.example.produto.dto;

import org.example.produto.Produto;

import java.util.Objects;

public class ProdutoUpdateDTO {
    private Long id;
    private String nome;
    private Double preco;

    public ProdutoUpdateDTO() {}

    public ProdutoUpdateDTO(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoUpdateDTO that = (ProdutoUpdateDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
