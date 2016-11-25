package com.company;

import java.util.ArrayList;

/**
 * Created by the-unicorn on 10-11-2016.
 */

public class Funcionario extends Pessoa {
    private int numMecanografico;
    private String categoria;

    public void listarExames(ArrayList<Exame> exames) {
        for(Exame exame : exames){
            System.out.println("Disciplina: " + exame.getDisciplina().getNome() + "\nData: " + exame.getData().getInicio().toString() + "\nSala: " + exame.getSala().getId() + "\n");
        }
    }

    public Funcionario() {}

    public Funcionario(String nome, String email, int numMecanografico, String categoria) {
        super(nome, email);
        this.numMecanografico = numMecanografico;
        this.categoria = categoria;
    }

    public int getNumMecanografico() {
        return numMecanografico;
    }

    public void setNumMecanografico(int numMecanografico) {
        this.numMecanografico = numMecanografico;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

