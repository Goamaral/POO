package com.company;

import java.util.ArrayList;

public class Disciplina implements java.io.Serializable {
	private static final long serialVersionUID = 3L;
    private String nome;
    private FuncionarioDocente responsavel;
    private ArrayList<FuncionarioDocente> outrosDocentes;
    private ArrayList<Aluno> alunosInscritos = new ArrayList<>();

    public ArrayList<StringBuilder> toStringBuilder() {
        StringBuilder base = new StringBuilder(this.getNome() + "»" + this.getResponsavel().getNumMecanografico());
        StringBuilder base2 = new StringBuilder();
        ArrayList<StringBuilder> out = new ArrayList<>();

        for(FuncionarioDocente funcionarioDocente : this.getOutrosDocentes()) {
            base2.append(funcionarioDocente.getNome() + "»" + funcionarioDocente.getNumMecanografico() + "&&");
        }

        for(Aluno aluno : this.getAlunosInscritos()) {
            base2.append(aluno.getNome() + "»" + aluno.getNumAluno() + "&&");
        }

        out.add(base);
        out.add(base2);

        return out;
    }

    public String toStringDetailed() {
        return getNome() + " responsavel: " + responsavel.getNome();
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
