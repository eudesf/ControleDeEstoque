package br.com.ceut.controleestoque.produto;


public class CadastroProduto {

	private RepositorioProduto repositorio;

	public void cadastrar(Produto produto) {
		//TODO inserir validações/verificações
		repositorio.inserir(produto);
	}
	
}
