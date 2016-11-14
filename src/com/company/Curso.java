package com.company;

import java.util.ArrayList;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class Curso {
    private String nome;
    private int duracao;
    private String grauConfere;
    private ArrayList<Disciplina> disciplinas;

    public Curso(String nome, int duracao, String grauConfere, ArrayList<Disciplina> disciplinas) {
        this.nome = nome;
        this.duracao = duracao;
        this.grauConfere = grauConfere;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getGrauConfere() {
        return grauConfere;
    }

    public void setGrauConfere(String grauConfere) {
        this.grauConfere = grauConfere;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
