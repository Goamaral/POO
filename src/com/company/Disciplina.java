package com.company;

import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private FuncionarioDocente responsavel;
    private ArrayList<FuncionarioDocente> outrosDocentes;
    private ArrayList<Aluno> alunosInscritos = new ArrayList<>();

    public String toString() {
        StringBuilder out = new StringBuilder(nome + "|" + responsavel.getNumMecanografico() + "\n");
        for(FuncionarioDocente funcionarioDocente : outrosDocentes) {
            out.append("\t\t" + funcionarioDocente.getNome() + "|" + funcionarioDocente.getNumMecanografico() + "\n");
        }
        for(Aluno aluno : alunosInscritos) {
            out.append("\t\t" + aluno.getNome() + "|" + aluno.getNumAluno() + "\n");
        }

        return out.toString();
    }

    //CONSTRUCTOR
    public Disciplina(String nome, FuncionarioDocente responsavel, ArrayList<FuncionarioDocente> outrosDocentes) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.outrosDocentes = outrosDocentes;
    }

    public boolean inscreverAluno(Aluno aluno) {
        if(!alunosInscritos.contains(aluno)) {
            alunosInscritos.add(aluno);
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

    //SETS
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setResponsavel(FuncionarioDocente responsavel) {
        this.responsavel = responsavel;
    }
    public void setOutrosDocentes(ArrayList<FuncionarioDocente> outrosDocentes) { this.outrosDocentes = outrosDocentes; }
    public void setAlunosInscritos(ArrayList<Aluno> alunosInscritos) {
        this.alunosInscritos = alunosInscritos;
    }
}
