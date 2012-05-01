package br.com.ceut.controleestoque.produtos;

import java.util.Date;

public class Produto {
	private long id;
	private String descricaoProduto;
	private Date dataCadastro;
	private Date dataAtualizacao;
	private int quantidadeDisponivel;
	private String valorUnitario;

	public Produto(String descricaoProduto) {
		this.quantidadeDisponivel = 0;
		this.dataAtualizacao = null;
		this.descricaoProduto = descricaoProduto;
	}

	public long getId() {
		return id;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getValorUnitario() {
		return valorUnitario;
	}

}
