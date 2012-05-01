package br.com.ceut.controleestoque.fachada;

import br.com.ceut.controleestoque.produtos.Produto;
import br.com.ceut.controleestoque.produtos.ProdutoNaoEncontradoException;
import br.com.ceut.controleestoque.produtos.RepositorioProdutos;
import br.com.ceut.controleestoque.produtos.RepositorioProdutosLista;

public class main {

	public static void main(String[] args) {

		try {
			RepositorioProdutos rp = new RepositorioProdutosLista();

			for (int i = 1; i <= 10; i++) {
				Produto produto = new Produto("EXEMPLO" + i);
				rp.inserir(produto);
			}

			Produto produto = new Produto("EXEMPLO5");
			produto.setQuantidadeDisponivel(50);

			System.out.println(rp.procurar("EXEMPLO5").getQuantidadeDisponivel());

			rp.atualizar(produto);

			System.out.println(rp.procurar("EXEMPLO5").getQuantidadeDisponivel());

			System.out.println(rp.existe("EXEMPLO5"));
			System.out.println(rp.procurar("EXEMPLO5"));
			rp.remover("EXEMPLO5");
			System.out.println(rp.existe("EXEMPLO5"));
			System.out.println(rp.procurar("EXEMPLO5"));
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		

	}

}
