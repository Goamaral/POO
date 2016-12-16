package com.company;

import java.util.ArrayList;

public class Curso implements java.io.Serializable {
	private static final long serialVersionUID = 2L;
    private String nome = "Nao existente";
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

    public Curso() {
    }

    public String toString() {
        return this.getNome() + " " + this.getGrauConfere() + " " + this.getDuracao() + " anos";
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
}
