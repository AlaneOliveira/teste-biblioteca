package dev.biblioteca.model.repositories;
import java.util.ArrayList;
import java.util.List;

import dev.biblioteca.model.entities.Livro;

public class LivroRepo {
    private List<Livro> l = new ArrayList<>();

    public String save(Livro l){
        this.l.add(l);
        return "Livro cadastrado com sucesso";
    }
    // método que busca pelo titulo
    public Livro findByTitulo(String titulo){
        for(Livro livro : l){
            if(livro.getTitulo().equals(titulo)){
                return livro; // encontrou!
            }
        }
        return null; // não encontrou
    }
}
