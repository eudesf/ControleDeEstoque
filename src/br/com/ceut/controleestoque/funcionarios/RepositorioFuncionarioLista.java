package br.com.ceut.controleestoque.funcionarios;

public class RepositorioFuncionarioLista {

	private Funcionario funcionario;
	private RepositorioFuncionarioLista proximo;

	  public RepositorioFuncionarioLista() {
	    funcionario = null;
	    proximo = null;
	  }

	  public void inserir(Funcionario funcionario) {
	    if (this.funcionario != null) {
	      proximo.inserir(funcionario);
	    } else {
	      this.funcionario = funcionario;
	      this.proximo = new RepositorioFuncionarioLista();
	    }
	  }

	  public void atualizar(Funcionario funcionario) {
	    if (this.funcionario != null) {
	      if (this.funcionario.getNome().equals(funcionario.getNome())) {
	        this.funcionario = funcionario;
	      } else {
	        proximo.atualizar(funcionario);
	      }
	    } else {
	      throw new RuntimeException("Funcionario não encontrada");
	    }
	  }

	  public void remover(String funcionario) {
	    if (this.funcionario != null) {
	      if (this.funcionario.getNome().equals(funcionario)) {
	        this.funcionario = proximo.funcionario;
	        this.proximo = proximo.proximo;
	      } else {
	        proximo.remover(funcionario);
	      }
	    } else {
	      throw new RuntimeException("Funcionario não encontrada");
	    }
	  }

	  public Funcionario procurar(String nome) {
	    Funcionario resposta;
	    if (this.funcionario != null) {
	      if (this.funcionario.getNome().equals(nome)) {
	        resposta = this.funcionario;
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
	    if (this.funcionario != null) {
	      if (this.funcionario.getNome().equals(nome)) {
	        resposta = true;
	      } else {
	        resposta = proximo.existe(nome);
	      }
	    } else {
	      resposta = false;
	    }
	    return resposta;
	  }	
	  
	  public Funcionario getFuncionario(){
		  return this.funcionario;
	  }
	  
	  public RepositorioFuncionarioLista getProximo(){
		  return this.proximo;
	  }
	  
	  public int size(){	  
		  int size = 0;
		  if(this.funcionario!=null){
			  size++;
			  RepositorioFuncionarioLista proximo = this.proximo;
				  while(proximo != null) {
				      if(proximo.getFuncionario()!=null){
				    	  size++;
				      	  proximo = proximo.getProximo();
				      }else
				    	  break;
				  }
			  
			  return size;
		  }else
			  return size;
	  }
	  
	  /*
	  public Funcionario[] getFuncionarios(){
		 int tam = size();
		 Funcionario[] funcionarios = new Funcionario[tam];
		 int cont = 0;
		 funcionarios[cont] = this.funcionario;
		 cont++;
		 RepositorioFuncionarioLista proximo = this.proximo;
		  while(proximo != null) {
		      funcionarios[cont] = proximo.getFuncionario();
		      proximo = proximo.getProximo();
		      cont++;
		  }
		  return funcionarios;
	  }
	  */
	
}
