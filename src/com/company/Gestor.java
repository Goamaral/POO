package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestor {

    private ArrayList<Exame> exames;
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Curso> cursos;
    private ArrayList<Sala> salas;
    private ArrayList<String> historico;
    private String importFileName;
    private String exportFileName;
    private static String[] menuPrincipal = {"Listar", "Criar Exames", "Configurar Exames", "Lancar notas" , "Sair"};
    private static String[] menuListar = {"Listar Exames", "Listar Alunos Inscritos Exame Classificacoes"};

    public static void main(String[] args) {
        while(true) {
            menu();
        }
    }

    public void listarExames() {

    }

    public void listarAlunosInscritosExameClassificacoes() {

    }

    public void guardarDados() {

    }

    public void guardarHistorico() {

    }

    public void carregarDados() {

    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Gestor de Exames ---" + "\n\n");
        printMenu(menuPrincipal);
        System.out.print("Opcao: ");
        switch (sc.nextInt()) {
            case 0:
                printMenu(menuListar);
                break;
        }


    }

    public static void printMenu(String[] menu) {
        for(int i=0; i < menu.length; ++i) {
            System.out.println("[" + i + "] " + menu[i]);
        }
    }
}
