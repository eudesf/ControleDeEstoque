package br.com.controleestoque.banco;

@SuppressWarnings("serial")
public class ProdutoNaoEncontrado extends Exception {

	public ProdutoNaoEncontrado(String codigo) {
		super(codigo);
	}
	
}
