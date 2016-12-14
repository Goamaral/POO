package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public abstract class Exame {
    private Disciplina disciplina;
    private Sala sala;
    private IntervaloTempo data;
    private FuncionarioDocente docenteResponsavel;
    private ArrayList<FuncionarioDocente> vigilantes;
    private ArrayList<FuncionarioNaoDocente> assistentes;
    private ArrayList<InscritoExame> resultados;

    //PUBLIC METHODS
    public abstract String toString();

    //TEST
    public boolean verificarAlunoInscritoDisciplina(Aluno aluno, Disciplina disciplina) {
        //Verificar se aluno esta inscrito
        if(disciplina.getAlunosInscritos().contains(aluno) ) {
            return true;
        } else return false;
    }

    //TEST
    public void lancarNotas() {
        Random r = new Random();
        double nota;

        if( vigilantes.size()==0 || assistentes.size()==0 ) return;

        for(InscritoExame inscrito : resultados) {
            inscrito.setNota(20 * r.nextDouble());
        }

    }

    //TEST
    public void listarFuncionarios() {
        System.out.println("Docente resposavel: " + docenteResponsavel.getNome());

        System.out.println("\nVigilantes:");
        if( vigilantes.size()!= 0 ) {
            for(Funcionario funcionario : vigilantes) {
                System.out.println(funcionario.getNome());
            }
        } else {
            System.out.println("Nao foram associados vigilantes");
        }

        System.out.println("\nAssistentes:");
        if( assistentes.size()!= 0 ) {
            for(Funcionario funcionario : assistentes) {
                System.out.println(funcionario.getNome());
            }
        } else {
            System.out.println("Nao foram associados assistentes");
        }
    }

    //TEST
    public void listarNota() {
        for(InscritoExame inscritoExame : resultados) {
            System.out.println("Aluno: " + inscritoExame.getAluno() + " Nota: " + inscritoExame.getNota());
        }
    }

    //TEST
    public void inscreverAluno(Aluno aluno) {
        InscritoExame inscrito = new InscritoExame(aluno);

        if( !verificarAlunoInscritoDisciplina(aluno, this.disciplina)) {
            System.out.println("O aluno nao esta inscrito a disciplina");
            return;
        }

        if( verificarAcessoExame(aluno) ) {
            resultados.add(inscrito);
            System.out.println("Aluno inserido com sucesso");
        } else {
            System.out.println("Nao é permitido o aluno se inscrever neste tipo de exame");
        }
    }

    //TEST
    public void inserirDocente(FuncionarioDocente docente) {
        if( vigilantes.contains(docente) || docenteResponsavel.equals(docente) ) {
            System.out.println("Docente é o docente responsavel ou já pertence ao grupo de vigilantes");
        } else {
            if(docente.preencherHorario(data)) {
                vigilantes.add(docente);
            } else {
                System.out.println("Docente nao esta disponivel");
            }
        }
    }

    //TEST
    public void inserirAssistente(FuncionarioNaoDocente funcionario) {
        if( assistentes.contains(funcionario) ) {
            System.out.println("Funcionario ja pertence ao grupo de assistentes");
        } else {
            assistentes.add(funcionario);
        }
    }

    abstract boolean verificarAcessoExame(Aluno aluno);

    //CONSTRUCTOR
    //EMPTY CONSTRUCTOR
    public Exame() {}
    //DUMB CONSTRUCTOR
    public Exame(Disciplina disciplina, IntervaloTempo data, Sala sala, FuncionarioDocente docenteResponsavel, ArrayList<FuncionarioDocente> vigilantes, ArrayList<FuncionarioNaoDocente> assistentes, ArrayList<InscritoExame> resultados) {
        this.disciplina = disciplina;
        this.data = data;
        this.sala = sala;
        this.docenteResponsavel = docenteResponsavel;
        this.vigilantes = vigilantes;
        this.assistentes = assistentes;
        this.resultados = resultados;
    }
    //NORMAL CONSTRUCTOR
    public Exame(Disciplina disciplina, Sala sala, FuncionarioDocente docenteResponsavel, IntervaloTempo intervaloTempo) {
        this.disciplina = disciplina;
        this.sala = sala;
        this.docenteResponsavel = docenteResponsavel;
        this.data = intervaloTempo;
    }

    //GETS
    public Disciplina getDisciplina() { return disciplina; }
    public IntervaloTempo getData() {
        return data;
    }
    public ArrayList<InscritoExame> getResultados() {
        return resultados;
    }
    public Sala getSala() { return sala; }
    public ArrayList<FuncionarioNaoDocente> getAssistentes() {
        return assistentes;
    }

    public FuncionarioDocente getDocenteResponsavel() {
        return docenteResponsavel;
    }

    public ArrayList<FuncionarioDocente> getVigilantes() {

        return vigilantes;
    }

    abstract String getEpoca();

    //SETS
    public boolean setHoras(Calendar inicio, int duracao){
        return sala.inserirIntervalo(inicio, duracao);
    };
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    public void setData(IntervaloTempo data) {
        this.data = data;
    }
    public void setResultados(ArrayList<InscritoExame> resultados) {
        this.resultados = resultados;
    }
}
