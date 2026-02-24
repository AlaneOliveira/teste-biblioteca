package dev.biblioteca.service;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.LivroRepo;

public class LivroService { // ← sem parênteses!

    private LivroRepo livroRepo = new LivroRepo(); // para não retornar null
    // metodo de inserir um livro cadastrado
    public String inserir(Livro l){ 
        System.out.println(cadastrar(l));
        return cadastrar(l); // retorna o resultado do cadastrar
    }

    // metodo de cadastrar um novo livro na biblioteca
    public String cadastrar(Livro l){
        if(l.getTitulo().isEmpty()){
            return "Livro sem titulo, por favor informe";
        }
        if(l.getAutor().isEmpty()){
            return "Livro sem autor, por favor informe";
        }
        if(l.getIsbn().isEmpty()){
            return "Livro sem ISBN, por favor informe";
        }
        if(l.getQuantidade() == 0){
            return "Livro sem a quantidade, por favor informe";
        }
        System.out.println("Livro " + l.getTitulo() + " cadastrado com sucesso!");
        return "Livro cadastrado com sucesso";
    }
    public String consulta(String titulo){
    if(titulo.isEmpty()){
        return "Título inválido!";
    }
    // busca o livro pelo titulo
    return "Livro encontrado: " + livroRepo.findByTitulo(titulo);
    }
    // metodo de emprestar um livro para um usuário
    public String emprestar(Livro l, User u){
        if (l.getTitulo().isEmpty()){ // verifica se o titulo do livro está vazio
            return "Titulo inválido!";
        }
        System.out.println("Livro: " + l.getTitulo() + " " + u.getLogin() + " com sucesso!");
        return "Livro emprestado com sucesso!";
    }
}