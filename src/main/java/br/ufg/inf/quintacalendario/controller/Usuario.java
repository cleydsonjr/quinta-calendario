
package br.ufg.inf.quintacalendario.controller;

public class Usuario {
    private String nome;
    private String senha;
    
    public String getNome(){
        return nome;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
