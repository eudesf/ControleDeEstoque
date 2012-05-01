package br.com.ceut.controleestoque.produtos;

public class ProdutoJaCadastradoException extends Exception {
	public ProdutoJaCadastradoException() {
		super("Produto ja cadastrado!");
	}
}
