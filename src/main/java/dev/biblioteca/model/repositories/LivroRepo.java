package dev.biblioteca.model.repositories;
import java.util.ArrayList;
import java.util.List;

import dev.biblioteca.model.entities.Livro;

public class LivroRepo {
    private List<Livro> l = new ArrayList<>();

    public void save(Livro l){
        this.l.add(l);
        System.out.println("Livro salvo no banco local!");
    }
    // método que busca pelo isbn
    public Livro findByIsbn(String isbn){
        for(Livro livro : l){
            if(livro.getIsbn().equals(isbn)){
                return livro; // encontrou!
            }
        }
        return null; // não encontrou
    }
}
