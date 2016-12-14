package com.company;

/**
 * Created by the-unicorn on 10-11-2016.
 */
public class ExameNormalRecurso extends Exame {
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
