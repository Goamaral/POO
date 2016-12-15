package com.company;

import java.sql.Array;
import java.util.ArrayList;

public class FuncionarioNaoDocente extends Funcionario {
    private String cargo;

    public String toStringDetailed() {
        return this.getNumMecanografico() +
                " " + this.getNome() +
                " Categoria: " + this.getCategoria() +
                " Cargo: " + this.getCargo() +
                " Email: " + this.getEmail();
    }

    public String toString() {
        StringBuilder out = new StringBuilder(super.getNome() +
                "»" + super.getEmail() +
                "»" + super.getNumMecanografico() +
                "»" + super.getCategoria() +
                "»" + cargo +
                "»" + "N" + "\n");

        return out.toString();
    }

    public String toStringBasic() {
        StringBuilder out = new StringBuilder("\t\t" + super.getNome() + "»"
                + super.getNumMecanografico() +
                "»" + cargo + "\n");

        return out.toString();
    }

    public String getCargo() {
        return cargo;
    }

    public FuncionarioNaoDocente(String nome, String email, int numMecanografico, String categoria, String cargo) {
        super(nome, email, numMecanografico, categoria);
        this.cargo = cargo;

    }
}
