package com.company;

public class ExameNormalRecurso extends Exame implements java.io.Serializable {
	private static final long serialVersionUID = 6L;
    private String epoca;

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

    public boolean verificarAcessoExame(Aluno aluno) {
        return true;
    }

    public String getEpoca() {
        return this.epoca;
    }

    public ExameNormalRecurso(Disciplina disciplina, Sala sala, FuncionarioDocente docenteResponsavel, IntervaloTempo intervaloTempo, String epoca) {
        super(disciplina, sala, docenteResponsavel, intervaloTempo);
        this.epoca = epoca;
    }
}
