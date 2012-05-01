package br.com.ceut.controleestoque.lojas;

import br.com.ceut.controleestoque.util.RepositorioException;

/**
 * Title: Description: Copyright: Copyright (c) 2002 Company:
 * 
 * @author
 * @version 1.0
 */

public class RepositorioLojasLista implements RepositorioLojas {

	private Loja loja;
	private RepositorioLojasLista proximo;

	public RepositorioLojasLista() {
		loja = null;
		proximo = null;
	}

	public void inserir(Loja loja) {
		if (this.loja != null) {
			proximo.inserir(loja);
		} else {
			this.loja = loja;
			this.proximo = new RepositorioLojasLista();
		}
	}

	public void atualizar(Loja loja) {
		if (this.loja != null) {
			if (this.loja.getDescricao().equals(loja.getDescricao())) {
				this.loja = loja;
			} else {
				proximo.atualizar(loja);
			}
		} else {
			throw new RuntimeException("Loja não encontrada");
		}
	}

	public void remover(String loja) {
		if (this.loja != null) {
			if (this.loja.getDescricao().equals(loja)) {
				this.loja = proximo.loja;
				this.proximo = proximo.proximo;
			} else {
				proximo.remover(loja);
			}
		} else {
			throw new RuntimeException("Loja não encontrada");
		}
	}

	public Loja procurar(String descricao) {
		Loja resposta;
		if (this.loja != null) {
			if (this.loja.getDescricao().equals(descricao)) {
				resposta = this.loja;
			} else {
				resposta = proximo.procurar(descricao);
			}
		} else {
			resposta = null;
		}
		return resposta;
	}

	public boolean existe(String descricao) {
		boolean resposta;
		if (this.loja != null) {
			if (this.loja.getDescricao().equals(descricao)) {
				resposta = true;
			} else {
				resposta = proximo.existe(descricao);
			}
		} else {
			resposta = false;
		}
		return resposta;
	}

	public Loja getLoja() {
		return this.loja;
	}

	public RepositorioLojasLista getProximo() {
		return this.proximo;
	}

	public int size() {
		int size = 0;
		if (this.loja != null) {
			size++;
			RepositorioLojasLista proximo = this.proximo;
			while (proximo != null) {
				if (proximo.getLoja() != null) {
					size++;
					proximo = proximo.getProximo();
				} else
					break;
			}

			return size;
		} else
			return size;
	}

	@Override
	public Loja[] getLojas() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IteratorLojas getIterator() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loja[] listarTudo() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

}