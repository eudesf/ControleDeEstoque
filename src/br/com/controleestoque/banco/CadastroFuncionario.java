package br.com.controleestoque.banco;

import br.com.controleestoque.dados.FuncionarioJaCadastrado;
import br.com.controleestoque.dados.RepositorioFuncionario;
import br.com.controleestoque.dados.RepositorioFuncionarioLista;

public class CadastroFuncionario {
	
	private RepositorioFuncionario repositorioFuncionarioLista;

	public CadastroFuncionario() {
		repositorioFuncionarioLista = new RepositorioFuncionarioLista();
	}
	
	public void cadastrar(Funcionario funcionario) throws FuncionarioJaCadastrado {
		repositorioFuncionarioLista.inserir(funcionario);
	}
	
}
