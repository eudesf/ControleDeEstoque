package br.com.ceut.controleestoque.lojas;

public class IteratorLojasArray implements IteratorLojas {

	private Loja[] lojas;
    private int indice;

    public IteratorLojasArray(Loja[] lojas) {
        this.lojas = lojas;
        indice = 0;
    }

    public boolean hasNext(){
        return (indice < lojas.length) && (lojas[indice] != null);
    }

    public Loja next() {
    	Loja resposta = null;
        if (hasNext()) {
            resposta = lojas[indice];
            indice = indice + 1;
        }
        return resposta;
    }	
	
}
