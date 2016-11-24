package com.company;

import java.util.ArrayList;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class Disciplina {
    private String nome;
    private FuncionarioDocente responsavel;
    private ArrayList<FuncionarioDocente> outrosDocentes;
    private ArrayList<Aluno> alunosInscritos;

    //CONSTRUCTOR
    public Disciplina(String nome, FuncionarioDocente responsavel, ArrayList<FuncionarioDocente> outrosDocentes, ArrayList<Aluno> alunosInscritos) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.outrosDocentes = outrosDocentes;
        this.alunosInscritos = alunosInscritos;
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
