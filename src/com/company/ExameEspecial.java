package com.company;

public class ExameEspecial extends Exame implements java.io.Serializable {
	private static final long serialVersionUID = 5L;
    public String toString(){
        StringBuilder out = new StringBuilder(super.getDisciplina().getNome().toString() +
                "»" + super.getData().toString() +
                "»" + super.getSala().getId() +
                "»" + getEpoca() + "\n");

        for(FuncionarioDocente funcionarioDocente : super.getVigilantes()) {
            out.append("\t" + funcionarioDocente.toStringBasic());
        }

        for(FuncionarioNaoDocente funcionarioNaoDocente : super.getAssistentes()) {
            out.append("\t" + funcionarioNaoDocente.toStringBasic());
        }

        for(InscritoExame inscritoExame : super.getResultados()) {
            out.append("\t" + inscritoExame.toString());
        }

        return out.toString();
    }

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
