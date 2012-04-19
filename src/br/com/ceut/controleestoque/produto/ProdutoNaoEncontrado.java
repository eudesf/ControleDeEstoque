package br.com.ceut.controleestoque.produto;

@SuppressWarnings("serial")
public class ProdutoNaoEncontrado extends Exception {

	public ProdutoNaoEncontrado(String codigo) {
		super(codigo);
	}
	
}
