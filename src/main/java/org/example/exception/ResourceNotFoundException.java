package org.example.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Produto não encontrado: " + id);
    }
}
