package dev.biblioteca.model.entities;

import lombok.Builder;
import lombok.Data;

@Data // anotação do lomboak para gerar os métodos getters e setters automaticamente 
@Builder // anotação do lombok para gerar um construtor com todos os atributos da classe, facilitando a criação de objetos do tipo Livro
public class Livro { // classe livro, com seus atributos e métodos getters e setters
    private String titulo;
    private String autor;
    private String isbn;
    private int quantidade;
    private String emprestimo;

    private User user; // atributo do tipo User para armazenar o usuário que emprestou o livro
}
