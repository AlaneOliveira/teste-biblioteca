package dev.biblioteca.model.repositories;

import java.util.ArrayList;
import java.util.List;

import dev.biblioteca.model.entities.User;

public class UsuarioRepo {
    private List<User> u = new ArrayList<>();

    public void save(User u){
        this.u.add(u);
        System.out.println("Usuário salvo no banco local!");
    }
}
