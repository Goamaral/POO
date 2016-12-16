package com.company;

public class FuncionarioNaoDocente extends Funcionario implements java.io.Serializable {
	private static final long serialVersionUID = 9L;
    private String cargo;

    public String toString() {
        return this.getNumMecanografico() +
                " " + this.getNome() +
                " Categoria: " + this.getCategoria() +
                " Cargo: " + this.getCargo() +
                " Email: " + this.getEmail();
    }

    public String getCargo() {
        return cargo;
    }

    public FuncionarioNaoDocente(String nome, String email, int numMecanografico, String categoria, String cargo) {
        super(nome, email, numMecanografico, categoria);
        this.cargo = cargo;

    }
}
