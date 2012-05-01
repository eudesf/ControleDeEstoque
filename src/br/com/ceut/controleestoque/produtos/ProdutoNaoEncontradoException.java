package br.com.ceut.controleestoque.produtos;

public class ProdutoNaoEncontradoException extends Exception {
	public ProdutoNaoEncontradoException() {
		super("Produto nao encontrado");
	}
}
