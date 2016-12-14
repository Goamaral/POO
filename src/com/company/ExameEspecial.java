package com.company;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class ExameEspecial extends Exame {
    public ExameEspecial(Disciplina disciplina, Sala sala, FuncionarioDocente docenteResponsavel, IntervaloTempo intervaloTempo) {
        super(disciplina, sala, docenteResponsavel, intervaloTempo);
    }

    public boolean verificarAcessoExame(Aluno aluno) {
        if( aluno.getRegime().equals("Especial") ) return true;
        else return false;
    }

    public String getEpoca() {
        return "Especial";
    }
}
