package dev.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.UserRepo;
import dev.biblioteca.service.UserService;

@ExtendWith(MockitoExtension.class) // o Mockito criará automaticamente os objetos simulados necessários,
                                    // simplificando o código do teste.
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

        when(this.rUserRepo.buscar("oi")).thenReturn(null); // simulando que o usuário não existe

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

        resultado = userService.logar(userTeste4); // chamando o método inserir do UserService para testar o login do usuário com senha vazia
                                            
        assertEquals("preencha o campo senha corretamente", resultado); // verificando se o resultado do método inserir
                                                                        // é igual a "Senha vazia!"
    }
}
