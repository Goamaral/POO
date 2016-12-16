package com.company;

import java.text.DecimalFormat;

public class InscritoExame implements java.io.Serializable {
	private static final long serialVersionUID = 10L;
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
		DecimalFormat df = new DecimalFormat("#.00");
        this.nota = df.format(nota);
    }

    public InscritoExame(Aluno aluno) {
        this.aluno = aluno;
        this.nota = "Nota nao lançada";
    }
}
