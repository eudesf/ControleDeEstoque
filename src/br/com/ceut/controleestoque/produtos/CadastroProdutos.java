package br.com.ceut.controleestoque.produtos;

import br.com.ceut.controleestoque.util.RepositorioException;

public class CadastroProdutos {

	private RepositorioProdutos produtos;

	public CadastroProdutos(RepositorioProdutos repositorio) {
		this.produtos = repositorio;
	}

	public void cadastrar(Produto produto) throws ProdutoNaoEncontradoException, ProdutoJaCadastradoException {
		produtos.inserir(produto);
	}

	public void remover(String nome) throws ProdutoNaoEncontradoException {
		produtos.remover(nome);
	}

	public boolean existe(String nome) throws RepositorioException {
		return produtos.existe(nome);
	}

	public void atualizar(Produto produto) throws ProdutoNaoEncontradoException {
		produtos.atualizar(produto);
	}

	public Produto procurar(String nome) throws ProdutoNaoEncontradoException {
		return produtos.procurar(nome);
	}

}
