package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IntervaloTempo {
    
    private Calendar inicio;
    private Calendar fim;
    private int duracao;

    public String stringify(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return formatter.format(calendar.getTime());
    }

    public String toString() {
        return duracao + " " + stringify(inicio);
    }

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
