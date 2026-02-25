package dev.biblioteca.model.entities;

import lombok.Builder; // importação do lombok para gerar um construtor com todos os atributos da classe, facilitando a criação de objetos do tipo User
import lombok.Data;

@Data // anotação do lomboak para gerar os métodos getters e setters automaticamente 
@Builder // anotação do lombok para gerar um construtor com todos os atributos da classe, facilitando a criação de objetos do tipo User
public class User { // classe usuário, com seus atributos e métodos getters e setters
    private String login;
    private String senha;
}
