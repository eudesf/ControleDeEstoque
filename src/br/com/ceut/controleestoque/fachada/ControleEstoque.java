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
import br.com.ceut.controleestoque.lojas.Loja;
import br.com.ceut.controleestoque.lojas.LojaJaCadastradaException;
import br.com.ceut.controleestoque.lojas.LojaNaoEncontradaException;
import br.com.ceut.controleestoque.lojas.RepositorioLojas;
import br.com.ceut.controleestoque.lojas.RepositorioLojasArray;
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
		RepositorioLojas repositorioLojas = new RepositorioLojasArray();

		produtos = new CadastroProdutos(repositorioProdutos);
		funcionarios = new CadastroFuncionarios(repositorioFuncionarios);
		fornecedores = new CadastroFornecedores(repositorioFornecedores);
		lojas = new CadastroLojas(repositorioLojas);
	}

	/*Metodos da classe Produto*/
	public void cadastrar(Produto produto) throws ProdutoNaoEncontradoException, ProdutoJaCadastradoException {
		produtos.cadastrar(produto);
	}

	public void removerProduto(String nome) throws ProdutoNaoEncontradoException {
		produtos.remover(nome);
	}

	public void atualizar(Produto produto) throws ProdutoNaoEncontradoException {
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
	
	public Funcionario procurarFuncionarioPorNome(String nome) throws FuncionarioNaoEncontradoException, RepositorioException {
		return funcionarios.procurarPorNome(nome);
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
	
	public Fornecedor procurarFornecedorPorNome(String nome) throws FornecedorNaoEncontradoException, RepositorioException {
		return fornecedores.procurarPorNome(nome);
	}
	
	public RepositorioFornecedores getFornecedores() throws RepositorioException {
		return fornecedores.getFornecedores();
	}
	
	public void atualizar(Fornecedor fornecedor) throws FornecedorNaoEncontradoException, RepositorioException {
		fornecedores.atualizar(fornecedor);
	}
	
	/*Metodos da classe Loja*/
	public void cadastrar(Loja loja) throws LojaJaCadastradaException, RepositorioException {
    	lojas.cadastrar(loja);
    }
    
    public void atualizar(Loja loja) throws LojaJaCadastradaException, RepositorioException, LojaNaoEncontradaException {
        lojas.atualizar(loja);
      }

      public void removerLoja(String descricao) throws LojaJaCadastradaException, RepositorioException, LojaNaoEncontradaException {
        lojas.remover(descricao);
      }

      public boolean existeLoja(String descricao) throws RepositorioException {
    	return lojas.existe(descricao);
      }
      
      public boolean existeFuncionarioLoja(Loja loja, String nomeFuncionario) throws RepositorioException {
    	  RepositorioFuncionariosArray repositorioFuncionario = loja.getFuncionarios();
    	  return repositorioFuncionario.existe(nomeFuncionario);
      }
      
      public boolean existeFornecedorLoja(Loja loja, String nomeFornecedor) throws RepositorioException {
    	  RepositorioFornecedoresArray repositorioFornecedor = loja.getFornecedores();
    	  return repositorioFornecedor.existe(nomeFornecedor);
      }
      
      public Loja procurarLoja(String descricao) throws LojaJaCadastradaException, RepositorioException, LojaNaoEncontradaException {
        return lojas.procurar(descricao);
      }
      
      public Loja[] listarTudoLojas() throws RepositorioException {
	    return lojas.listarTudo();
	  }
      
      public Funcionario[] listarTudoFuncionarios() throws RepositorioException {
  	    return funcionarios.listarTudo();
  	  }
      
      public Fornecedor[] listarTudoFornecedores() throws RepositorioException {
	    return fornecedores.listarTudo();
	  }
      
}
