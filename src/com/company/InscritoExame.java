package com.company;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class InscritoExame {
    private Aluno aluno;
    private String nota;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = Double.toString(nota);
    }

    public InscritoExame(Aluno aluno, double nota) {
        this.aluno = aluno;
        this.nota = Double.toString(nota);
    }

    public InscritoExame(Aluno aluno) {
        this.aluno = aluno;
        this.nota = new String("Nota nao lançada");
    }
}
