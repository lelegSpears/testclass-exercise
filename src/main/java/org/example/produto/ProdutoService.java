package org.example.produto;

import org.example.exception.ResourceNotFoundException;
import org.example.produto.dto.ProdutoCreateDTO;
import org.example.produto.dto.ProdutoResponseDTO;
import org.example.produto.dto.ProdutoUpdateDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoResponseDTO findById(Long id){
        Produto dto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return produtoMapper.toDTO(dto);
    }

    public ProdutoResponseDTO create(ProdutoCreateDTO createDTO){
        Produto produto = produtoMapper.toEntity(createDTO);
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toDTO(produtoSalvo);
    }

    public void delete(Long id){
        try {
            produtoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public ProdutoResponseDTO update(ProdutoUpdateDTO updateDTO, Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(produto, updateDTO);
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toDTO(produtoSalvo);
    }

    public void updateData(Produto oldData, ProdutoUpdateDTO newData){
        oldData.setNome(newData.getNome());
        oldData.setPreco(newData.getPreco());
    }
}
