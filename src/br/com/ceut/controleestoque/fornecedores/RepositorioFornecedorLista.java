package br.com.ceut.controleestoque.fornecedores;

public class RepositorioFornecedorLista {

	private Fornecedor fornecedor;
	private RepositorioFornecedorLista proximo;

	  public RepositorioFornecedorLista() {
	    fornecedor = null;
	    proximo = null;
	  }

	  public void inserir(Fornecedor fornecedor) {
	    if (this.fornecedor != null) {
	      proximo.inserir(fornecedor);
	    } else {
	      this.fornecedor = fornecedor;
	      this.proximo = new RepositorioFornecedorLista();
	    }
	  }

	  public void atualizar(Fornecedor fornecedor) {
	    if (this.fornecedor != null) {
	      if (this.fornecedor.getNome().equals(fornecedor.getNome())) {
	        this.fornecedor = fornecedor;
	      } else {
	        proximo.atualizar(fornecedor);
	      }
	    } else {
	      throw new RuntimeException("Fornecedor não encontrada");
	    }
	  }

	  public void remover(String fornecedor) {
	    if (this.fornecedor != null) {
	      if (this.fornecedor.getNome().equals(fornecedor)) {
	        this.fornecedor = proximo.fornecedor;
	        this.proximo = proximo.proximo;
	      } else {
	        proximo.remover(fornecedor);
	      }
	    } else {
	      throw new RuntimeException("Fornecedor não encontrada");
	    }
	  }

	  public Fornecedor procurar(String nome) {
	    Fornecedor resposta;
	    if (this.fornecedor != null) {
	      if (this.fornecedor.getNome().equals(nome)) {
	        resposta = this.fornecedor;
	      } else {
	        resposta = proximo.procurar(nome);
	      }
	    } else {
	      resposta = null;
	    }
	    return resposta;
	  }

	  public boolean existe(String nome) {
	    boolean resposta;
	    if (this.fornecedor != null) {
	      if (this.fornecedor.getNome().equals(nome)) {
	        resposta = true;
	      } else {
	        resposta = proximo.existe(nome);
	      }
	    } else {
	      resposta = false;
	    }
	    return resposta;
	  }
	  
	  public Fornecedor getFornecedor(){
		  return this.fornecedor;
	  }
	  
	  public RepositorioFornecedorLista getProximo(){
		  return this.proximo;
	  }
	  
	  public int size(){	  
		  int size = 0;
		  if(this.fornecedor!=null){
			  size++;
			  RepositorioFornecedorLista proximo = this.proximo;
				  while(proximo != null) {
				      if(proximo.getFornecedor()!=null){
				    	  size++;
				      	  proximo = proximo.getProximo();
				      }else
				    	  break;
				  }
			  
			  return size;
		  }else
			  return size;
	  }
	
}
