package dev.biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.service.LivroService;

@ExtendWith(MockitoExtension.class) 
@SpringBootTest // anotação para dizer que é um teste de contexto do Spring Boot
public class LivroTest {
    private Livro livro; // criando um objeto do tipo Livro para usar nos testes
    private User user;
    @InjectMocks // anotação para injetar os mocks criados na classe UserService
    private LivroService livroService = new LivroService(); // criando um objeto do tipo UserService
    String resultado;
    @BeforeEach // anotação para dizer que esse método deve ser executado antes de cada teste
    public void setUp(){ // método para configurar o ambiente de teste, criando um objeto do tipo Livro com dados de exemplo
        livro = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(3)
                .build();
    }
    @Test // anotação para dizer que esse método é um teste
    public void CadastroSemTitulo(){
        livro = Livro.builder()
            .titulo("")
            .autor("Autor Teste")
            .isbn("123456789")
            .quantidade(3)
            .build();
        resultado = livroService.cadastrar(livro);
        assertEquals("Livro sem titulo, por favor informe", resultado);
    }
    @Test
    public void CadastroSemAutor(){
        livro = Livro.builder()
            .titulo("teste")
            .autor("")
            .isbn("123456789")
            .quantidade(3)
            .build();
        resultado = livroService.cadastrar(livro);
        assertEquals("Livro sem autor, por favor informe", resultado);
    }
    @Test
    public void CadastroSemIsbn(){
        livro = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("")
                .quantidade(3)
                .build();
        resultado = livroService.cadastrar(livro);
        assertEquals("Livro sem ISBN, por favor informe", resultado);
    }
    @Test
    public void CadastroSemQuantidade(){
        livro = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(0)
                .build();
        resultado = livroService.cadastrar(livro);
        assertEquals("Livro sem a quantidade, por favor informe", resultado);
    }
    @Test
    public void CadastroCamposValidos(){
        resultado = livroService.cadastrar(livro);
        assertEquals("Livro cadastrado com sucesso", resultado);
    }
    @Test 
    public void ConsultaValida(){ 
        livro = Livro.builder()
        .titulo("teste")
        .build();
        resultado = livroService.consulta(livro.getIsbn());  //  pega o isbn do livro
        assertEquals("Livro cadastrado", resultado);    
    }
    @Test
    public void EmprestimoValido(){
        livro = Livro.builder()
            .isbn("123456789")
            .user(user) // passando o objeto user criado no método setUp para o atributo user do livro
        .build();
        resultado = livroService.emprestar(livro, user);
        assertEquals("Emprestimo realizado com sucesso!", resultado);
    }
    @Test
    public void EmprestimoEstoqueIndisponivel(){
        livro = Livro.builder()
            .isbn("123456789")
            .user(user) // passando o objeto user criado no método setUp para o atributo user do livro
            .build();
        resultado = livroService.emprestar(livro, user);
        assertEquals("Emprestimo nao realizado por falta de estoque", resultado);    
    }
}
