package com.company;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by the-unicorn on 10-11-2016.
 */

public abstract class Exame {
    private Disciplina disciplina;
    private IntervaloTempo data;
    private Sala sala;
    private FuncionarioDocente docenteResponsavel;
    private ArrayList<FuncionarioDocente> vigilantes;
    private ArrayList<FuncionarioNaoDocente> assistentes;
    private ArrayList<InscritoExame> resultados;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public IntervaloTempo getData() {
        return data;
    }

    public void setData(IntervaloTempo data) {
        this.data = data;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public String imprimirInformacaoExame(){
        return "Disciplina: " + disciplina.getNome() + "\nData: " + data.getInicio().toString() + "\nSala: " + sala.getId();
    }
    
    public boolean verificarAlunoInscritoDisciplina(Aluno aluno, Disciplina disciplina) {
         return true;
    }

    public boolean verificarAcessoExame(Aluno aluno) {
        return true;
    }

    public void lancarNotas() {

    }

    public void listarFuncionarios() {

    }

    public void listarNota() {

    }

    public void printErro(String erro) {

    }

    abstract void inserirAluno(Aluno aluno);

    public void inserirDocente(FuncionarioDocente docente) {

    }

    public void inserirFuncionario(FuncionarioNaoDocente funcionario) {

    }

    public Exame() {}

    public Exame(Disciplina disciplina, IntervaloTempo data, Sala sala, FuncionarioDocente docenteResponsavel, ArrayList<FuncionarioDocente> vigilantes, ArrayList<FuncionarioNaoDocente> assistentes, ArrayList<InscritoExame> resultados) {
        this.disciplina = disciplina;
        this.data = data;
        this.sala = sala;
        this.docenteResponsavel = docenteResponsavel;
        this.vigilantes = vigilantes;
        this.assistentes = assistentes;
        this.resultados = resultados;
    }

    public Exame(Disciplina disciplina, Sala sala, FuncionarioDocente docenteResponsavel, Date inicio, int duracao) {
        this.disciplina = disciplina;
        this.sala = sala;
        this.docenteResponsavel = docenteResponsavel;
        this.sala.inserirIntervalo(inicio, duracao);
    }
}
