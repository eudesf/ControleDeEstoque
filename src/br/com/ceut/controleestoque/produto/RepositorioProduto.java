package br.com.ceut.controleestoque.produto;


public interface RepositorioProduto {

	void inserir(Produto produto);

	void atualizar(String codigo) throws ProdutoNaoEncontrado;

	void remover(String codigo) throws ProdutoNaoEncontrado;

	Produto procurar(String codigo) throws ProdutoNaoEncontrado;

	RepositorioProduto procurar(Produto produto);

	boolean existe(String codigo);

	IteratorProduto getIterator();

}