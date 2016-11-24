package com.company;

import java.util.ArrayList;

/**
 * Created by the-unicorn on 10-11-2016.
 */

public class Aluno extends Pessoa {
    private int numAluno;
    private Curso curso;
    private int ano;
    private String regime;

    public void listarClassificacoes(ArrayList<Exame> exames) {
        
    }

    public int getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(int numAluno) {
        this.numAluno = numAluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Aluno(String nome, String email, int numAluno, Curso curso, int ano, String regime) {

        super(nome, email);
        this.numAluno = numAluno;
        this.curso = curso;
        this.ano = ano;
        this.regime = regime;
    }
}
