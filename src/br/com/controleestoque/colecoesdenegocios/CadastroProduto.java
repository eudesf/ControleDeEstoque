package br.com.controleestoque.colecoesdenegocios;

import br.com.controleestoque.classesbasicas.Produto;
import br.com.controleestoque.colecoesdedados.RepositorioProdutoLista;

public class CadastroProduto {

	private RepositorioProdutoLista repositorio;

	public void cadastrar(Produto produto) {
		//TODO inserir valida��es/verifica��es
		repositorio.inserir(produto);
	}
	
}
