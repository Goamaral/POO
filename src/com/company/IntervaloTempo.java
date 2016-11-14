package com.company;

import java.util.Date;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class IntervaloTempo {
    private Date inicio;
    private Date fim;
    private int duracao;

    private void setFim() {
        /*fim = inicio + duracao*/
    }

    public IntervaloTempo(Date inicio, int duracao) {
        this.inicio = inicio;
        this.duracao = duracao;
        setFim();
    }
}
