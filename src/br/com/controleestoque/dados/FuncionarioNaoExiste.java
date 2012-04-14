package br.com.controleestoque.dados;

public class FuncionarioNaoExiste extends Exception {

	private static final long serialVersionUID = -6386309097346616230L;

	public FuncionarioNaoExiste(String matricula) {
		super(matricula);
	}
}
