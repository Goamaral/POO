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

    //TEST
    public void listarClassificacoes(ArrayList<Exame> exames) {
        for(Exame exame : exames) {
            for(InscritoExame inscrito : exame.getResultados()) {
                if( inscrito.getAluno().equals(this) ) {
                    System.out.println(exame.getData().getInicio() + " Disciplina: " + exame.getDisciplina().getNome() + " Nota: " + inscrito.getNota());
                }
            }
        }
    }

    //CONSTRUCTOR
    public Aluno(String nome, String email, int numAluno, Curso curso, int ano, String regime) {

        super(nome, email);
        this.numAluno = numAluno;
        this.curso = curso;
        this.ano = ano;
        this.regime = regime;
    }

    //GETS
    public int getNumAluno() {
        return numAluno;
    }
    public Curso getCurso() {
        return curso;
    }
    public int getAno() {
        return ano;
    }
    public String getRegime() {
        return regime;
    }

    //SETS
    public void setNumAluno(int numAluno) {
        this.numAluno = numAluno;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setRegime(String regime) {
        this.regime = regime;
    }
}
