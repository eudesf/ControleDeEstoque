package br.com.controleestoque.dados;

import br.com.controleestoque.banco.Produto;
import br.com.controleestoque.banco.ProdutoNaoEncontrado;

public class RepositorioProdutoLista implements RepositorioProduto  {
	
	@Override
	public void inserir(Produto produto) {
		//TODO implementar aqui
	}
	
	@Override
	public void atualizar(String codigo) throws ProdutoNaoEncontrado {
		//TODO implementar aqui
	}
	
	@Override
	public void remover(String codigo) throws ProdutoNaoEncontrado {
		//TODO implementar aqui
	}
	
	@Override
	public Produto procurar(String codigo) throws ProdutoNaoEncontrado {
		//TODO implementar aqui
		return null;
	}
	
	@Override
	public RepositorioProduto procurar(Produto produto) {
		//TODO implementar aqui
		return null;
	}
	
	@Override
	public boolean existe(String codigo) {
		//TODO implementar aqui
		return false;
	}
	
	@Override
	public IteratorProduto getIterator() {
		//TODO implementar aqui
		return null;
	}
	
}
