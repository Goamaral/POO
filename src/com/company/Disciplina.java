package com.company;

import java.util.ArrayList;

public class Disciplina implements java.io.Serializable {
	private static final long serialVersionUID = 3L;
    private String nome;
    private FuncionarioDocente responsavel;
    private ArrayList<FuncionarioDocente> outrosDocentes;
    private ArrayList<Aluno> alunosInscritos = new ArrayList<>();

    public String toString() {
        return this.getNome() + " responsavel: " + this.getResponsavel().getNome();
    }

    //CONSTRUCTOR
    public Disciplina(String nome, FuncionarioDocente responsavel, ArrayList<FuncionarioDocente> outrosDocentes) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.outrosDocentes = outrosDocentes;
    }

    public boolean inscreverAluno(Aluno aluno) {
        if(!getAlunosInscritos().contains(aluno)) {
            getAlunosInscritos().add(aluno);
            return true;
        }
        return false;
    }

    //GETS
    public String getNome() {
        return nome;
    }
    public FuncionarioDocente getResponsavel() {
        return responsavel;
    }
    public ArrayList<FuncionarioDocente> getOutrosDocentes() {
        return outrosDocentes;
    }
    public ArrayList<Aluno> getAlunosInscritos() {
        return alunosInscritos;
    }
}
