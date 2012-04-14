package br.com.controleestoque.dados;

import java.util.ArrayList;
import java.util.List;

import br.com.controleestoque.banco.Funcionario;

public class RepositorioFuncionarioLista implements RepositorioFuncionario {
	
	private List<Funcionario> listaFuncionario;

	public RepositorioFuncionarioLista() {
		listaFuncionario = new ArrayList<Funcionario>();
	}
	
	@Override
	public void inserir(Funcionario funcionario) throws FuncionarioJaCadastrado {
		if (getIndice(funcionario.getMatricula()) != null) {
			throw new FuncionarioJaCadastrado(funcionario.getMatricula());
		}
		listaFuncionario.add(funcionario);
	}
	
	@Override
	public void remover(String matricula) throws FuncionarioNaoExiste {
		Integer indice = getIndice(matricula);
		if (indice == null) {
			throw new FuncionarioNaoExiste(matricula);
		}
		listaFuncionario.remove(indice);
	}
	
	@Override
	public Funcionario procurar(String matricula) throws FuncionarioNaoExiste {
		Integer indice = getIndice(matricula);
		if (indice == null) {
			throw new FuncionarioNaoExiste(matricula);
		}
		return listaFuncionario.get(indice);
	}
	
	@Override
	public boolean existe(String matricula) {
		return getIndice(matricula) != null;
	}
	
	@Override
	public IteratorFuncionario getIterator() {
		return new IteratorFuncionario(listaFuncionario.iterator());
	}
	
	private Integer getIndice(String matricula) {
		for (int indice = 0; indice < listaFuncionario.size(); indice++) {
			if (listaFuncionario.get(indice).getMatricula().equals(matricula)) {
				return indice;
			}
		}
		return null;
	}

}
