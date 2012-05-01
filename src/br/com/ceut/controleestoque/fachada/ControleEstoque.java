package br.com.ceut.controleestoque.fachada;

import br.com.ceut.controleestoque.fornecedores.CadastroFornecedores;
import br.com.ceut.controleestoque.fornecedores.Fornecedor;
import br.com.ceut.controleestoque.fornecedores.FornecedorJaCadastradoException;
import br.com.ceut.controleestoque.fornecedores.FornecedorNaoEncontradoException;
import br.com.ceut.controleestoque.fornecedores.RepositorioFornecedores;
import br.com.ceut.controleestoque.fornecedores.RepositorioFornecedoresArray;
import br.com.ceut.controleestoque.funcionarios.CadastroFuncionarios;
import br.com.ceut.controleestoque.funcionarios.Funcionario;
import br.com.ceut.controleestoque.funcionarios.FuncionarioJaCadastradoException;
import br.com.ceut.controleestoque.funcionarios.FuncionarioNaoEncontradoException;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionarios;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionariosArray;
import br.com.ceut.controleestoque.lojas.CadastroLojas;
import br.com.ceut.controleestoque.produtos.CadastroProdutos;
import br.com.ceut.controleestoque.produtos.Produto;
import br.com.ceut.controleestoque.produtos.ProdutoJaCadastradoException;
import br.com.ceut.controleestoque.produtos.ProdutoNaoEncontradoException;
import br.com.ceut.controleestoque.produtos.RepositorioProdutos;
import br.com.ceut.controleestoque.produtos.RepositorioProdutosLista;
import br.com.ceut.controleestoque.util.RepositorioException;

public class ControleEstoque {

	private CadastroProdutos produtos;
	private CadastroFuncionarios funcionarios;
	private CadastroFornecedores fornecedores;
	private CadastroLojas lojas;
	
	public ControleEstoque() {
		initCadastros();
	}

	public void initCadastros() {
		RepositorioProdutos repositorioProdutos = new RepositorioProdutosLista();
		RepositorioFuncionarios repositorioFuncionarios = new RepositorioFuncionariosArray();
		RepositorioFornecedores repositorioFornecedores = new RepositorioFornecedoresArray();

		//IMPLEMENTAR INTERFACE REPOSITORIOLOJAS

		produtos = new CadastroProdutos(repositorioProdutos);
		funcionarios = new CadastroFuncionarios(repositorioFuncionarios);
		fornecedores = new CadastroFornecedores(repositorioFornecedores);
		
		//Colocar repositorio de loja aqui!!!
	}

	/*Metodos da classe Produto*/
	public void cadastrarProduto(Produto produto) throws ProdutoNaoEncontradoException, ProdutoJaCadastradoException {
		produtos.cadastrar(produto);
	}

	public void removerProduto(String nome) throws ProdutoNaoEncontradoException {
		produtos.remover(nome);
	}

	public void atualizarProduto(Produto produto) throws ProdutoNaoEncontradoException {
		produtos.atualizar(produto);
	}

	public Produto procurarProduto(String nome) throws ProdutoNaoEncontradoException {
		return produtos.procurar(nome);
	}
	
	/*Metodos da classe Funcionario*/
	public void cadastrar(Funcionario funcionario) throws FuncionarioJaCadastradoException, RepositorioException {
		funcionarios.cadastrar(funcionario);
	}
	
	public void removerFuncionario(String matricula) throws FuncionarioNaoEncontradoException, RepositorioException {
		funcionarios.remover(matricula);
	}
	
	public Funcionario procurarFuncionario(String matricula) throws FuncionarioNaoEncontradoException, RepositorioException {
		return funcionarios.procurar(matricula);
	}
	
	public RepositorioFuncionarios getFuncionarios() throws RepositorioException {
		return funcionarios.getFuncionarios();
	}
	
	public void atualizar(Funcionario funcionario) throws FuncionarioNaoEncontradoException, RepositorioException {
		funcionarios.atualizar(funcionario);
	}
	
	/*Metodos da classe Fornecedores*/
	public void cadastrar(Fornecedor fornecedor) throws FornecedorJaCadastradoException, RepositorioException {
		fornecedores.cadastrar(fornecedor);
	}
	
	public void removerFornecedor(String codigo) throws FornecedorNaoEncontradoException, RepositorioException {
		fornecedores.remover(codigo);
	}
	
	public Fornecedor procurarFornecedor(String codigo) throws FornecedorNaoEncontradoException, RepositorioException {
		return fornecedores.procurar(codigo);
	}
	
	public RepositorioFornecedores getFornecedores() throws RepositorioException {
		return fornecedores.getFornecedores();
	}
	
	public void atualizar(Fornecedor fornecedor) throws FornecedorNaoEncontradoException, RepositorioException {
		fornecedores.atualizar(fornecedor);
	}
	
	/*Metodos da classe Loja*/
}
