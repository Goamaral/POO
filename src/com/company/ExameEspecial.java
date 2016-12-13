package com.company;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class ExameEspecial extends Exame {
    public boolean verificarAcessoExame(Aluno aluno) {
        if( aluno.getRegime().equals("Especial") ) return true;
        else return false;
    }
}
