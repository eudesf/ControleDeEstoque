package br.com.ceut.controleestoque.lojas;

public class LojaNaoEncontradaException extends Exception {

	private String descricao;
	
	public LojaNaoEncontradaException(String descricao) {
		super("Loja n�o encontrada!");
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
