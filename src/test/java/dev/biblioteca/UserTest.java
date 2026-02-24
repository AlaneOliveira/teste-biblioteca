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
    @BeforeEach // anotacao que diz pro teste rodar antes de qualquer teste
    public void setUp(){
         user = User.builder()
                .login("teste")
                .senha(123)
                .build();
    }
    @Test // anotação para dizer que esse método é um teste
    public void LoginUserSucesso(){
        String resultado = userService.inserir(user);
        // asserEquals = serve para verificar se valores sao iguais
        assertEquals("Usuário logado com sucesso!", resultado); // verificando se o resultado do método inserir é igual a "Usuário logado com sucesso!"
    }
    @Test
    public void LoginSenhaInvalida(){
        String resultado = userService.inserir(user);
        assertEquals("Senha inválida!", resultado); // verificando se o resultado
    }
    @Test 
    public void LoginInvalido(){
        String resultado = userService.inserir(user);
        assertEquals("Login inválido!", resultado); // verificando se o resultado do método inserir é igual a "Login inválido!"
    }
    @Test
    public void UserInexistente(){
        String resultado = userService.inserir(user);
        assertEquals("Usuário inexistente!", resultado); // verificando se o resultado do método inserir é igual a "Usuário inexistente!"
    }
    @Test 
    public void UserVazio(){
        String resultado = userService.inserir(user);
        assertEquals("Usuário vazio!", resultado); // verificando se o resultado do método inserir é igual a "Usuário vazio!"
    }
    @Test
    public void SenhaVazia(){
        String resultado = userService.inserir(user); // chamando o método inserir do UserService para testar o login do usuário
        assertEquals("Senha vazia!", resultado); // verificando se o resultado do método inserir é igual a "Senha vazia!"
    }
}
