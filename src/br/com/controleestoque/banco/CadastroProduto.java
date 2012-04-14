package br.com.controleestoque.banco;

import br.com.controleestoque.dados.RepositorioProduto;

public class CadastroProduto {

	private RepositorioProduto repositorio;

	public void cadastrar(Produto produto) {
		//TODO inserir validações/verificações
		repositorio.inserir(produto);
	}
	
}
