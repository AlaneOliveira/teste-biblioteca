package dev.biblioteca.model.repositories;
import java.util.ArrayList;
import java.util.List;

import dev.biblioteca.model.entities.User;

public class UserRepo {
    private List<User> u = new ArrayList<>(); // criando uma lista de usuários para armazenar os dados
    
    public void save(User u){ // método para salvar um usuário na lista
        this.u.add(u); // adicionando o usuário na lista
        System.out.println("Usuário salvo no banco local!"); // mensagem de confirmação de que o usuário foi salvo no banco local
    }
}
