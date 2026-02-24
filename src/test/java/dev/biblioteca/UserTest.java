package dev.biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import dev.biblioteca.model.entities.User;
import dev.biblioteca.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest // anotação para dizer que é um teste de contexto do Spring Boot
public class UserTest {

    private User user; // criando um objeto do tipo User para usar nos testes
    @InjectMocks // anotação para injetar os mocks criados na classe UserService
    private UserService userService = new UserService(); // criando um objeto do tipo UserService
    String resultado;
    @BeforeEach // anotacao que diz pro teste rodar antes de qualquer teste
    public void setUp(){
         user = User.builder()
                .login("teste")
                .senha("123")
                .build();
    }
    @Test // anotação para dizer que esse método é um teste
    public void LoginUserSucesso(){
        resultado = userService.inserir(user);
        // asserEquals = serve para verificar se valores sao iguais
        assertEquals("Usuário logado com sucesso!", resultado); // verificando se o resultado do método inserir é igual a "Usuário logado com sucesso!"
    }
    @Test
    public void LoginSenhaInvalida(){
        user = User.builder()
            .login("teste")   // ← login vazio
            .senha("4dd")
            .build();
        resultado = userService.inserir(user);
        assertEquals("Senha inválida!", resultado); // verificando se o resultado
    }
    @Test 
    public void LoginInvalido(){
        user = User.builder() // criando um objeto do tipo User com login vazio para testar o login inválido
            .login("") // login vazio
            .senha("123") // senha válida
            .build();
        resultado = userService.inserir(user);
        assertEquals("Login inválido!", resultado); // verificando se o resultado do método inserir é igual a "Login inválido!"
    }
    @Test
    public void LoginUserInexistente(){
        user = User.builder() // criando um objeto do tipo User com login e senha que não existem para testar o login de usuário inexistente
            .login("userhaha") // login que não existe
            .senha("123") // senha válida
            .build();
        resultado = userService.inserir(user);
        assertEquals("Usuário inexistente!", resultado); // verificando se o resultado do método inserir é igual a "Usuário inexistente!"
    }
    @Test 
    public void LoginUserVazio(){
        user = User.builder() // criando um objeto do tipo User com login e senha vazios para testar o login de usuário vazio
            .login("") // login vazio
            .senha("") // senha vazia
            .build();
        resultado = userService.inserir(user);
        assertEquals("Usuário vazio!", resultado); // verificando se o resultado do método inserir é igual a "Usuário vazio!"
    }
    @Test
    public void LoginSenhaVazia(){
        user = User.builder() // criando um objeto do tipo User com login válido e senha vazia para testar o login de senha vazia
            .login("teste") // login válido
            .senha("") // senha vazia
            .build();
        resultado = userService.inserir(user); // chamando o método inserir do UserService para testar o login do usuário
        assertEquals("Senha vazia!", resultado); // verificando se o resultado do método inserir é igual a "Senha vazia!"
    }
}
