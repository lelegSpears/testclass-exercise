package Produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Main;
import org.example.produto.ProdutoController;
import org.example.produto.ProdutoService;
import org.example.produto.dto.ProdutoCreateDTO;
import org.example.produto.dto.ProdutoResponseDTO;
import org.example.produto.dto.ProdutoUpdateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
@ContextConfiguration(classes = Main.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnProdutoById() throws Exception {

        ProdutoResponseDTO dto =
                new ProdutoResponseDTO(1L, "PC", 6000.0);

        given(produtoService.findById(1L))
                .willReturn(dto);

        mockMvc.perform(get("/test/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("PC"))
                .andExpect(jsonPath("$.preco").value(6000.0));
    }

    @Test
    void shouldCreateProduto() throws Exception {

        ProdutoCreateDTO createDTO =
                new ProdutoCreateDTO("PC", 6000.0);

        ProdutoResponseDTO responseDTO =
                new ProdutoResponseDTO(1L, "PC", 6000.0);

        given(produtoService.create(any(ProdutoCreateDTO.class)))
                .willReturn(responseDTO);

        mockMvc.perform(post("/test/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("PC"))
                .andExpect(jsonPath("$.preco").value(6000.0));
    }

    @Test
    void shouldDeleteProduto() throws Exception {

        doNothing().when(produtoService).delete(1L);

        mockMvc.perform(delete("/test/delete/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateProduto() throws Exception {

        ProdutoUpdateDTO updateDTO =
                new ProdutoUpdateDTO("Placa", 300.0);

        ProdutoResponseDTO responseDTO =
                new ProdutoResponseDTO(1L, "Placa", 300.0);

        given(produtoService.update(any(ProdutoUpdateDTO.class), eq(1L)))
                .willReturn(responseDTO);

        mockMvc.perform(patch("/test/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Placa"))
                .andExpect(jsonPath("$.preco").value(300.0));
    }
}