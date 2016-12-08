package br.ufg.inf.quintacalendario.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Usuario {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String login;
    private String senha;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String login() {
        return login;
    }

    public String senha() {
        return senha;
    }
}
