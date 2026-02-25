package dev.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.UserRepo;
import dev.biblioteca.service.UserService;

@ExtendWith(MockitoExtension.class) // o Mockito criará automaticamente os objetos simulados necessários,
                                    // simplificando o código do teste.
@SpringBootTest // anotação para dizer que é um teste de contexto do Spring Boot
public class UserTest {

    private User user; // criando um objeto do tipo User para usar nos testes

    @Mock
    private UserRepo rUserRepo;

    @InjectMocks // anotação para injetar os mocks criados na classe UserService
    private UserService userService;

    String resultado;

    @BeforeEach // anotacao que diz pro teste rodar antes de qualquer teste
    public void setUp() {
        user = User.builder()
                .login("teste")
                .senha("123")
                .build();
    }

    @Test // anotação para dizer que esse método é um teste
    public void LoginUserSucesso() {

        // Arrage
        when(this.rUserRepo.buscar("teste")).thenReturn(user);

        //act
        resultado = userService.logar(user);
        
        //Validate
        // asserEquals = serve para verificar se valores sao iguais
        assertEquals("Usuário logado com sucesso!", resultado); // verificando se o resultado do método inserir é igual
                                                                // a "Usuário logado com sucesso!"
    }

    @Test
    public void LoginExistenteSenhaInvalida() {
        User userTeste = User.builder()
                .login("teste")
                .senha("ddd") // senha inválida (não é número)
                .build();

        when(this.rUserRepo.buscar("teste")).thenReturn(user); 

        resultado = userService.logar(userTeste);

        assertEquals("preencha o campo senha corretamente", resultado); // verificando se o resultado do método inserir
                                                                        // é igual a "Usuário ou senha inválidos!"
    }

    @Test
    public void LoginInvalido() {
        User userTeste2 = User.builder() // criando um objeto do tipo User com login vazio para testar o login inválido
                .login("oi") // login invalido (não existe)
                .senha("123")
                .build();

        when(this.rUserRepo.buscar("teste")).thenReturn(user);

        resultado = userService.logar(userTeste2);

        assertEquals("usuario nao encontrado", resultado); // login existe e senha válida
    }

    @Test
    public void LoginVazio() {
        User userTeste3 = User.builder() // criando um objeto do tipo User com login e senha vazios para testar o login
                              // de usuário vazio
                .login("") // login vazio
                .senha("123") // senha válida
                .build();

        when(this.rUserRepo.buscar("teste")).thenReturn(user);

        resultado = userService.logar(userTeste3);

        assertEquals("Preencha o campo login", resultado); // verificando se o resultado do método inserir é igual a
                                                           // "Preencha o campo login"
    }

    @Test
    public void SenhaVazia() {
        User userTeste4 = User.builder() // criando um objeto do tipo User com login válido e senha vazia para testar o
                              // login de senha vazia
                .login("teste") // login válido
                .senha("") // senha vazia
                .build();

        when(this.rUserRepo.buscar("teste")).thenReturn(user);

        resultado = userService.logar(userTeste4); // chamando o método inserir do UserService para testar o login do
                                               // usuário
        assertEquals("preencha o campo senha corretamente", resultado); // verificando se o resultado do método inserir
                                                                        // é igual a "Senha vazia!"
    }
}
