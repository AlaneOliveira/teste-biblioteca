package dev.biblioteca.service;
import dev.biblioteca.model.entities.Usuario;

public class UsuarioService {
    // método para inserir usuário
    public String inserir(Usuario u){
        System.out.println(cadastrar(u));
        return "Usuário logado com sucesso!";
    }
    // método para cadastrar usuário
    public String cadastrar(Usuario u){
        System.out.println("Usuário " + u.getLogin() + "Senha " + u.getSenha() + " cadastrado com sucesso!");
        return "Usuário cadastrado com sucesso!";
    }
}
