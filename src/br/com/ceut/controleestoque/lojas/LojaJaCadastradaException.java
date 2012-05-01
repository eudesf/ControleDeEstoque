package br.com.ceut.controleestoque.lojas;

public class LojaJaCadastradaException extends Exception {

	private String descricao;

	public LojaJaCadastradaException(String descricao) {
		super("Loja já cadastrada, escolha outra descrição para a loja.");
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
