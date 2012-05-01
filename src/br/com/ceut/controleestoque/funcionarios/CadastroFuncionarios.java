package br.com.ceut.controleestoque.funcionarios;

import br.com.ceut.controleestoque.util.RepositorioException;

public class CadastroFuncionarios {

	private RepositorioFuncionarios funcionarios;

	public CadastroFuncionarios(RepositorioFuncionarios repositorio) {
		this.funcionarios = repositorio;
	}

	public void cadastrar(Funcionario funcionario)
			throws FuncionarioJaCadastradoException, RepositorioException {
		if (funcionario != null) {
			if (!funcionarios.existe(funcionario.getMatricula())) {
				funcionarios.inserir(funcionario);
			} else {
				throw new FuncionarioJaCadastradoException(funcionario
						.getMatricula());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void remover(String matricula)
			throws FuncionarioNaoEncontradoException, RepositorioException {
		funcionarios.remover(matricula);
	}

	public boolean existe(String matricula) throws RepositorioException {
		return funcionarios.existe(matricula);
	}

	public RepositorioFuncionarios getFuncionarios()
			throws RepositorioException {
		return funcionarios.getFuncionarios();
	}

	public Funcionario procurar(String matricula)
			throws FuncionarioNaoEncontradoException, RepositorioException {
		return funcionarios.procurar(matricula);
	}
	
	public Funcionario procurarPorNome(String nome) throws FuncionarioNaoEncontradoException, RepositorioException {
	    return funcionarios.procurar(nome);
	  }

	public void atualizar(Funcionario funcionario)
			throws FuncionarioNaoEncontradoException, RepositorioException {
		funcionarios.atualizar(funcionario);
	}
	
	public Funcionario[] listarTudo() throws RepositorioException {
		return funcionarios.getFuncionariosCadastrados();
	}
	
	/* É preciso usar estrutura de lista para usar este método
	public Funcionario[] listarTudo() {
		 int tam = funcionarios.size();
		 Funcionario[] funcionarios = new Funcionario[tam];
		 int cont = 0;
			 if(this.funcionarios.getFuncionario()!=null){
				 funcionarios[cont] = this.funcionarios.getFuncionario();
				 cont++;
				 if(this.funcionarios.getProximo()!=null){
					 RepositorioFuncionarioLista proximo = this.funcionarios.getProximo();
					  while(proximo != null) {
						  if(proximo.getFuncionario()!=null){
						      funcionarios[cont] = proximo.getFuncionario();
						      cont++;
						      proximo = proximo.getProximo();
						  }else
							  break;
					  }
				 }	  
			 }
		 	
		 if(cont==0)
	  		  System.out.println("Não existem funcionários cadastrados no momento!");
		 
		 	return funcionarios;
	  }
	*/

}
