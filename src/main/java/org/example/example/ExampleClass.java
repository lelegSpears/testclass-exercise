package org.example.example;

public class ExampleClass {
        public int somar(int a, int b) {
            return a + b;
        }

        public Double dividir(double a, double b) {

            if (b == 0) {
                throw new IllegalArgumentException("Divisão por zero não permitida");
            }

            return a / b;
        }

        public boolean isPar(int numero) {
            return numero % 2 == 0;
        }

        public String saudacao(String nome) {

            if (nome == null || nome.isBlank()) {
                return "Olá, visitante!";
            }

            return "Olá, " + nome + "!";
        }
}
