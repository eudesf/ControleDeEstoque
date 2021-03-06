package br.com.ceut.controleestoque.produtos;

import br.com.ceut.controleestoque.util.RepositorioException;

public interface RepositorioProdutos {
	public void inserir(Produto produto) throws ProdutoNaoEncontradoException,
			ProdutoJaCadastradoException;

	public void atualizar(Produto produto) throws ProdutoNaoEncontradoException;

	public void remover(String nome) throws ProdutoNaoEncontradoException;

	public Produto procurar(String nome) throws ProdutoNaoEncontradoException;

	public boolean existe(String nome) throws RepositorioException;

	public RepositorioProdutos getProdutos() throws RepositorioException;

}
