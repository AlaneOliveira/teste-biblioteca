package dev.biblioteca.service;

import java.util.Optional;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.LivroRepo;

public class LivroService { // ← sem parênteses!

    private LivroRepo lRepo;

    public LivroService(LivroRepo rLivroRepo) {
        this.lRepo = rLivroRepo;
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
        lRepo.save(l); // salva o livro no repositório
        return "Livro cadastrado com sucesso";
    }

    public Optional<Livro> consulta(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return Optional.empty();
        }

        Livro local = lRepo.findByTitulo(titulo);

        if (local == null) {
            return Optional.empty();
        }

        return Optional.of(local);
    }

    // metodo de emprestimo de um livro para um usuário
    public String emprestimo(Livro l, User u) {
    if (l.getQuantidade() <= 0) {
        return "Livro indisponível para empréstimo!";
    }
    if (u == null) {
        return "Usuário inválido!";
    }

    l.setQuantidade(l.getQuantidade() - 1);
    l.setUser(u); // Associa o usuário ao livro
    lRepo.save(l); // PERSISTE a alteração

    return "Emprestimo realizado com sucesso!";
}
}
