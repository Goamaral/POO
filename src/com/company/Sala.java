package com.company;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class Sala {
    private ArrayList<IntervaloTempo> horasOcupadas;
    private String id;

    public boolean verificarDisponibilidade(IntervaloTempo intervalo) {
        return true;
    }

    public void inserirIntervalo(Date inicio, int duracao) {

    }

    public Sala(ArrayList<IntervaloTempo> horasOcupadas, String id) {
        this.horasOcupadas = horasOcupadas;
        this.id = id;
    }
}
