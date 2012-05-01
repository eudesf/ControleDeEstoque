package br.com.ceut.controleestoque.fornecedores;

import br.com.ceut.controleestoque.util.RepositorioException;

public class CadastroFornecedores {

	private RepositorioFornecedores fornecedores;

	public CadastroFornecedores(RepositorioFornecedores repositorio) {
		this.fornecedores = repositorio;
	}

	public void cadastrar(Fornecedor fornecedor)
			throws FornecedorJaCadastradoException, RepositorioException {
		if (fornecedor != null) {
			if (!fornecedores.existe(fornecedor.getCodigo())) {
				fornecedores.inserir(fornecedor);
			} else {
				throw new FornecedorJaCadastradoException(fornecedor
						.getCodigo());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void remover(String codigo) throws FornecedorNaoEncontradoException,
			RepositorioException {
		fornecedores.remover(codigo);
	}

	public boolean existe(String codigo) throws RepositorioException {
		return fornecedores.existe(codigo);
	}

	public RepositorioFornecedores getFornecedores()
			throws RepositorioException {
		return fornecedores.getFornecedores();
	}

	public Fornecedor procurar(String codigo)
			throws FornecedorNaoEncontradoException, RepositorioException {
		return fornecedores.procurar(codigo);
	}

	public void atualizar(Fornecedor fornecedor)
			throws FornecedorNaoEncontradoException, RepositorioException {
		fornecedores.atualizar(fornecedor);
	}

}
