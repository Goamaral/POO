package com.company;

import java.util.ArrayList;

public class FuncionarioDocente extends Funcionario implements java.io.Serializable {
	private static final long serialVersionUID = 8L;
    private String areaDeInvestigacao;
    private ArrayList<IntervaloTempo> docenteOcupado = new ArrayList<>();

    public String toString() {
        return this.getNumMecanografico() +
                " " + this.getNome() +
                " Categoria: " + this.getCategoria() +
                " Area: " + this.getAreaDeInvestigacao() +
                " Email: " + this.getEmail();
    }

    //PRIVATE METHODS
    //PUBLIC METHODS
    public boolean preencherHorario(IntervaloTempo intervalo) {
        IntervaloTempo auxNext;
        int i=0;

        if(this.getDocenteOcupado().size()==0) {
            this.getDocenteOcupado().add(intervalo);
            return true;
        }

        for(IntervaloTempo aux : this.getDocenteOcupado()) {
            //Inserir no inicio
            if(i==0) {
                //Se o inicio de aux for depois do fim de intervalo
                if(aux.getInicio().compareTo(intervalo.getFim()) >= 0) {
                    //Adicionar intervalo ao inicio da fila
                    this.getDocenteOcupado().add(0, intervalo);
                    return true;
                }
            }

            //Inserir no meio
            //Se o fim de aux for antes do inicio de intervalo
            if(aux.getFim().compareTo(intervalo.getInicio()) <= 0) {
                //Inserir no fim
                if(this.getDocenteOcupado().size() == i+1) {
                    //Adicionar no fim da lista
                    this.getDocenteOcupado().add(i+1, intervalo);
                    return true;
                }
                auxNext = this.getDocenteOcupado().get(i+1);
                //Se o inicio de aux + 1 for maior que o fim de intervalo
                if(auxNext.getInicio().compareTo(intervalo.getFim()) >= 0) {
                    this.getDocenteOcupado().add(i+1, intervalo);
                    return true;
                }
            }

            ++i;
        }
        return false;
    }


    //CONSTRUCTOR
    //NORMAL CONSTRUCTOR
    public FuncionarioDocente(String nome, String email, int numMecanografico, String categoria, String areaDeInvestigacao) {
        super(nome, email, numMecanografico, categoria);
        this.setAreaDeInvestigacao(areaDeInvestigacao);
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
}
