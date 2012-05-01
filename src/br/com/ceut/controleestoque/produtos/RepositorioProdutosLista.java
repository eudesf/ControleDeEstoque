package br.com.ceut.controleestoque.produtos;

import br.com.ceut.controleestoque.util.RepositorioException;

public class RepositorioProdutosLista implements RepositorioProdutos {

	private Produto produto;
	private RepositorioProdutosLista proximo;

	public RepositorioProdutosLista() {
		super();
		this.produto = null;
		this.proximo = null;
	}

	public void atualizar(Produto produto) throws ProdutoNaoEncontradoException {
		if (this.produto != null) {
			if (this.produto.getDescricaoProduto().equals(
					produto.getDescricaoProduto())) {
				this.produto = produto;
			} else {
				proximo.atualizar(produto);
			}
		} else {
			throw new ProdutoNaoEncontradoException();
		}
	}

	public boolean existe(String nome) {
		boolean resposta;
		if (this.produto != null) {
			if (this.produto.getDescricaoProduto().equals(nome)) {
				resposta = true;
			} else {
				resposta = proximo.existe(nome);
			}
		} else {
			resposta = false;
		}
		return resposta;
	}

	public void inserir(Produto produto) throws ProdutoNaoEncontradoException,
			ProdutoJaCadastradoException {
		boolean resultado = existe(produto.getDescricaoProduto());
		if (resultado == false) {
			if (this.produto != null) {
				proximo.inserir(produto);
			} else {
				this.produto = produto;
				this.proximo = new RepositorioProdutosLista();
			}
		} else {
			throw new ProdutoJaCadastradoException();
		}

	}

	public Produto procurar(String nome) throws ProdutoNaoEncontradoException {
		Produto resposta = null;
		if (this.produto != null) {
			if (this.produto.getDescricaoProduto().equals(nome)) {
				resposta = this.produto;
			} else {
				resposta = proximo.procurar(nome);
			}
		} else {
			throw new ProdutoNaoEncontradoException();
		}
		return resposta;
	}

	public void remover(String nome) throws ProdutoNaoEncontradoException {
		boolean resultado = existe(nome);
		if (resultado == true) {
			if (this.produto != null) {
				if (this.produto.getDescricaoProduto().equals(nome)) {
					this.produto = proximo.produto;
					this.proximo = proximo.proximo;
				} else {
					proximo.remover(nome);
				}
			} else {
				throw new ProdutoNaoEncontradoException();
			}
		} else {
			throw new ProdutoNaoEncontradoException();
		}
	}

	public RepositorioProdutos getProdutos() throws RepositorioException {
		// deveria retornar um clone por segurança
		return this;
	}
	
}
