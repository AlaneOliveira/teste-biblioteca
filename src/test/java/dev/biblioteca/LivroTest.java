package dev.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.LivroRepo;
import dev.biblioteca.service.LivroService;

@ExtendWith(MockitoExtension.class) // o Mockito criará automaticamente os objetos simulados necessários,
                                    // simplificando o código do teste.
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
        livro = Livro.builder() // usando o padrão builder para criar um objeto do tipo Livro
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(3)
                .build();
        user = User.builder() // criando um objeto do tipo User para usar nos testes
                .login("teste")
                .senha("123")
                .build();        
    }
    @Test
public void CadastroCamposValidos() {
    // 1. Arrange (Organizar)
    // O mock deve simular o método que EXISTE na interface LivroRepo (findByTitulo)
    // Se o livro já existe, não precisamos mockar nada para o cadastro de sucesso, 
    // mas se o objetivo é testar o CADASTRAR, usamos o save.
    
    Livro livroValido = Livro.builder()
            .titulo("Java Moderno")
            .autor("Autor Exemplo")
            .isbn("987654321")
            .quantidade(10)
            .build();

    // 2. Act (Agir)
    // O método cadastrar(Livro l) do seu LivroService retorna uma STRING
    String resultadoCadastro = livroService.cadastrar(livroValido);

    // 3. Assert (Verificar)
    assertEquals("Livro cadastrado com sucesso", resultadoCadastro);
    
    // Verifica se o repositório realmente foi chamado para salvar
    verify(livroRepo, times(1)).save(livroValido);
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

        assertEquals("Livro sem a quantidade, por favor informe", resultado);

        verify(livroRepo, never()).save(any());
    }

    @Test
public void ConsultaValida() {
    // Arrange (Organizar)
    Livro consultateste = Livro.builder()
            .titulo("teste")
            .autor("Autor Teste")
            .isbn("123456789")
            .quantidade(3)
            .build();

    // Usa a variável 'livroRepo' (minúsculo) e o método correto 'findByTitulo'
    when(livroRepo.findByTitulo("teste")).thenReturn(consultateste); // CORREÇÃO: findByTitulo, não findByLogin 

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
        Livro eValido = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(3)
                .user(user)

                .build();
        when(livroRepo.findByTitulo("teste")).thenReturn(eValido); // CORREÇÃO: findByTitulo, não findByLogin
        
        Livro resLivro = livroService.consulta(eValido.getTitulo()).get();

        resultado = livroService.emprestimo(resLivro, user); // aqui passa o objeto user

        verify(livroRepo, times(1)).save(resLivro); // Verifica se o método save foi chamado para persistir a alteração do livro

        assertEquals("Emprestimo realizado com sucesso!", resultado);
    }

    @Test
    public void EmprestimoEstoqueIndisponivel() {
        Livro eInvalido = Livro.builder()
                .titulo("teste")
                .autor("Autor Teste")
                .isbn("123456789")
                .quantidade(0)
                .user(user) // passando o objeto user criado no método setUp para o atributo user do livro
                .build();
        when(livroRepo.findByTitulo("teste")).thenReturn(eInvalido); // CORREÇÃO: findByTitulo, não findByLogin
        
        Livro resLivro = livroService.consulta(eInvalido.getTitulo()).get();

        resultado = livroService.emprestimo(resLivro, user); // aqui passa o objeto user

        assertEquals("Livro indisponível para empréstimo!", resultado);
    }
}
