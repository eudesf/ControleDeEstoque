package br.com.controleestoque.colecoesdedados;

import br.com.controleestoque.classesbasicas.Produto;
import br.com.controleestoque.excecoes.ProdutoNaoExiste;
import br.com.controleestoque.iterators.IteratorProduto;

public class RepositorioProdutoLista  {
	
	public void inserir(Produto produto) {
		//TODO implementar aqui
	}
	
	public void atualizar(String codigo) throws ProdutoNaoExiste {
		//TODO implementar aqui
	}
	
	public void remover(String codigo) throws ProdutoNaoExiste {
		//TODO implementar aqui
	}
	
	public Produto procurar(String codigo) {
		//TODO implementar aqui
		return null;
	}
	
	public RepositorioProdutoLista procurar(Produto produto) {
		//TODO implementar aqui
		return null;
	}
	
	public boolean existe(String codigo) {
		//TODO implementar aqui
		return false;
	}
	
	public IteratorProduto getIterator() {
		//TODO implementar aqui
		return null;
	}
	
}
