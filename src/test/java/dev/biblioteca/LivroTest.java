package dev.biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;

@SpringBootTest // anotação para dizer que é um teste de contexto do Spring Boot
public class LivroTest {
    private Livro livro; // criando um objeto do tipo Livro para usar nos testes

    @BeforeEach // anotação para dizer que esse método deve ser executado antes de cada teste
    public void setUp(){ // método para configurar o ambiente de teste, criando um objeto do tipo Livro com dados de exemplo
        livro = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade("3")
                .build();
    }

}
