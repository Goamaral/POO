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
    private String[] menu_principal = {"Listagens"}

    public static void main(String[] args) {
        while(1) {
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

    public void menu() {

        int menu1;
        System.out.println("--- Gestor de Exames ---" + "\n\n");
        for(int i=0; i < menu_principal.length; ++i) {
            System.out.println("(" + i + ") " + menu_principal[i]);
        }
    }
}
