package br.com.ceut.controleestoque.produto;


public class CadastroProduto {

	private RepositorioProduto repositorio;

	public void cadastrar(Produto produto) {
		//TODO inserir valida��es/verifica��es
		repositorio.inserir(produto);
	}
	
}
