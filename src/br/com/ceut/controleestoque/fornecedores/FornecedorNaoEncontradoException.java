package br.com.ceut.controleestoque.fornecedores;

public class FornecedorNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String codigo;
	
	public FornecedorNaoEncontradoException(String codigo) {
		super("Fornecedor não encontrado!");
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
