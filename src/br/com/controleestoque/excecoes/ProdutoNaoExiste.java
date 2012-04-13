package br.com.controleestoque.excecoes;

@SuppressWarnings("serial")
public class ProdutoNaoExiste extends Exception {

	public ProdutoNaoExiste(String codigo) {
		super(codigo);
	}
	
}
