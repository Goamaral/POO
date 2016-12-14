package com.company;

import java.util.ArrayList;

public abstract class Funcionario extends Pessoa {
    private int numMecanografico;
    private String categoria;

    //PUBLIC METHODS
    public abstract String toStringBasic();
    public abstract String toString();

    public void listarExames(ArrayList<Exame> exames) {
        for(Exame exame : exames){
            System.out.println("Disciplina: " + exame.getDisciplina().getNome() + "\nData: " + exame.getData().getInicio().toString() + "\nSala: " + exame.getSala().getId() + "\n");
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

