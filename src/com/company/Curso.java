package com.company;

import java.util.ArrayList;

public class Curso {
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

    public String toStringDetailed() {
        return this.getNome() + " " + getGrauConfere() + " " + this.getDuracao() + " anos";
    }

    public String toString() {
        StringBuilder out = new StringBuilder(getNome() + "»" + getDuracao() + "»" + getGrauConfere() + "\n");
        ArrayList<StringBuilder> cap;
        String[] aux;

        for(Disciplina disciplina : disciplinas) {
            cap = disciplina.toStringBuilder();
            out.append("\t" + cap.get(0).toString() + "\n");
            aux = cap.get(1).toString().split("&&");
            for(String string : aux) {
                out.append("\t\t" + string + "\n");
            }
        }

        return  out.toString();
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
