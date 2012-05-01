package br.com.ceut.controleestoque.fornecedores;

public class FornecedorJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String codigo;

	public FornecedorJaCadastradoException(String codigo) {
		super("Fornecedor já cadastrado!");
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}
