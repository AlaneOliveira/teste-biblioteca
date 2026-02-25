package dev.biblioteca.service;

import java.util.Optional;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.LivroRepo;

public class LivroService { // ← sem parênteses!

    private LivroRepo lRepo;
    private LivroService (LivroRepo livroRepo){
        this.lRepo = livroRepo;
    }

    // metodo de cadastrar um novo livro na biblioteca
    public String cadastrar(Livro l) {
        if (l.getTitulo().isEmpty()) {
            return "Livro sem titulo, por favor informe";
        }
        if (l.getAutor().isEmpty()) {
            return "Livro sem autor, por favor informe";
        }
        if (l.getIsbn().isEmpty()) {
            return "Livro sem ISBN, por favor informe";
        }
        if (l.getQuantidade() == 0) {
            return "Livro sem a quantidade, por favor informe";
        }
        livroRepo.save(l); // salva o livro no repositório
        return "Livro cadastrado com sucesso";
    }

    public Optional<Livro> consulta(String titulo) {
        if (titulo.isEmpty()) {
            return Optional.empty();
        }
        // busca o livro pelo titulo
        return Optional.of(livroRepo.findByTitulo(titulo));
    }

    // metodo de emprestar um livro para um usuário
    public String emprestar(Livro l, User u) {

        if (l.getTitulo() == null || l.getTitulo().isEmpty()) {
            return "Título inválido!";
        }

        if (l.getQuantidade() <= 0) {
            return "Livro indisponível para empréstimo!";
        }

        if (u == null) {
            return "Usuário inválido!";
        }
        l.setQuantidade(l.getQuantidade() - 1);

        return "Emprestimo realizado com sucesso!";
    }
}