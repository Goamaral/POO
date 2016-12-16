package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public abstract class Exame implements java.io.Serializable {
	private static final long serialVersionUID = 4L;
    private Disciplina disciplina;
    private Sala sala;
    private IntervaloTempo data;
    private FuncionarioDocente docenteResponsavel;
    private ArrayList<FuncionarioDocente> vigilantes = new ArrayList<>();
    private ArrayList<FuncionarioNaoDocente> assistentes = new ArrayList<>();
    private ArrayList<InscritoExame> resultados = new ArrayList<>();
	private boolean notasLancadas = false;

    //PUBLIC METHODS
    public void listarNotas() {
        if(this.getResultados().size() == 0) {
            System.out.println("Nao existem alunos inscritos");
        }

        for(InscritoExame inscritoExame : this.getResultados()) {
            System.out.print(inscritoExame.getAluno().getNumAluno() + " " + inscritoExame.getAluno().getNome());
            System.out.print(" Nota: " + inscritoExame.getNota() + "\n");
        }
    }

    public String toString() {
        return this.getEpoca() +
                " " + this.getDisciplina().getNome() +
                " " + this.getData().toString() +
                " " + this.getSala().getId() +
                " " + this.getVigilantes().size() + " vigilantes " +
                " " + this.getResultados().size() + " alunos inscritos";
    }

    //TEST
    public boolean verificarAlunoInscritoDisciplina(Aluno aluno, Disciplina disciplina) {
        //Verificar se aluno esta inscrito
        return disciplina.getAlunosInscritos().contains(aluno);
    }

    //TEST
    public void lancarNotas() {
        if (!this.notasLancadas) {

            Random r = new Random();

            if (vigilantes.size() == 0) {
                System.out.println("Nao existem vigilantes associados a este exame");
                return;
            }

            if (assistentes.size() == 0) {
                System.out.println("Nao existem assistentes associados a este exame");
                return;
            }

            if (resultados.size() == 0) {
                System.out.println("Nao existem alunos inscritos neste exame");
                return;
            }

            for (InscritoExame inscrito : resultados) {
                inscrito.setNota((int) (20 * r.nextDouble()));
            }

            notasLancadas = true;

            System.out.println("Notas lancadas");
        } else {
            System.out.println("Notas ja lancadas");
        }
    }

    //TEST
    public void listarFuncionarios() {
        System.out.println("Responsavel:");
        System.out.println("\t" + this.getDocenteResponsavel().toString());

        System.out.println("");

        System.out.println("Vigilantes:");

        if(this.getVigilantes().size() == 0) {
            System.out.println("\tNao foram associados vigilantes");
        }

        for(FuncionarioDocente funcionarioDocente: this.getVigilantes()) {
            System.out.println("\t" + funcionarioDocente.toString());
        }

        System.out.println("");

        System.out.println("Assistentes:");

        if(this.getAssistentes().size() == 0) {
            System.out.println("\tNao foram associados assistentes");
        }

        for(FuncionarioNaoDocente funcionarioNaoDocente: this.getAssistentes()) {
            System.out.println("\t" + funcionarioNaoDocente.toString());
        }

        System.out.println("");
    }

    //TEST
    public void inscreverAluno(Aluno aluno) {
        InscritoExame inscrito = new InscritoExame(aluno);
        Scanner sc = new Scanner(System.in);
        String opcao;
        boolean repeat;

        if( !verificarAlunoInscritoDisciplina(aluno, this.disciplina)) {
            System.out.println("O aluno nao esta inscrito a disciplina");

            do {
                repeat = false;
                System.out.println("Pretende que o aluno se inscreva? (s/n)");
                System.out.print("\nOpcao: ");

                opcao = sc.nextLine();

                if(opcao.length() != 1) {
                    System.out.println("Opcao invalida");
                    repeat = true;
                }

                if(!repeat) {
                    if(!opcao.equals("s") && !opcao.equals("n")) {
                        System.out.println("Opcao invalida");
                        repeat = true;
                    }
                }
            } while(repeat);

            if(opcao.equals("s")) {
                this.getDisciplina().inscreverAluno(aluno);
                System.out.println("Aluno adicionado à disciplina");
            } else {
                return;
            }
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

    public void listarAlunosInscritos(){
        if(this.getResultados().size() == 0) {
            System.out.println("Nao existem alunos inscritos");
        }

        for (InscritoExame inscritoExame : this.getResultados()) {
            System.out.println(inscritoExame.getAluno().getNumAluno() +
                    " " + inscritoExame.getAluno().getNome() +
                    " ano: " + inscritoExame.getAluno().getAno() +
                    " curso: " + inscritoExame.getAluno().getCurso().getNome() +
                    " regime: " + inscritoExame.getAluno().getRegime() +
                    " email: " + inscritoExame.getAluno().getEmail());
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
        sala.inserirIntervalo(intervaloTempo.getInicio(), intervaloTempo.getDuracao());
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
