package org.example.produto;

import org.example.produto.dto.ProdutoCreateDTO;
import org.example.produto.dto.ProdutoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public ProdutoResponseDTO toDTO(Produto produto){
        return new ProdutoResponseDTO(produto.getId(),produto.getNome(),produto.getPreco());
    }

    public Produto toEntity(ProdutoCreateDTO produtoDTO){
        return new Produto(produtoDTO.getNome(),produtoDTO.getPreco());
    }
}
