package br.com.ceut.controleestoque.funcionarios;

import br.com.ceut.controleestoque.util.RepositorioException;

public interface RepositorioFuncionarios {

	public void inserir(Funcionario funcionario) throws RepositorioException;

	public void remover(String matricula) throws FuncionarioNaoEncontradoException, RepositorioException;

	public Funcionario procurar(String matricula) throws FuncionarioNaoEncontradoException, RepositorioException;

	public void atualizar(Funcionario funcionario) throws FuncionarioNaoEncontradoException, RepositorioException;

	public boolean existe(String matricula) throws RepositorioException;

    public RepositorioFuncionarios getFuncionarios() throws RepositorioException;

    public IteratorFuncionarios getIterator() throws RepositorioException;
}