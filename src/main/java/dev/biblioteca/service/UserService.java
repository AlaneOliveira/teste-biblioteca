package dev.biblioteca.service;

import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.UserRepo;

public class UserService {

    private UserRepo uRepo = new UserRepo(); // objeto da classe UserRepo para acessar os métodos de salvar usuário no banco local

    // método para inserir usuário
    public String inserir(User usuario){
        System.out.println(cadastrar(usuario));
        return "Usuário logado com sucesso!";
    }

    // método para cadastrar usuário
    public String cadastrar(User usuario){
        if(usuario.getLogin().isEmpty()){
            return "Preencha o campo login";
        }
        if(usuario.getSenha().isEmpty() || !usuario.getSenha().matches("[0-9]+")){ // só aceita numeros
            return "preencha o campo senha corretamente";
        }
        System.out.println("Usuário " + usuario.getLogin() + " Senha " + usuario.getSenha() + " cadastrado com sucesso!");
        uRepo.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }
}
