package com.company;

import java.util.ArrayList;

public class Gestor {

    private ArrayList<Exame> exames;
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Curso> cursos;
    private ArrayList<Sala> salas;
    private ArrayList<String> listagens;
    private ArrayList<String> pesquisas;
    private String importFileName;
    private String exportFileName;
    private static String[] menuPrincipal = {"Listagens"};

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

        int menu1;
        System.out.println("--- Gestor de Exames ---" + "\n\n");
        for(int i=0; i < menuPrincipal.length; ++i) {
            System.out.println("(" + i + ") " + menuPrincipal[i]);
        }
    }
}
