package br.com.ceut.controleestoque.fornecedores;

import br.com.ceut.controleestoque.util.RepositorioException;

public class CadastroFornecedores {

	private RepositorioFornecedores fornecedores;

	public CadastroFornecedores(RepositorioFornecedores repositorio) {
		this.fornecedores = repositorio;
	}

	public void cadastrar(Fornecedor fornecedor)
			throws FornecedorJaCadastradoException, RepositorioException {
		if (fornecedor != null) {
			if (!fornecedores.existe(fornecedor.getCodigo())) {
				fornecedores.inserir(fornecedor);
			} else {
				throw new FornecedorJaCadastradoException(fornecedor
						.getCodigo());
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void remover(String codigo) throws FornecedorNaoEncontradoException,
			RepositorioException {
		fornecedores.remover(codigo);
	}

	public boolean existe(String codigo) throws RepositorioException {
		return fornecedores.existe(codigo);
	}

	public RepositorioFornecedores getFornecedores()
			throws RepositorioException {
		return fornecedores.getFornecedores();
	}

	public Fornecedor procurar(String codigo)
			throws FornecedorNaoEncontradoException, RepositorioException {
		return fornecedores.procurar(codigo);
	}
	
	public Fornecedor procurarPorNome(String nome) throws FornecedorNaoEncontradoException, RepositorioException {
	    return fornecedores.procurar(nome);
	  }

	public void atualizar(Fornecedor fornecedor)
			throws FornecedorNaoEncontradoException, RepositorioException {
		fornecedores.atualizar(fornecedor);
	}
	
	public Fornecedor[] listarTudo() throws RepositorioException {
		return fornecedores.getFornecedoresCadastrados();
	}
	
/* É preciso usar estrutura de lista para usar este método
	public Fornecedor[] listarTudo() {
		 int tam = fornecedores.size();
		 Fornecedor[] fornecedores = new Fornecedor[tam];
		 int cont = 0;
			 if(this.fornecedores.getFornecedor()!=null){
				 fornecedores[cont] = this.fornecedores.getFornecedor();
				 cont++;
				 if(this.fornecedores.getProximo()!=null){
					 RepositorioFornecedorLista proximo = this.fornecedores.getProximo();
					  while(proximo != null) {
						  if(proximo.getFornecedor()!=null){
							  fornecedores[cont] = proximo.getFornecedor();
						      cont++;
						      proximo = proximo.getProximo();
						  }else
							  break;
					  }
				 }	  
			 }
		 	
		 if(cont==0)
	  		  System.out.println("Não existem fornecedores cadastrados no momento!");
		 
		 	return fornecedores;
 	}
*/
	
}
