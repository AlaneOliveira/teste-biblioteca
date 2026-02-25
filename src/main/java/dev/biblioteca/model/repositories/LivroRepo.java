package dev.biblioteca.model.repositories;

import dev.biblioteca.model.entities.Livro;

public interface LivroRepo {

    public void save (Livro l);

    public Livro findByTitulo (String titulo);

} 
