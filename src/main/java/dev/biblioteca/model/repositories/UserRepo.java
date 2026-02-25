package dev.biblioteca.model.repositories;
import java.util.ArrayList;
import java.util.List;

import dev.biblioteca.model.entities.User;

public interface UserRepo {
   
    public void save(User u);

    public User buscar(String login);
}
