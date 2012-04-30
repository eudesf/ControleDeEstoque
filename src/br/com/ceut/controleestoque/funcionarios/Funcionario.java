package br.com.ceut.controleestoque.funcionarios;

import java.util.Date;

/**
 * Classe que modela objetos que representam Funcion‡rios da Empresa
 */
public class Funcionario extends Pessoa {
	
	// representa a matr’cula do funcion‡rio
	private String matricula;
	
	// representa o cpf do funcion‡rio
	private String cpf;
	
	// representa a data de admiss‹o do funcion‡rio
	private Date dataAdmissao;
	
	// representa a data de demiss‹o do funcion‡rio
	private Date dataDemissao;
	
	// representa um valor l—gico indicando se o funcion‡rio est‡ ativo
	private boolean ativo;

	/**
	 * Construtor que recebe uma string para inicializar o 
	 * atributo matr’cula
	 */
	public Funcionario(String matricula) {
		this.matricula = matricula;
	}
	
	/**
	 * Retorna o valor do atributo matr’cula do objeto.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna o valor do atributo cpf do objeto.
	 */
	public String getCPF() {
		return cpf;
	}
	
	/**
	 * Retorna o valor do atributo dataAdmissao do objeto.
	 */
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * Retorna o valor do atributo dataDemissao do objeto.
	 */
	public Date getDataDemissao() {
		return dataDemissao;
	}
	
	/**
	 * Retorna o valor do atributo ativo do objeto.
	 */
	public boolean isAtivo() {
		return ativo;
	}
	
	/**
	 * Atribui o valor do atributo cpf do objeto.
	 */
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Atribui o valor do atributo dataAdmissao do objeto.
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Atribui o valor do atributo dataDemissao do objeto.
	 */
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	/**
	 * Atribui o valor do atributo ativo do objeto.
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
