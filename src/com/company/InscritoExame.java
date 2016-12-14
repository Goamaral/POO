package com.company;

public class InscritoExame {
    private Aluno aluno;
    private String nota;

    public String toString() {
        return aluno.getNome() + "|" + aluno.getNumAluno() + "|" + nota;
    }

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
