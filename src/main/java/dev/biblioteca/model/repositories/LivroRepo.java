package dev.biblioteca.repositories;
import dev.biblioteca.entities.livro;
import dev.biblioteca.entities.usuario;

public class livroRepo {
    // metodo de inserir um livro cadastrado
    public String inserir(livro l){ 
        System.out.println(cadastrar(l)); // chamando o método cadastrar para inserir o livro
        return "Livro cadastrado com sucesso!";
    }
    // metodo de cadastrar um novo livro na biblioteca
    public String cadastrar(livro l){
        System.out.println("Livro " + l.getTitulo() + " autor " + l.getAutor() + " ISBN " + l.getISBN() + " quantidade " + l.getQuantidade() + " cadastrado com sucesso!"); // pega os atributos da classe livro e cadastra um novo livro
        return "Livro cadastrado com sucesso!";
    }
    // metodo de emprestar um livro para um usuário
    public String emprestar(livro l, usuario u){
        System.out.println("Livro " + l.getTitulo() + " emprestado para " + u.getLogin() + " com sucesso!");
        return "Livro emprestado com sucesso!";
    }
}
