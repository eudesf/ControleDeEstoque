package br.com.ceut.controleestoque.lojas;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

public class RepositorioLojasArray {

  private Loja[] lojas;
  private int indice;

  public RepositorioLojasArray() {
    lojas = new Loja[100];
    indice = 0;
  }

  public void inserir(Loja loja) {
    lojas[indice] = loja;
    indice = indice + 1;
  }

  public void atualizar(Loja loja) {
    int i = getIndice(loja.getDescricao());
    if (i == indice) {
      throw new RuntimeException("Loja não encontrada");
    } else {
      lojas[i] = loja;
    }
  }

  public void remover(String descricao) {
    int i = getIndice(descricao);
    if (i == indice) {
      throw new RuntimeException("Loja não encontrada");
    } else {
      lojas[i] = lojas[indice-1];
      indice = indice - 1;
    }
  }

  public Loja procurar(String descricao) {
    Loja resposta;
    int i = getIndice(descricao);
    if (i == indice) {
      resposta = null;
    } else {
      resposta = lojas[i];
    }
    return resposta;
  }

  public boolean existe(String descricao) {
    boolean resposta;
    int i = getIndice(descricao);
    if (i == indice) {
      resposta = false;
    } else {
      resposta = true;
    }
    return resposta;
  }

  private int getIndice(String descricao) {
    String n;
    boolean achou = false;
    int i = 0;
    while ( (!achou) && (i < indice) ) {
      n = lojas[i].getDescricao();
      if (n.equals(descricao)) {
        achou = true;
      } else {
        i = i + 1;
      }
    }
    return i;
  }
  
  public Loja[] getLojas() {
		return lojas;
  }
  
  public int getIndice(){
	  return this.indice;
  }

}