package com.company;

import java.util.ArrayList;

public abstract class Funcionario extends Pessoa {
    private int numMecanografico;
    private String categoria;

    //PUBLIC METHODS
    public abstract String toStringBasic();
    public abstract String toString();
    public abstract String toStringDetailed();

    public void listarExames(ArrayList<Exame> exames) {
        for(Exame exame : exames) {
            if (exame.getDocenteResponsavel().equals(this) ||
                    exame.getVigilantes().contains(this) ||
                    exame.getAssistentes().contains(this))
            {
                System.out.println(exame.toStringDetailed());
            }
        }
    }

    //CONSTRUCTOR
    //EMPTY CONSTRUCTOR
    public Funcionario() {}
    //NORMAL CONSTRUCTOR
    public Funcionario(String nome, String email, int numMecanografico, String categoria) {
        super(nome, email);
        this.numMecanografico = numMecanografico;
        this.categoria = categoria;
    }

    //GETS
    public int getNumMecanografico() {
        return numMecanografico;
    }
    public String getCategoria() {
        return categoria;
    }

    //SETS
    public void setNumMecanografico(int numMecanografico) {
        this.numMecanografico = numMecanografico;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

