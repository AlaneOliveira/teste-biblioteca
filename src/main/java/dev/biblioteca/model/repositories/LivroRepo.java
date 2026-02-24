package dev.biblioteca.model.repositories;
import java.util.ArrayList;
import java.util.List;

import dev.biblioteca.model.entities.Livro;
import dev.biblioteca.model.entities.User;

public class LivroRepo {
    private List<Livro> l = new ArrayList<>();

    public void save(Livro l){
        this.l.add(l);
        System.out.println("Livro salvo no banco local!");
    }
}
