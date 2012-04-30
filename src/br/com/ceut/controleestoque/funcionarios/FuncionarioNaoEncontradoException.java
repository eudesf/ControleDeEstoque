package br.com.ceut.controleestoque.funcionarios;

public class FuncionarioNaoEncontradoException extends Exception {

	private String matricula;
	
	public FuncionarioNaoEncontradoException(String matricula) {
		super("Funcion‡rio n‹o encontrado!");
		this.matricula = matricula;
	}
	
	public String getMatricula() {
		return matricula;
	}
}
