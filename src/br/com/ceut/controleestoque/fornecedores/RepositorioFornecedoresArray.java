package br.com.ceut.controleestoque.fornecedores;

import br.com.ceut.controleestoque.util.RepositorioException;

public class RepositorioFornecedoresArray implements RepositorioFornecedores {

	private Fornecedor[] fornecedores;
	private int indice;
	public static final int TAMANHO_DEFAULT = 100;

	public RepositorioFornecedoresArray() {
		fornecedores = new Fornecedor[TAMANHO_DEFAULT];
		indice = 0;
	}

	RepositorioFornecedoresArray(Fornecedor[] contas, int indice) {
		this.fornecedores = contas;
		this.indice = indice;
	}

	public void inserir(Fornecedor fornecedor) {
		fornecedores[indice] = fornecedor;
		indice = indice + 1;
	}

	public void remover(String codigo) throws FornecedorNaoEncontradoException {
		int i = getIndice(codigo);
		fornecedores[i] = fornecedores[indice - 1];
		indice = indice - 1;
		fornecedores[indice] = null;
	}

	public Fornecedor procurar(String codigo)
			throws FornecedorNaoEncontradoException {
		int i = getIndice(codigo);
		return fornecedores[i];
	}

	public void atualizar(Fornecedor fornecedor)
			throws FornecedorNaoEncontradoException {
		int i = getIndice(fornecedor.getCodigo());
		fornecedores[i] = fornecedor;
	}

	public boolean existe(String codigo) {
		boolean resposta;
		try {
			getIndice(codigo);
			resposta = true;
		} catch (FornecedorNaoEncontradoException ex) {
			resposta = false;
		}
		return resposta;
	}

	public RepositorioFornecedores getFornecedores() {
		return this;
	}

	public IteratorFornecedores getIterator() {
		IteratorFornecedores iterator = new IteratorFornecedoresArray(
				fornecedores);
		return iterator;
	}
	
	public Fornecedor[] getTodosFornecedores() {
		return this.fornecedores;
	}
	
	public int getIndice() {
		return this.indice;
	}

	private int getIndice(String codigo)
			throws FornecedorNaoEncontradoException {
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; !achou && (i < indice); i = i + 1) {
			if (fornecedores[i].getCodigo().equals(codigo)) {
				resposta = i;
				achou = true;
			}
		}
		if (!achou)
			throw new FornecedorNaoEncontradoException(codigo);
		return resposta;
	}
	
	private int getIndicePorNome(String nome) throws FornecedorNaoEncontradoException {
		int resposta = -1;
		boolean achou = false;
		for (int i = 0; !achou && (i < indice); i = i + 1) {
			if (fornecedores[i].getNome().equals(nome)) {
				resposta = i;
				achou = true;
			}
		}
		if (!achou)
			throw new FornecedorNaoEncontradoException(nome);
		return resposta;
	}

	@Override
	public Fornecedor[] getFornecedoresCadastrados()
			throws RepositorioException {
		if (getIndice() == 0)
			System.out.println("Não existem fornecedores cadastrados no momento!");

		return getTodosFornecedores();
	}

	@Override
	public Fornecedor procurarPorNome(String nome)
			throws FornecedorNaoEncontradoException, RepositorioException {
		int i = getIndicePorNome(nome);
		return fornecedores[i];
	}
}
