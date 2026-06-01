package example;

import org.example.example.ExampleClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleClassTest {

    private final ExampleClass exampleClass = new ExampleClass();

    @Test
    @DisplayName("Deve Somar dois números")
    void shouldReturnSum(){
    int resultado = exampleClass.somar(1,3);
    assertEquals(4, resultado);
    }

    @Test
    @DisplayName("Deve retornar resultado da divisão")
    void shouldReturnDivisor(){
        Double resultado = exampleClass.dividir(2,4);
        assertEquals(0.5, resultado, 0.0001); // margem de tolerancia 0.0001
    }

    @Test
    @DisplayName("Ao dividir por 0 deve retornar IllegalArgumentException")
    void shouldReturnIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                () -> exampleClass.dividir(10,0));
    }

    @Test
    @DisplayName("Deve retornar true se for par")
    void shouldReturnTrue(){
        assertTrue(exampleClass.isPar(4));
    }

    @Test
    @DisplayName("Deve retornar falso se for ímpar")
    void shouldReturnFalse(){
        assertFalse(exampleClass.isPar(3));
    }

    @Test
    @DisplayName("Deve montar e retornar Olá + %s + ! ")
    void shouldReturnFormattedString(){
        String resultado = exampleClass.saudacao("amigo");
        assertEquals("Olá, amigo!",resultado);
    }

    @Test
    @DisplayName("Deve montar e retornar Olá + %s + ! ")
    void shouldReturn(){
        String resultado = exampleClass.saudacao(null);
        assertEquals("Olá, visitante!",resultado);
    }

    @Test
    @DisplayName("Deve montar e retornar Olá + %s + ! ")
    void shouldReturnFormat(){
        String resultado = exampleClass.saudacao("");
        assertEquals("Olá, visitante!",resultado);
    }
}
