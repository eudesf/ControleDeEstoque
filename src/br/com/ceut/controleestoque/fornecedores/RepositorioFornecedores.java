package br.com.ceut.controleestoque.fornecedores;

import br.com.ceut.controleestoque.util.RepositorioException;

public interface RepositorioFornecedores {

	public void inserir(Fornecedor fornecedor) throws RepositorioException;

	public void remover(String codigo) throws FornecedorNaoEncontradoException,
			RepositorioException;

	public Fornecedor procurar(String codigo)
			throws FornecedorNaoEncontradoException, RepositorioException;
	
	public Fornecedor procurarPorNome(String nome)
			throws FornecedorNaoEncontradoException, RepositorioException;

	public void atualizar(Fornecedor fornecedor)
			throws FornecedorNaoEncontradoException, RepositorioException;

	public boolean existe(String codigo) throws RepositorioException;

	public RepositorioFornecedores getFornecedores()
			throws RepositorioException;

	public Fornecedor[] getFornecedoresCadastrados() throws RepositorioException;
	
	public IteratorFornecedores getIterator() throws RepositorioException;
}