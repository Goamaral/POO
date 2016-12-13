package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestor {
    //TODO -> protect inputs
    private static ArrayList<Exame> exames;
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Curso> cursos;
    private ArrayList<Sala> salas;
    private ArrayList<String> historico;
    private String importFileName;
    private String exportFileName;
    private static String[] menuPrincipal = {"Listar", "Criar exames", "Configurar exames", "Lancar notas" , "Sair"};
    private static String[] menuListar = {"Listar exames", "Listar alunos inscritos num exame", "Listar exames de um aluno", "Listar funcionarios de um exame", "Listar exames de um funcionario", "Listar notas"};

    public static void main(String[] args) {
        while(true) menu();
    }

    public static void listarExames() {
        //Imprimir exames
        for (Exame exame : exames) {
            System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s",
                    exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                    exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
        }
    }

    public static Exame escolhaListaExames() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);

        //Imprimir exames
        for (Exame exame : exames) {
            System.out.printf("[%d] %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    i, exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                    exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
            ++i;
        }

        System.out.print("Opcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return exames.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    public static void listarAlunosInscritosExame(Exame exame) {
        for (InscritoExame inscritoExame : exame.getResultados()) {
            System.out.println(inscritoExame.getAluno().getNumAluno() +
                    " " + inscritoExame.getAluno().getNome() +
                    " ano: " + inscritoExame.getAluno().getAno() +
                    " curso: " + inscritoExame.getAluno().getCurso().getNome() +
                    " regime: " + inscritoExame.getAluno().getRegime() +
                    " email: " + inscritoExame.getAluno().getEmail());
        }
    }

    public static ArrayList<Aluno> getAlunos() {
        ArrayList<Aluno> out = new ArrayList<>();

        for(Exame exame : exames) {
            for(InscritoExame inscritoExame : exame.getResultados()) {
                if(!out.contains(inscritoExame.getAluno())) {
                    out.add(inscritoExame.getAluno());
                }
            }
        }

        return out;
    }

    public static Aluno escolhaListaAlunos() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> alunos = getAlunos();

        for(Aluno aluno : alunos) {
            System.out.println(aluno.getNumAluno() +
                    " " + aluno.getNome() +
                    " ano: " + aluno.getAno() +
                    " curso: " + aluno.getCurso().getNome() +
                    " regime: " + aluno.getRegime());
        }

        System.out.print("Opcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return alunos.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    public static void listarExamesAluno(Aluno aluno) {
        for(Exame exame : exames) {
            for(InscritoExame inscritoExame : exame.getResultados()) {
                if(inscritoExame.getAluno().equals(aluno)) {
                    System.out.printf("%s, %s, %s, %s, %s, %s, %s",
                            exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                            exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
                }
            }
        }
    }

    public static boolean examesDisponiveis() {
        if(exames == null) {
            System.out.println("Nao existem exames no sistema");
            return false;
        }
        return true;
    }

    public static void listarFuncionariosExame(Exame exame) {
        System.out.println("Responsavel:");
        System.out.println(exame.getDocenteResponsavel().getNumMecanografico() + " "
                + exame.getDocenteResponsavel().getNome() + " "
                + exame.getDocenteResponsavel().getCategoria() + " "
                + exame.getDocenteResponsavel().getAreaDeInvestigacao() + " "
                + exame.getDocenteResponsavel().getEmail());

        System.out.println("Vigilantes:");
        for(FuncionarioDocente funcionarioDocente: exame.getVigilantes()) {
            System.out.println(funcionarioDocente.getNumMecanografico() + " "
                    + funcionarioDocente.getNome() + " "
                    + funcionarioDocente.getCategoria() + " "
                    + funcionarioDocente.getAreaDeInvestigacao() + " "
                    + funcionarioDocente.getEmail());
        }

        System.out.println("Assistentes:");
        for(FuncionarioNaoDocente funcionarioNaoDocente: exame.getAssistentes()) {
            System.out.println(funcionarioNaoDocente.getNumMecanografico() + " "
                    + funcionarioNaoDocente.getNome() + " "
                    + funcionarioNaoDocente.getCategoria() + " "
                    + funcionarioNaoDocente.getCargo() + " "
                    + funcionarioNaoDocente.getEmail());
        }
    }

    public static ArrayList<Funcionario> getFuncionarios() {
        ArrayList<Funcionario> out = new ArrayList<>();

        for(Exame exame : exames) {
            for(Funcionario funcionario : exame.getAssistentes()) {
                if(!out.contains(funcionario)) {
                    out.add(funcionario);
                }
            }

            for(Funcionario funcionario : exame.getVigilantes()) {
                if(!out.contains(funcionario)) {
                    out.add(funcionario);
                }
            }
        }

        return out;
    }

    public static Funcionario escolhaListaFuncionarios() {
        int i = 0;
        int opcaoInteger;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Funcionario> funcionarios = getFuncionarios();

        for(Funcionario funcionario : funcionarios) {
            if(funcionario instanceof FuncionarioDocente) {
                System.out.println("[" + i + "] " + funcionario.getNumMecanografico() + " "
                        + funcionario.getNome() + " "
                        + funcionario.getCategoria() + " "
                        + ((FuncionarioDocente)funcionario).getAreaDeInvestigacao() + " "
                        + funcionario.getEmail());
            }

            if(funcionario instanceof FuncionarioNaoDocente) {
                System.out.println("[" + i + "] " + funcionario.getNumMecanografico() + " "
                        + funcionario.getNome() + " "
                        + funcionario.getCategoria() + " "
                        + ((FuncionarioNaoDocente)funcionario).getCargo() + " "
                        + funcionario.getEmail());
            }
            ++i;
        }

        System.out.print("Opcao: ");
        try {
            opcaoInteger = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }

        try {
            return funcionarios.get(opcaoInteger);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Insira uma opcao valida");
            return null;
        }
    }

    public static void listarExamesFuncionario(Funcionario funcionario) {
        for(Exame exame : exames) {
            if(funcionario instanceof FuncionarioDocente) {
                if (exame.getDocenteResponsavel().equals(funcionario)) {
                    System.out.printf("%s, %s, %s, %s, %s, %s, %s",
                            exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                            exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
                }

                if (exame.getVigilantes().contains(funcionario)) {
                    System.out.printf("%s, %s, %s, %s, %s, %s, %s",
                            exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                            exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
                }
            }

            if(funcionario instanceof FuncionarioNaoDocente) {
                if (exame.getAssistentes().contains(funcionario)) {
                    System.out.printf("%s, %s, %s, %s, %s, %s, %s",
                            exame.getEpoca(), exame.getDisciplina().getNome(), exame.getData().getInicio(), exame.getData().getDuracao(),
                            exame.getSala().getId(), exame.getVigilantes().size(), exame.getResultados().size());
                }
            }
        }
    }

    private static void listarNotasExame(Exame exame) {
        for(InscritoExame inscritoExame : exame.getResultados()) {
            System.out.print(inscritoExame.getAluno().getNumAluno() +
                    " " + inscritoExame.getAluno().getNome() +
                    " ano: " + inscritoExame.getAluno().getAno() +
                    " curso: " + inscritoExame.getAluno().getCurso().getNome() +
                    " regime: " + inscritoExame.getAluno().getRegime() +
                    " email: " + inscritoExame.getAluno().getEmail());
            if(Double.parseDouble(inscritoExame.getNota()) == 0) {
                System.out.print(" Nota: ainda nao foi lançada\n");
            } else {
                System.out.print(" Nota: " + inscritoExame.getNota() + "\n");
            }
        }
    }

    public void guardarDados() {

    }

    public void guardarHistorico() {

    }

    public void carregarDados() {

    }

    public static void sair() {
        System.exit(0);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        Aluno auxAluno;
        Exame auxExame;
        Funcionario auxFuncionario;
        int auxInt;

        System.out.println("--- Gestor de Exames ---" + "\n");
        printMenu(menuPrincipal);
        System.out.print("\nOpcao: ");

        try {
            auxInt = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.printf("Opcao invalida");
            return;
        }

        switch (auxInt) {
            //Listar
            case 0:
                System.out.println("\n--- Listar ---\n");
                printMenu(menuListar);
                System.out.println("[6] Menu principal");
                System.out.print("\nOpcao: ");

                try {
                    auxInt = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.printf("Opcao invalida");
                    return;
                }

                switch (auxInt) {
                    //Listar exames
                    case 0:
                        System.out.println("\n--- Listar Exames ---");
                        if(examesDisponiveis()){
                            listarExames();
                        }
                        break;
                        
                    //Listar alunos inscritos num exame
                    case 1:
                        System.out.println("\n--- Listar alunos inscritos num exame ---");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();

                            if(auxExame != null) {
                                listarAlunosInscritosExame(auxExame);
                            }
                        }

                        break;
                        
                    //Listar exames de um aluno
                    case 2:
                        System.out.println("\n--- Listar exames de um aluno ---");

                        if(examesDisponiveis()) {
                            auxAluno = escolhaListaAlunos();

                            if(auxAluno != null) {
                                listarExamesAluno(auxAluno);
                            }
                        }

                        break;

                    //Listar funcionarios de um exame
                    case 3:
                        System.out.println("---Listar funcionarios de um exame---");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();

                            if(auxExame != null) {
                                listarFuncionariosExame(auxExame);
                            }
                        }

                        break;
                    
                    case 4:
                        System.out.println("---Listar exames de um funcionario---");

                        if(examesDisponiveis()) {
                            auxFuncionario = escolhaListaFuncionarios();

                            if(auxFuncionario != null) {
                                listarExamesFuncionario(auxFuncionario);
                            }
                        }

                        break;
                        
                    case 5:
                        System.out.println("---Listar notas---");

                        if(examesDisponiveis()) {
                            auxExame = escolhaListaExames();
                            if(auxExame != null) {
                                listarNotasExame(auxExame);
                            }
                        }

                        break;

                    case 6:
                        break;

                    default:
                        System.out.println("Opcao invalida");
                        break;
                }
                break;
            //Criar Exames
            case 1:
                break;
            //Sair
            case 4:
                sair();
                break;
            //Opcao invalida
            default:
                System.out.println("Opcao invalida");
                break;
        }
    }

    public static void printMenu(String[] menu) {
        for(int i=0; i < menu.length; ++i) {
            System.out.println("[" + i + "] " + menu[i]);
        }
    }
}
