package com.company;

import java.util.ArrayList;

public class FuncionarioDocente extends Funcionario {
    private String areaDeInvestigacao;
    private ArrayList<IntervaloTempo> docenteOcupado = new ArrayList<>();

    public String toStringDetailed() {
        return this.getNumMecanografico() +
                " " + this.getNome() +
                " Categoria: " + this.getCategoria() +
                " Area: " + this.getAreaDeInvestigacao() +
                " Email: " + this.getEmail();
    }

    public String toString() {
        StringBuilder out = new StringBuilder(super.getNome() +
                "|" + super.getEmail() +
                "|" + super.getNumMecanografico() +
                "|" + super.getCategoria() +
                "|" + this.getAreaDeInvestigacao() + "\n");

        for(IntervaloTempo intervaloTempo : this.getDocenteOcupado()) {
            out.append("\t" + intervaloTempo.toString() + "\n");
        }

        return out.toString();
    }

    public String toStringBasic() {
        StringBuilder out = new StringBuilder("\t\t" + super.getNome() + "|"
                + super.getNumMecanografico() +
                "|" + this.getAreaDeInvestigacao()+ "\n");

        for(IntervaloTempo intervaloTempo : this.getDocenteOcupado()) {
            out.append("\t\t\t" + intervaloTempo.toString() + "\n");
        }

        return out.toString();
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
    //DUMB CONSTRUCTOR
    public FuncionarioDocente(String nome, String email, int numMecanografico, String categoria, String areaDeInvestigacao, ArrayList<IntervaloTempo> docenteOcupado) {
        super(nome, email, numMecanografico, categoria);
        this.setAreaDeInvestigacao(areaDeInvestigacao);
        this.setDocenteOcupado(docenteOcupado);
    }
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

    public void setDocenteOcupado(ArrayList<IntervaloTempo> docenteOcupado) {
        this.docenteOcupado = docenteOcupado;
    }
}
