package br.com.ceut.controleestoque.fornecedores;

public class IteratorFornecedoresArray implements IteratorFornecedores {

	private Fornecedor[] fornecedores;
	private int indice;

	public IteratorFornecedoresArray(Fornecedor[] contas) {
		this.fornecedores = contas;
		indice = 0;
	}

	public boolean hasNext() {
		return (indice < fornecedores.length) && (fornecedores[indice] != null);
	}

	public Fornecedor next() {
		Fornecedor resposta = null;
		if (hasNext()) {
			resposta = fornecedores[indice];
			indice = indice + 1;
		}
		return resposta;
	}
}
