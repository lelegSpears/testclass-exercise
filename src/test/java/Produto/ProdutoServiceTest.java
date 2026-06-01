package Produto;

import org.example.exception.ResourceNotFoundException;
import org.example.produto.Produto;
import org.example.produto.ProdutoMapper;
import org.example.produto.ProdutoRepository;
import org.example.produto.ProdutoService;
import org.example.produto.dto.ProdutoCreateDTO;
import org.example.produto.dto.ProdutoResponseDTO;
import org.example.produto.dto.ProdutoUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)  // Futuramente adicionar ArgumentCaptor para testes
public class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;

    private ProdutoResponseDTO produtoResponseDTO;

    private ProdutoCreateDTO produtoCreateDTO;

    private Produto produtoSalvo;

    private ProdutoUpdateDTO produtoUpdateDTO;

    @BeforeEach
    public void setUp() {
        produto = new Produto(1L, "PC", 6000.0);

        produtoResponseDTO = new ProdutoResponseDTO(1L, "PC", 6000.0);

        produtoCreateDTO = new ProdutoCreateDTO("PC", 6000.0);

        produtoSalvo = new Produto(1L, "PC", 6000.0);

        produtoUpdateDTO = new ProdutoUpdateDTO("Placa", 300.0);
    }

    @DisplayName("Deve Retornar ProdutoResponseDTO por ID")
    @Test
    void shouldReturnProdutoResponseDTOById() {

        given(produtoRepository.findById(1L))
                .willReturn(Optional.of(produto));

        given(produtoMapper.toDTO(produto))
                .willReturn(produtoResponseDTO);

        ProdutoResponseDTO result = produtoService.findById(1L);

        assertEquals(6000.0, result.getPreco(), 0.01);
        assertEquals("PC", result.getNome());

        verify(produtoRepository).findById(1L);
        verify(produtoMapper).toDTO(produto);
    }

    @DisplayName("Deve retornar exceção ao não encontrar usuário")
    @Test
    void shouldThrowResourceNotFoundException() {
        given(produtoRepository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> produtoService.findById(2L));

        verify(produtoRepository).findById(2L);
    }

    @DisplayName("Deve retornar ProdutoResponseDTO do Create")
    @Test
    void shouldReturnProdutoResponseDTO() {
        given(produtoMapper.toEntity(produtoCreateDTO))
                .willReturn(produto);

        given(produtoRepository.save(produto))
                .willReturn(produtoSalvo);

        given(produtoMapper.toDTO(produtoSalvo))
                .willReturn(produtoResponseDTO);

        assertEquals(produtoResponseDTO, produtoService.create(produtoCreateDTO));

        verify(produtoMapper).toEntity(produtoCreateDTO);
        verify(produtoRepository).save(produto);
        verify(produtoMapper).toDTO(produtoSalvo);
    }

    @DisplayName("Deve apagar um produto")
    @Test
    void shouldDelete() {
        produtoService.delete(1L);

        verify(produtoRepository).deleteById(1L);
    }

    @DisplayName("Deve lançar ResourceNotFoundException")
    @Test
    void shouldThrowExceptionWhenDelete() {

        willThrow(new EmptyResultDataAccessException(1))
                .given(produtoRepository)
                .deleteById(3L);

        assertThrows(ResourceNotFoundException.class,
                () -> produtoService.delete(3L));

        verify(produtoRepository).deleteById(3L);
    }

    @DisplayName("Deve atualizar os dados de Produto")
    @Test
    void shouldUpdateProdutoData() {
        given(produtoRepository.findById(1L)).willReturn(Optional.of(produto));

        given(produtoRepository.save(produto)).willReturn(produtoSalvo);

        given(produtoMapper.toDTO(produtoSalvo)).willReturn(produtoResponseDTO);

        assertEquals(produtoResponseDTO, produtoService.update(produtoUpdateDTO, 1L));

        verify(produtoRepository).findById(1L);
        verify(produtoRepository).save(produtoSalvo);
        verify(produtoMapper).toDTO(produtoSalvo);
    }
}