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
        Exame exame;

        //Verificar se existem exames
        if(exames==null) {
            System.out.println("Nao exitem exames no sistema\n0");
            return;
        }

        //Imprimir exames
        //TEST
        for(int i=0;i<exames.size();++i) {
            exame = exames.get(i);
            System.out.println(exame.getDisciplina() + " às " + exame.getData().getInicio() + " com duração de " + exame.getData().getDuracao() + " na sala " + exame.getSala().getId());
        }
    }

    public static void listarAlunosInscritosExame() {
        Exame exame;
        Scanner scanner = new Scanner(System.in);
        int opcao;
        ArrayList<InscritoExame> inscritoExames;

        //Verificar se existem exames
        if(exames==null) {
            System.out.println("Nao exitem exames no sistema\n");
            return;
        }

        for(int i=0;i<exames.size();++i) {
            exame = exames.get(i);
            System.out.println("[" + i + "] " + exame.getDisciplina() + " às " + exame.getData().getInicio() + " com duração de " + exame.getData().getDuracao() + " na sala " + exame.getSala().getId());
        }

        System.out.print("Opcao: ");
        opcao = scanner.nextInt();

        inscritoExames = exames.get(opcao).getResultados();

        for (InscritoExame inscritoExame : inscritoExames) {
            System.out.println(inscritoExame.getAluno().getNumAluno() +
                    " nome: " + inscritoExame.getAluno().getNome() +
                    " ano: " + inscritoExame.getAluno().getAno() +
                    " curso: " + inscritoExame.getAluno().getCurso().getNome() +
                    " regime: " + inscritoExame.getAluno().getRegime());
        }
    }

    public static ArrayList<Exame> getExamesAlunoPorNome(String[] arrStrings) {
        ArrayList<Exame> out = new ArrayList<>();
        ArrayList<InscritoExame> inscritoExames;
        String nomeAluno;
        String[] nomesAluno;
        //Procurar aluno
        for(Exame exame : exames) {
            //Get inscritos
            inscritoExames = exame.getResultados();
            for(InscritoExame inscritoExame : inscritoExames) {
                //Get nome
                nomeAluno = inscritoExame.getAluno().getNome();
                nomesAluno = nomeAluno.split(" ");
                //comparar primeiro nome e apelido
                if(nomesAluno[0].equals(arrStrings[0]) && nomesAluno[nomesAluno.length-1].equals(arrStrings[1])) {
                    out.add(exame);
                }
            }
        }

        return out;
    }

    public static void listarExamesAluno(ArrayList<Exame> examesAluno) {
        for(Exame exame : examesAluno) {
            System.out.println(exame.getDisciplina() + " às " + exame.getData().getInicio() + " com duração de " + exame.getData().getDuracao() + " na sala " + exame.getSala().getId());
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
        String auxString;
        String[] auxStrings;

        System.out.println("--- Gestor de Exames ---" + "\n");
        printMenu(menuPrincipal);
        System.out.print("\nOpcao: ");
        switch (sc.nextInt()) {
            //Listar
            case 0:
                System.out.println("\n--- Listar ---\n");
                printMenu(menuListar);
                System.out.println("[6] Menu principal");
                System.out.print("\nOpcao: ");
                switch (sc.nextInt()) {
                    
                    //Listar exames
                    case 0:
                        System.out.println("\n--- Listar Exames ---");
                        listarExames();
                        break;
                        
                    //Listar alunos inscritos num exame
                    case 1:
                        System.out.println("\n--- Listar alunos inscritos num exame ---");
                        listarAlunosInscritosExame();
                        break;
                        
                    //Listar exames de um aluno
                    case 2:
                        System.out.println("\n--- Listar exames de um aluno ---");
                        System.out.println("[0] Procurar por primeiro nome e Apelido1");
                        System.out.println("[1] Escolher aluno da lista");
                        System.out.println("[2] Menu principal");
                        System.out.print("\nOpcao: ");
                        switch (sc.nextInt()) {
                            //Procurar por nome
                            case 0:
                                ArrayList<Exame> examesAluno;
                                System.out.print("\nNome: ");
                                //Obter nome
                                auxString = sc.next();
                                auxStrings = auxString.split(" ");

                                //Verificar se temos duas palavras
                                if(auxStrings.length != 2) {
                                    System.out.println("Insira apenas um nome e um apelido\n");
                                    break;
                                }

                                examesAluno = getExamesAlunoPorNome(auxStrings);

                                if(examesAluno == null) {
                                    System.out.println("Aluno não encontrado\n");
                                    break;
                                }

                                //TODO -> verificar mesmo nome mas com numeros diferentes

                                //Listar exames de aluno
                                listarExamesAluno(examesAluno);

                                break;
                            //Escolher aluno da lista
                            case 1:
                                break;
                            //Voltar ao menu principal
                            default:
                                break;
                        }
                        break;
                    
                    case 3:
                        System.out.println("---Listar funcionarios de um exame---");
                        break;
                    
                    case 4:
                        System.out.println("---Listar exames de um funcionario---");
                        break;
                        
                    case 5:
                        System.out.println("---Listar notas---");
                        break;
                        
                    default:
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
