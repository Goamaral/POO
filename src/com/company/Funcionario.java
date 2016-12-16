package com.company;

import java.util.ArrayList;

public abstract class Funcionario extends Pessoa implements java.io.Serializable {
	private static final long serialVersionUID = 7L;
    private int numMecanografico;
    private String categoria;

    //PUBLIC METHODS
    public abstract String toString();

    public void listarExames(ArrayList<Exame> exames) {
        for(Exame exame : exames) {
            if (exame.getDocenteResponsavel().equals(this) ||
                    exame.getVigilantes().contains(this) ||
                    exame.getAssistentes().contains(this))
            {
                System.out.println(exame.toString());
            }
        }
    }

    //CONSTRUCTOR
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
}
