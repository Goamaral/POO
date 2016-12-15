package com.company;

import java.util.ArrayList;


public class Aluno extends Pessoa {
    private int numAluno;
    private Curso curso;
    private int ano;
    private String regime;

    public String toString() {
        return super.getNome() + "»" + getEmail() + "»" + numAluno + "»" + ano + "»" + regime + "»" + "A" + "\n";
    }

    public String toStringDetailed() {
        return this.getNumAluno() +
                " " + this.getNome() +
                " ano: " + this.getAno() +
                " curso: " + this.getCurso().getNome() +
                " regime: " + this.getRegime();
    }

    public void listarExames(ArrayList<Exame> exames) {
        for(Exame exame : exames) {
            for(InscritoExame inscritoExame : exame.getResultados()) {
                if(inscritoExame.getAluno().equals(this)) {
                    System.out.printf(exame.toStringDetailed());
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
