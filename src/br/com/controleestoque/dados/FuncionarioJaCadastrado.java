package br.com.controleestoque.dados;

public class FuncionarioJaCadastrado extends Exception {

	private static final long serialVersionUID = 2407452386509063598L;

	public FuncionarioJaCadastrado(String matricula) {
		super(matricula);
	}
}
