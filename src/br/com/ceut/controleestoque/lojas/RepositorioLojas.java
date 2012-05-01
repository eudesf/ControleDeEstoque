package br.com.ceut.controleestoque.lojas;

import br.com.ceut.controleestoque.util.RepositorioException;

public interface RepositorioLojas {

	public void inserir(Loja loja) throws RepositorioException;

	public void remover(String descricao) throws LojaNaoEncontradaException,
			RepositorioException;

	public Loja procurar(String descricao) throws LojaNaoEncontradaException,
			RepositorioException;

	public void atualizar(Loja loja) throws LojaNaoEncontradaException,
			RepositorioException;

	public boolean existe(String descricao) throws RepositorioException;

	public Loja[] getLojas() throws RepositorioException;

	public IteratorLojas getIterator() throws RepositorioException;

	public Loja[] listarTudo() throws RepositorioException;

}
