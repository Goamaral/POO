package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IntervaloTempo implements java.io.Serializable {
	private static final long serialVersionUID = 11L;
    private Calendar inicio;
    private Calendar fim;
    private int duracao;

    public String stringify(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return formatter.format(calendar.getTime());
    }

    public String toString() {
        return duracao + " minutos " + stringify(inicio);
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
        fim.add(Calendar.MINUTE, duracao);
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
