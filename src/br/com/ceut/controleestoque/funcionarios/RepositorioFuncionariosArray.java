package br.com.ceut.controleestoque.funcionarios;

import br.com.ceut.controleestoque.util.RepositorioException;

public class RepositorioFuncionariosArray implements RepositorioFuncionarios {

	private Funcionario[] funcionarios;
	private int indice;
	public static final int TAMANHO_DEFAULT = 100;

	public RepositorioFuncionariosArray() {
		funcionarios = new Funcionario[TAMANHO_DEFAULT];
		indice = 0;
	}

	/**
	 * Propositadamente com visibilidade default
	 */
	RepositorioFuncionariosArray(Funcionario[] contas, int indice) {
		this.funcionarios = contas;
		this.indice = indice;
	}

	public void inserir(Funcionario conta) {
		funcionarios[indice] = conta;
		indice = indice + 1;
	}

	public void remover(String matricula) throws FuncionarioNaoEncontradoException {
		int i = getIndice(matricula);
		funcionarios[i] = funcionarios[indice - 1];
		indice = indice - 1;
		funcionarios[indice] = null;
	}

	public Funcionario procurar(String matricula) throws FuncionarioNaoEncontradoException {
		int i = getIndice(matricula);
		return funcionarios[i];
	}

	public void atualizar(Funcionario conta) throws FuncionarioNaoEncontradoException {
		int i = getIndice(conta.getMatricula());
		funcionarios[i] = conta;
	}

	public boolean existe(String matricula) {
		boolean resposta;
		try {
			getIndice(matricula);
			resposta = true;
		} catch (FuncionarioNaoEncontradoException ex) {
			resposta = false;
		}
		return resposta;
	}

	public RepositorioFuncionarios getFuncionarios() {
		// deveria retornar um clone por segurança
		return this;
	}

	public IteratorFuncionarios getIterator() {
		IteratorFuncionarios iterator = new IteratorFuncionariosArray(
				funcionarios);
		return iterator;
	}

	public Funcionario[] getTodosFuncionarios() {
		return this.funcionarios;
	}
	
	public int getIndice() {
		return this.indice;
	}
	
	private int getIndice(String matricula) throws FuncionarioNaoEncontradoException {
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; !achou && (i < indice); i = i + 1) {
			if (funcionarios[i].getMatricula().equals(matricula)) {
				resposta = i;
				achou = true;
			}
		}
		if (!achou)
			throw new FuncionarioNaoEncontradoException(matricula);
		return resposta;
	}
	
	private int getIndicePorNome(String nome) throws FuncionarioNaoEncontradoException {
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; !achou && (i < indice); i = i + 1) {
			if (funcionarios[i].getNome().equals(nome)) {
				resposta = i;
				achou = true;
			}
		}
		if (!achou)
			throw new FuncionarioNaoEncontradoException(nome);
		return resposta;
	}

	@Override
	public Funcionario[] getFuncionariosCadastrados()
	  throws RepositorioException {
		if (getIndice() == 0)
			System.out.println("Não existem funcionários cadastrados no momento!");

		return getTodosFuncionarios();
	}

	@Override
	public Funcionario procurarPorNome(String nome) throws FuncionarioNaoEncontradoException, RepositorioException {
		int i = getIndicePorNome(nome);
		return funcionarios[i];
	}
}
