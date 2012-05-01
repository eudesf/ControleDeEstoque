package br.com.ceut.controleestoque.funcionarios;

public class FuncionarioJaCadastradoException extends Exception {

	private String matricula;

	public FuncionarioJaCadastradoException(String matricula) {
		super("Funcion‡rio j‡ existente!");
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}
}
