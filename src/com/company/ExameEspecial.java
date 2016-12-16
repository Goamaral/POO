package com.company;

public class ExameEspecial extends Exame implements java.io.Serializable {
	private static final long serialVersionUID = 5L;

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
