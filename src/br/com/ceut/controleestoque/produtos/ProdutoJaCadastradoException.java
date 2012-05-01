package br.com.ceut.controleestoque.produtos;

import br.com.ceut.controleestoque.util.RepositorioException;

public class ProdutoJaCadastradoException extends Exception {
	public ProdutoJaCadastradoException(){
		super("Produto ja cadastrado!");
	}
}
