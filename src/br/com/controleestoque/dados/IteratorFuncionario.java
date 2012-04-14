package br.com.controleestoque.dados;

import java.util.Iterator;

import br.com.controleestoque.banco.Funcionario;

public class IteratorFuncionario {
	
	private Iterator<Funcionario> iterator;
	
	public IteratorFuncionario(Iterator<Funcionario> iterator) {
		this.iterator = iterator;
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}

	public Funcionario next() {
		return iterator.next();
	}
	
}
