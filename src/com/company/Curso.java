package com.company;

import java.util.ArrayList;

public class Curso {
    private String nome;
    private int duracao;
    private String grauConfere;
    private ArrayList<Disciplina> disciplinas;

    //CONSTRUCTOR
    public Curso(String nome, int duracao, String grauConfere, ArrayList<Disciplina> disciplinas) {
        this.nome = nome;
        this.duracao = duracao;
        this.grauConfere = grauConfere;
        this.disciplinas = disciplinas;
    }

    //GETS
    public String getNome() { return nome; }
    public int getDuracao() {
        return duracao;
    }
    public String getGrauConfere() {
        return grauConfere;
    }
    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    //SETS
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public void setGrauConfere(String grauConfere) {
        this.grauConfere = grauConfere;
    }
    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
