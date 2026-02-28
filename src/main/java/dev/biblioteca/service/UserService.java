package dev.biblioteca.service;

import dev.biblioteca.model.entities.User;
import dev.biblioteca.model.repositories.UserRepo;

public class UserService {

    private UserRepo uRepo; // objeto da classe UserRepo para acessar os métodos de salvar usuário no banco local

    public UserService(UserRepo rUserRepo) {
        this.uRepo = rUserRepo;
    }

    // método para inserir usuário
    public String logar(User usuario){
        if (usuario.getLogin().isEmpty()){
            return "Preencha o campo login";
        }
        else if (usuario.getSenha().isEmpty() || !usuario.getSenha().matches("[0-9]+")){ // só aceita numeros
            return "preencha o campo senha corretamente";
        } 
        User local = uRepo.buscar(usuario.getLogin());

        if( local == null){
            return "usuario nao encontrado";
        }

        if (!local.getSenha().equals(usuario.getSenha())) {
            return "Senha Inválida";
        }
        return "Usuário logado com sucesso!";
    }

    // método para cadastrar usuário
    public String cadastrar(User usuario){
        if(!usuario.getSenha().matches("[0-9]+")){
            return "Preencha o campo senha corretamente";
        }
        if(usuario.getLogin().isEmpty() || usuario.getSenha().isEmpty()){ // verifica se o login do usuário está vazio
            return "usuario vazio";
        }
    
        uRepo.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }
}
