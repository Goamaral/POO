package com.company;

import java.util.Calendar;

/**
 * Created by the-unicorn on 10-11-2016.
 */

public class IntervaloTempo {
    
    private Calendar inicio;
    private Calendar fim;
    private int duracao;

    //CONSTRUCTOR
    public IntervaloTempo(Calendar inicio, int duracao) {
        this.inicio = inicio;
        this.duracao = duracao;
        setFim();
    }

    //PRIVATE METHODS
    //TEST
    private void setFim() {
        fim = (Calendar)inicio.clone();
        fim.add(inicio.MINUTE, duracao);
    }

    //SETS
    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }
    public void setFim(Calendar fim) {
        this.fim = fim;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    //GETS
    public Calendar getInicio() { return inicio; }
    public Calendar getFim() {
        return fim;
    }
    public int getDuracao() {
        return duracao;
    }
}
