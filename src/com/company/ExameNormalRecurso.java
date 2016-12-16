package com.company;

public class ExameNormalRecurso extends Exame implements java.io.Serializable {
	private static final long serialVersionUID = 6L;
    private String epoca;

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
