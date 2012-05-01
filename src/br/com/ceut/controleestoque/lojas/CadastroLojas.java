package br.com.ceut.controleestoque.lojas;

import br.com.ceut.controleestoque.fornecedores.RepositorioFornecedoresArray;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionariosArray;
import br.com.ceut.controleestoque.util.RepositorioException;

/**
 * Title: Description: Copyright: Copyright (c) 2002 Company:
 * 
 * @author
 * @version 1.0
 */

public class CadastroLojas {

	private RepositorioLojasArray lojas;

	// private RepositorioLojasLista lojas;

	public CadastroLojas(RepositorioLojasArray repositorio) {
		this.lojas = repositorio;
		// lojas = new RepositorioLojasLista();
	}

	public void cadastrar(Loja loja) throws LojaJaCadastradaException,
			RepositorioException {
		if (loja != null) {
			if (lojas.existe(loja.getDescricao())) {
				// throw new
				// RuntimeException("Loja já cadastrada, escolha outra descrição para a loja.");
				throw new LojaJaCadastradaException(loja.getDescricao());
			} else {
				lojas.inserir(loja);
			}
		} else
			throw new IllegalArgumentException();
	}

	public void atualizar(Loja loja) throws LojaJaCadastradaException,
			RepositorioException {
		lojas.atualizar(loja);
	}

	public void remover(String descricao) throws LojaJaCadastradaException,
			RepositorioException {
		lojas.remover(descricao);
	}

	public boolean existe(String descricao) throws RepositorioException {
		return lojas.existe(descricao);
	}

	public boolean existeFuncionario(Loja loja, String nomeFuncionario)
			throws RepositorioException {
		RepositorioFuncionariosArray repositorioFuncionario = loja
				.getFuncionarios();

		return repositorioFuncionario.existe(nomeFuncionario);
	}

	public boolean existeFornecedor(Loja loja, String nomeFornecedor)
			throws RepositorioException {
		RepositorioFornecedoresArray repositorioFornecedor = loja
				.getFornecedores();

		return repositorioFornecedor.existe(nomeFornecedor);
	}

	public Loja procurar(String descricao) throws LojaJaCadastradaException,
			RepositorioException {
		return lojas.procurar(descricao);
	}

	public Loja[] listarTudo() {

		if (lojas.getIndice() == 0)
			System.out.println("Não existem lojas cadastradas no momento!");

		return lojas.getLojas();
	}

}