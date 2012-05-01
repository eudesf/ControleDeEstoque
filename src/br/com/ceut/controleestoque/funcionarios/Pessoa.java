package br.com.ceut.controleestoque.funcionarios;

import java.util.Date;

/**
 * Classe abstrata que representa uma pessoa (f’sica ou juridica).
 */
public abstract class Pessoa {

	// representa o nome da pessoa
	private String nome;
	
	// representa a data de cadastro da pessoa
	private Date dataCadastro;
	
	// representa o endereco da pessoa
	private String endereco;

	
	/**
	 * Retorna o valor do atributo nome do objeto. 
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o valor do atributo dataCadastro do objeto.
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Retorna o valor do atributo endereco do objeto.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Atribui o valor do atributo nome do objeto. 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Atribui o valor do atributo dataCadastro do objeto. 
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Atribui o valor do atributo endereco do objeto.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
