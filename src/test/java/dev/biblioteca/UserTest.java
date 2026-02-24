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
public void LoginExistenteSenhaInvalida(){
    user = User.builder()
        .login("teste")   // login válido
        .senha("ddd") // senha inválida (não é número)
        .build();
    resultado = userService.inserir(user);
    assertEquals("preencha o campo senha corretamente", resultado); // verificando se o resultado do método inserir é igual a "Usuário ou senha inválidos!"
}

@Test 
public void LoginInvalido(){
    user = User.builder() // criando um objeto do tipo User com login vazio para testar o login inválido
        .login("oi") // login invalido (não existe)
        .senha("123") // senha válida
        .build();
    resultado = userService.inserir(user);
    assertEquals("Usuário logado com sucesso!", resultado); // login existe e senha válida
}

@Test
public void LoginVazio(){
    user = User.builder() // criando um objeto do tipo User com login e senha vazios para testar o login de usuário vazio
        .login("") // login vazio
        .senha("123") // senha válida
        .build();
    resultado = userService.inserir(user);
    assertEquals("Preencha o campo login", resultado); // verificando se o resultado do método inserir é igual a "Preencha o campo login"
}

@Test
public void SenhaVazia(){
    user = User.builder() // criando um objeto do tipo User com login válido e senha vazia para testar o login de senha vazia
        .login("teste") // login válido
        .senha("") // senha vazia
        .build();
    resultado = userService.inserir(user); // chamando o método inserir do UserService para testar o login do usuário
    assertEquals("preencha o campo senha corretamente", resultado); // verificando se o resultado do método inserir é igual a "Senha vazia!"
}
}
