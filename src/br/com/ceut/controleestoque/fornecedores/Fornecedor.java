package br.com.ceut.controleestoque.fornecedores;

import java.util.Date;

import br.com.ceut.controleestoque.util.Pessoa;

public class Fornecedor extends Pessoa {
	
	private String codigo;
	
	private String cnpj;
	
	private Date dataCadastro;

	public Fornecedor(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCNPJ() {
		return cnpj;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
