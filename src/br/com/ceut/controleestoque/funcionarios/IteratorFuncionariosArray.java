package br.com.ceut.controleestoque.funcionarios;

public class IteratorFuncionariosArray implements IteratorFuncionarios {
	
    private Funcionario[] funcionarios;
    private int indice;

    public IteratorFuncionariosArray(Funcionario[] contas) {
        this.funcionarios = contas;
        indice = 0;
    }

    public boolean hasNext(){
        return (indice < funcionarios.length) && (funcionarios[indice] != null);
    }

    public Funcionario next() {
        Funcionario resposta = null;
        if (hasNext()) {
            resposta = funcionarios[indice];
            indice = indice + 1;
        }
        return resposta;
    }	
}
