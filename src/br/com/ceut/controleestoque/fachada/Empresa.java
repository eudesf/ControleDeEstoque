package br.com.ceut.controleestoque.fachada;

import br.com.ceut.controleestoque.funcionarios.CadastroFuncionarios;
import br.com.ceut.controleestoque.funcionarios.Funcionario;
import br.com.ceut.controleestoque.funcionarios.FuncionarioJaCadastradoException;
import br.com.ceut.controleestoque.funcionarios.FuncionarioNaoEncontradoException;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionarios;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionariosArray;
import br.com.ceut.controleestoque.util.RepositorioException;

public class Empresa {
	
	private CadastroFuncionarios funcionarios;
	
	public Empresa() {
		initCadastros();
	}

	private void initCadastros() {
		RepositorioFuncionarios repositorioFuncionarios = new RepositorioFuncionariosArray();
		funcionarios = new CadastroFuncionarios(repositorioFuncionarios);
	}
	
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
}
