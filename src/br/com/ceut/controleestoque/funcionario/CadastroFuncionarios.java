package br.com.ceut.controleestoque.funcionario;

import br.com.ceut.controleestoque.util.RepositorioException;

public class CadastroFuncionarios {

	private RepositorioFuncionarios funcionarios;

	public CadastroFuncionarios(RepositorioFuncionarios repositorio) {
		this.funcionarios = repositorio;
	}

	public void cadastrar(Funcionario funcionario) throws FuncionarioJaCadastradoException, RepositorioException {
		if (funcionario != null) {
			if (!funcionarios.existe(funcionario.getMatricula())) {
				funcionarios.inserir(funcionario);
			} else {
				throw new FuncionarioJaCadastradoException(funcionario.getMatricula());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void remover(String matricula) throws FuncionarioNaoEncontradoException, RepositorioException {
		funcionarios.remover(matricula);
	}

	public boolean existe(String matricula) throws RepositorioException {
		return funcionarios.existe(matricula);
	}

	public RepositorioFuncionarios getFuncionarios() throws RepositorioException {
		return funcionarios.getFuncionarios();
	}

	public Funcionario procurar(String matricula) throws FuncionarioNaoEncontradoException, RepositorioException {
		return funcionarios.procurar(matricula);
	}
	
	public void atualizar(Funcionario funcionario) throws FuncionarioNaoEncontradoException, RepositorioException {
		funcionarios.atualizar(funcionario);
	}
	
}
