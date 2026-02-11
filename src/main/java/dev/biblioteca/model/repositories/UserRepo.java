package dev.biblioteca.model.repositories;
import dev.biblioteca.model.entities.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo {
    private List<Usuario> u = new ArrayList<>(); // criando uma lista de usuários para armazenar os dados
    
    public void save(Usuario u){ // método para salvar um usuário na lista
        this.u.add(u); // adicionando o usuário na lista
        System.out.println("Usuário salvo no banco local!"); // mensagem de confirmação de que o usuário foi salvo no banco local
    }
}
