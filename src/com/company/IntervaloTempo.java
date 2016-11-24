package com.company;

import java.util.Date;

/**
 * Created by the-unicorn on 10-11-2016.
 */

public class IntervaloTempo {
    
    private Date inicio;
    private Date fim;
    private int duracao;
    
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    private void setFim() {
        /*fim = inicio + duracao*/
    }

    public IntervaloTempo(Date inicio, int duracao) {
        this.inicio = inicio;
        this.duracao = duracao;
        setFim();
    }
}
