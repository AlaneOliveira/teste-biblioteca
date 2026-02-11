package dev.biblioteca.service;
import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.UserRepo;

public class UserService {
    // método para inserir usuário
    public String inserir(Use u){
        System.out.println(cadastrar(u));
        return "Usuário logado com sucesso!";
    }
    // método para cadastrar usuário
    public String cadastrar(User u){
        System.out.println("Usuário " + u.getLogin() + "Senha " + u.getSenha() + " cadastrado com sucesso!");
        return "Usuário cadastrado com sucesso!";
    }
}
