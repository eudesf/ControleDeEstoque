package br.com.ceut.controleestoque.lojas;

public class LojaJaCadastradaException extends Exception {

	private String descricao;

	public LojaJaCadastradaException(String descricao) {
		super("Loja j� cadastrada, escolha outra descri��o para a loja.");
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
