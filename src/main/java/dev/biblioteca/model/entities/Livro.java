package dev.biblioteca.entities;

public class livro {
    private String titulo;
    private String autor;
    private String ISBN;
    private int quantidade;
    private String emprestimo;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getEmprestimo() {
        return emprestimo;
    }
    public void setEmprestimo(String emprestimo) {
        this.emprestimo = emprestimo;
    }
}
