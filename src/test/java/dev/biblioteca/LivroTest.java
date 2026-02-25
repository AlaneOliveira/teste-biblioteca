package dev.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.service.LivroService;
import dev.biblioteca.model.repositories.LivroRepo;

@ExtendWith(MockitoExtension.class) // o Mockito criará automaticamente os objetos simulados necessários,
                                    // simplificando o código do teste.
@SpringBootTest // anotação para dizer que é um teste de contexto do Spring Boot
public class LivroTest {

    private Livro livro; // criando um objeto do tipo Livro para usar nos testes

    private User user;

    @Mock
    private LivroRepo livroRepo;

    @InjectMocks // anotação para injetar os mocks criados na classe UserService
    private LivroService livroService;
    String resultado;

    @BeforeEach // anotação para dizer que esse método deve ser executado antes de cada teste
    public void setUp() { // método para configurar o ambiente de teste, criando um objeto do tipo Livro
                          // com dados de exemplo
        livro = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(3)
                .build();
    }

    @Test // anotação para dizer que esse método é um teste
    public void cadastroSemTitulo() {

        Livro livroTeste = Livro.builder()
                .titulo("")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(3)
                .build();

        resultado = livroService.cadastrar(livroTeste);

        assertEquals("Livro sem titulo, por favor informe", resultado);

        verify(livroRepo, never()).save(any());
    }

    @Test
    public void CadastroSemAutor() {
        Livro livroTeste1 = Livro.builder()
                .titulo("teste")
                .autor("")
                .isbn("123456789")
                .quantidade(3)
                .build();

        resultado = livroService.cadastrar(livroTeste1);

        assertEquals("Livro sem autor, por favor informe", resultado);

        verify(livroRepo, never()).save(any());
    }

    @Test
    public void CadastroSemIsbn() {
        Livro livroteste2 = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("")
                .quantidade(3)
                .build();

        resultado = livroService.cadastrar(livroteste2);        

        assertEquals("Livro sem ISBN, por favor informe", resultado);

        verify(livroRepo, never()).save(any());
    }

    @Test
    public void CadastroSemQuantidade() {
        Livro livroteste3 = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(0)
                .build();

        resultado = livroService.cadastrar(livroteste3);        

        assertEquals("Livro sem quantidade, por favor informe", resultado);

        verify(livroRepo, never()).save(any());
    }

    @Test
    public void CadastroCamposValidos() {
        Livro livroteste4 = Livro.builder()
                .titulo("")
                .autor("")
                .isbn("")
                .quantidade(0)
                .build();

        resultado = livroService.cadastrar(livroteste4);

        assertEquals("Livro sem quantidade, por favor informe", resultado);

        verify(livroRepo, never()).save(any());
    }

    @Test
public void ConsultaValida() {
    // Arrange (Organizar)
    Livro livroLocal = Livro.builder()
            .titulo("teste")
            .autor("Autor Teste")
            .isbn("123456789")
            .quantidade(3)
            .build();

    // Usa a variável 'livroRepo' (minúsculo) e o método correto 'findByTitulo'
    when(livroRepo.findByTitulo("teste")).thenReturn(livroLocal); 

    // 2. Act (Agir)
    // CORREÇÃO: O retorno é Optional<Livro>, não String.
    java.util.Optional<Livro> resultadoOptional = livroService.consulta("teste");

    // 3. Assert (Verificar)
    // Verificamos se o Optional não está vazio e se o conteúdo é o esperado
    assertEquals(true, resultadoOptional.isPresent(), "O livro deveria ter sido encontrado");
    assertEquals("teste", resultadoOptional.get().getTitulo());
}

    @Test
    public void EmprestimoValido() {
        livro = Livro.builder()
                .titulo("123456789")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(3)
                .user(user)
                .build();

        livroService.cadastrar(livro); // cadastra primeiro!
        Livro resLivro = livroService.consulta(livro.getTitulo()).get();
        resultado = livroService.emprestar(resLivro, user); // aqui passa o objeto user
        assertEquals("Emprestimo realizado com sucesso!", resultado);
    }

    @Test
    public void EmprestimoEstoqueIndisponivel() {
        livro = Livro.builder()
                .titulo("123456789")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(0)
                .user(user) // passando o objeto user criado no método setUp para o atributo user do livro
                .build();
        livroService.cadastrar(livro); // cadastra primeiro!

        resultado = livroService.consulta(livro.getTitulo());

        resultado = livroService.emprestar(livro, user); // aqui passa o objeto user
        assertEquals("Livro indisponível para empréstimo!", resultado);
    }
}
