package dev.biblioteca;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest // anotação para dizer que é um teste de contexto do Spring Boot
public class UserTest {

    private User user;
    @BeforeEach // anotacao que diz pro teste rodar antes de qualquer teste
    public void setUp(){
         user = User.builder()
                .login("teste")
                .senha(123)
                .build();
    }
    @Test 
    public void LoginUserSucesso(){

    }
    @Test
    public void LoginSenhaInvalida(){

    }
    @Test 
    public void LoginInvalido(){

    }
    @Test
    public void UserInexistente(){

    }
    @Test 
    public void UserVazio(){

    }
    @Test
    public void SenhaVazia(){

    }

}