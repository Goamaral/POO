package com.company;

import java.util.ArrayList;

public class FuncionarioDocente extends Funcionario {
    private String areaDeInvestigacao;
    private ArrayList<IntervaloTempo> docenteOcupado;

    //PRIVATE METHODS
    //PUBLIC METHODS
    //TEST
    public boolean preencherHorario(IntervaloTempo intervalo) {
        IntervaloTempo auxNext;
        int i=0;

        for(IntervaloTempo aux : docenteOcupado) {
            //Inserir no inicio
            if(i==0) {
                //Se o inicio de aux for depois do fim de intervalo
                if(aux.getInicio().compareTo(intervalo.getFim()) >= 0) {
                    //Adicionar intervalo ao inicio da fila
                    docenteOcupado.add(0, intervalo);
                    return true;
                }
            }

            //Inserir no meio
            //Se o fim de aux for antes do inicio de intervalo
            if(aux.getFim().compareTo(intervalo.getInicio()) <= 0) {
                //Inserir no fim
                if(docenteOcupado.size() == i+1) {
                    //Adicionar no fim da lista
                    docenteOcupado.add(i+1, intervalo);
                    return true;
                }
                auxNext = docenteOcupado.get(i+1);
                //Se o inicio de aux + 1 for maior que o fim de intervalo
                if(auxNext.getInicio().compareTo(intervalo.getFim()) >= 0) {
                    docenteOcupado.add(i+1, intervalo);
                    return true;
                }
            }

            ++i;
        }
        return false;
    }


    //CONSTRUCTOR
    //DUMB CONSTRUCTOR
    public FuncionarioDocente(String nome, String email, int numMecanografico, String categoria, String areaDeInvestigacao, ArrayList<IntervaloTempo> docenteOcupado) {
        super(nome, email, numMecanografico, categoria);
        this.areaDeInvestigacao = areaDeInvestigacao;
        this.docenteOcupado = docenteOcupado;
    }
    //NORMAL CONSTRUCTOR
    public FuncionarioDocente(String nome, String email, int numMecanografico, String categoria, String areaDeInvestigacao) {
        super(nome, email, numMecanografico, categoria);
        this.areaDeInvestigacao = areaDeInvestigacao;
    }

    //GETS
    public String getAreaDeInvestigacao() {
        return areaDeInvestigacao;
    }

    //SETS
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
