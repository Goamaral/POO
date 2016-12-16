package com.company;

public class Pessoa implements java.io.Serializable {
	private static final long serialVersionUID = 12L;
    private String nome;
    private String email;

    public Pessoa(String nome, String email) {

        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
