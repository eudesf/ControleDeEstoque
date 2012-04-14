package br.com.controleestoque.dados;

import br.com.controleestoque.banco.Funcionario;

public interface RepositorioFuncionario {

	void inserir(Funcionario funcionario) throws FuncionarioJaCadastrado;

	void remover(String matricula) throws FuncionarioNaoExiste;

	Funcionario procurar(String matricula) throws FuncionarioNaoExiste;

	boolean existe(String matricula);

	IteratorFuncionario getIterator();

}