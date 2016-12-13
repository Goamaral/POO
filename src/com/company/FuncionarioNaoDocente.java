package com.company;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class FuncionarioNaoDocente extends Funcionario {
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public FuncionarioNaoDocente(String nome, String email, int numMecanografico, String categoria, String cargo) {
        super(nome, email, numMecanografico, categoria);
        this.cargo = cargo;

    }
}
