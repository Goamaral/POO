package com.company;

import java.util.ArrayList;
import java.util.Calendar;

public class Sala implements java.io.Serializable {
	private static final long serialVersionUID = 13L;
    private ArrayList<IntervaloTempo> horasOcupadas;
    private String id;

    public String toString() {
        StringBuilder out = new StringBuilder(id + "\n");

        for (IntervaloTempo intervaloTempo : horasOcupadas) {
            out.append("\t" + intervaloTempo.toString());
        }

        return out.toString();
    }

    public String toStringDetailed() {
        return getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //TEST
    public boolean inserirIntervalo(Calendar inicio, int duracao) {
        IntervaloTempo intervalo = new IntervaloTempo(inicio, duracao);
        IntervaloTempo auxNext;
        int i = 0;

        for (IntervaloTempo aux : horasOcupadas) {
            //Inserir no inicio
            if (i == 0) {
                //Se o inicio de aux for depois do fim de intervalo
                if (aux.getInicio().compareTo(intervalo.getFim()) >= 0) {
                    //Adicionar intervalo ao inicio da fila
                    horasOcupadas.add(0, intervalo);
                    return true;
                }
            }

            //Inserir no meio
            //Se o fim de aux for antes do inicio de intervalo
            if (aux.getFim().compareTo(intervalo.getInicio()) <= 0) {
                //Inserir no fim
                if (horasOcupadas.size() == i + 1) {
                    //Adicionar no fim da lista
                    horasOcupadas.add(i + 1, intervalo);
                    return true;
                }
                auxNext = horasOcupadas.get(i + 1);
                //Se o inicio de aux + 1 for maior que o fim de intervalo
                if (auxNext.getInicio().compareTo(intervalo.getFim()) >= 0) {
                    horasOcupadas.add(i + 1, intervalo);
                    return true;
                }
            }

            ++i;
        }
        return false;
    }

    public Sala(ArrayList<IntervaloTempo> horasOcupadas, String id) {
        this.horasOcupadas = horasOcupadas;
        this.id = id;
    }

    public Sala(String id) {
        this.id = id;
        this.horasOcupadas = new ArrayList<>();
    }
}
