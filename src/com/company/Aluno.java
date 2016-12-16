package com.company;

import java.util.ArrayList;


public class Aluno extends Pessoa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    private int numAluno;
    private Curso curso;
    private int ano;
    private String regime;

    public String toString() {
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
                    System.out.println(exame.toString());
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
}
