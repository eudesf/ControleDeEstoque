package br.com.ceut.controleestoque.lojas;

import java.util.Date;

import br.com.ceut.controleestoque.fornecedores.Fornecedor;
import br.com.ceut.controleestoque.fornecedores.RepositorioFornecedoresArray;
import br.com.ceut.controleestoque.funcionarios.Funcionario;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionariosArray;


public class Loja {

//	private long id;
	private String codigo;
	private String descricao;
	private Date dataCadastro;
	private RepositorioFuncionariosArray funcionarios;
	private RepositorioFornecedoresArray fornecedores;

	public Loja(String descricao, String codigo){
		this.descricao = descricao;
		this.codigo = codigo;
		this.dataCadastro = new Date();
		this.funcionarios = new RepositorioFuncionariosArray();
		this.fornecedores = new RepositorioFornecedoresArray();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public RepositorioFuncionariosArray getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(RepositorioFuncionariosArray funcionarios) {
		this.funcionarios = funcionarios;
	}

	public RepositorioFornecedoresArray getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(RepositorioFornecedoresArray fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public void inserirFuncionario(Funcionario funcionario){
		getFuncionarios().inserir(funcionario);
	}
	
	public void inserirFornecedor(Fornecedor fornecedor){
		getFornecedores().inserir(fornecedor);
	}
	
}
