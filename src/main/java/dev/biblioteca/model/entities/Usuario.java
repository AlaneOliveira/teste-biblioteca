package dev.biblioteca.entities;

public class usuario {
    private String login;
    private String senha;

    // get = poder editar 
    public String getLogin(){
        return login;
    }
    // set = não poder editar
    public void setLogin(String login){
        this.login = login;
    }
    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
}
