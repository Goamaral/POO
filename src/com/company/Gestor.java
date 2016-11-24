package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestor {

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
        while(true) {
            menu();
        }
    }

    public static void listarExames() {
        for(int i=0;i<exames.size();++i) {
            System.out.println(exames.get(i));
        }
    }

    public static void listarAlunosInscritosExameClassificacoes() {

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
                        listarAlunosInscritosExameClassificacoes();
                        break;
                        
                    //Listar exames de um aluno
                    case 2:
                        System.out.println("\n--- Listar exames de um aluno ---");
                        System.out.println("[0] Procurar por nome");
                        System.out.println("[1] Escolher aluno da lista");
                        System.out.println("[2] Menu principal");
                        System.out.print("\nOpcao: ");
                        switch (sc.nextInt()) {
                            //Procurar por nome
                            case 0:
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
