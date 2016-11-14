package com.company;

import java.util.ArrayList;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class FuncionarioDocente extends Funcionario {
    private String areaDeInvestigacao;
    private ArrayList<IntervaloTempo> docenteOcupado;

    public boolean verificarDisponibilidade(IntervaloTempo intervalo) {
        return true;
    }

    public void preencherHorario(IntervaloTempo intervalo) {

    }

    public FuncionarioDocente(String nome, String email, int numMecanografico, String categoria, String areaDeInvestigacao, ArrayList<IntervaloTempo> docenteOcupado) {
        super(nome, email, numMecanografico, categoria);
        this.areaDeInvestigacao = areaDeInvestigacao;
        this.docenteOcupado = docenteOcupado;
    }

    public FuncionarioDocente(String nome, String email, int numMecanografico, String categoria, String areaDeInvestigacao) {
        super(nome, email, numMecanografico, categoria);
        this.areaDeInvestigacao = areaDeInvestigacao;
    }

    public String getAreaDeInvestigacao() {
        return areaDeInvestigacao;
    }

    public void setAreaDeInvestigacao(String areaDeInvestigacao) {
        this.areaDeInvestigacao = areaDeInvestigacao;
    }

    public ArrayList<IntervaloTempo> getDocenteOcupado() {
        return docenteOcupado;
    }

    public void setDocenteOcupado(ArrayList<IntervaloTempo> docenteOcupado) {
        this.docenteOcupado = docenteOcupado;
    }
}
