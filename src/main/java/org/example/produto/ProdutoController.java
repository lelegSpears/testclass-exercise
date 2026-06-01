package org.example.produto;

import org.example.produto.dto.ProdutoCreateDTO;
import org.example.produto.dto.ProdutoResponseDTO;
import org.example.produto.dto.ProdutoUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class ProdutoController {

    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoCreateDTO dto){
        return ResponseEntity.ok(produtoService.create(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@RequestBody ProdutoUpdateDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(produtoService.update(dto, id));
    }
}
