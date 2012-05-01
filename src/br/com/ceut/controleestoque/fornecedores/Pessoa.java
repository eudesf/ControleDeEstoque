package br.com.ceut.controleestoque.fornecedores;

import java.util.Date;

public abstract class Pessoa {

	private String nome;

	private Date dataCadastro;

	private String endereco;

	public String getNome() {
		return nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}