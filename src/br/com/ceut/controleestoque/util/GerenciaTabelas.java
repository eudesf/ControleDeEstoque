package br.com.ceut.controleestoque.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GerenciaTabelas {
    
    public static final String[] TABELAS = {"_funcionarios"};

    private static Statement stmt;
    private static String login;   
    
    private static void removerTabela(String nome) throws SQLException {
        String sql = "DROP TABLE " + nome;
        executarSQL(sql);
    }

    private static void esvaziarTabela(String nome) throws SQLException {
        String sql = "TRUNCATE TABLE " + nome;
        executarSQL(sql);
    }
    
    private static void criarTabela(String sql) throws SQLException {
        try {
            executarSQL(sql);
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(sql);
        }                        
    }

    private static void executarSQL(String sql) throws SQLException {
        if (sql == null) {
            throw new IllegalArgumentException("parametro invalido");
        }
        System.out.println(sql);
		    GerenciaTabelas.stmt.executeUpdate(sql); 
    }


    public static void main(String[] args) {
        PersistenceMechanismRDBMS pm = null;
        try {
            pm = PersistenceMechanismRDBMS.getInstance();
            pm.connect();
			GerenciaTabelas.stmt = (Statement) pm.getCommunicationChannel();            
            main();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getMessage()); //ex.printStackTrace();
        } finally {
            try {
                if (GerenciaTabelas.stmt != null) {
					            GerenciaTabelas.stmt.close();
                }            
            } catch (Exception ex) {
                System.out.println(ex.getMessage()); //ex.printStackTrace();                
                System.out.println("Erro! 2");
            }
            try {
                pm.releaseCommunicationChannel();
            } catch (Exception ex) {
                System.out.println(ex.getMessage()); //ex.printStackTrace();                
                System.out.println("Erro! 3");
            }
            try {
                pm.disconnect();
            } catch (Exception ex) {
                System.out.println(ex.getMessage()); //ex.printStackTrace();                                
                System.out.println("Erro! 4");
            }                
        }                
    }
    
    private static void main() throws Exception {               
        System.out.println("Digite o seu login para identificar suas tabelas");
        login = Util.readStr();
        m();
    }
    
    private static void m() throws Exception {
        String sql;
        System.out.println("Escolha uma opção");
        System.out.println("1 - Criar   base");
        System.out.println("2 - Limpar  base");
        System.out.println("3 - Remover base");
        System.out.println("4 - Executar select");
        System.out.println("5 - Executar update");        
        System.out.println("6 - Mudar login");        
        System.out.println("7 - Sair");                
        int opcao = Util.readInt();
        switch (opcao) {
            case 1: criarTabelas();
                    System.out.println("Tabelas criadas");
                    povoarTabelas();
                    System.out.println("Tabelas de dados básicos povoadas");
                    break;
                    
            case 2: esvaziarTabelas();
                    System.out.println("Tabelas esvaziadas");
                    povoarTabelas();
                    System.out.println("Tabelas de dados básicos povoadas");
                    break;
                     
            case 3: removerTabelas();
                    System.out.println("Tabelas removidas");
                    break;

            case 4: System.out.println("Digite o comando sql (apenas a primeira coluna será apresentada)");
                    sql = Util.readStr();
                    try {
                        ResultSet rs = GerenciaTabelas.stmt.executeQuery(sql); 
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }                        
                    } catch(SQLException e) {
                        System.out.println(e.getMessage()); //e.printStackTrace();
                    }                    
                    break;                                                  

            case 5: System.out.println("Digite o comando sql");
                    sql = Util.readStr();
                    try {
                        int i = GerenciaTabelas.stmt.executeUpdate(sql); 
                        System.out.println(i+" linhas afetadas");
                    } catch(SQLException e) {
                        System.out.println(e.getMessage()); //e.printStackTrace();
                    }                    
                    break;                                                  

            case 6: main();
                    break;
                    
            case 7: return;
        }        
        m();
    }
 
    private static void criarTabelas() throws SQLException {
        String sql = null;
        sql = "CREATE TABLE " + login + "_FUNCIONARIOS (" +
        		"nome VARCHAR(60)," +
        		"datacadastro DATETIME," +
        		"endereco VARCHAR(60)," +
        		"matricula VARCHAR(45)," +
        		"cpf VARCHAR(45)," +
        		"dataadmissao DATETIME," +
        		"datademissao DATETIME," +
        		"ativo TINYINT(1)," +
        		"PRIMARY KEY (matricula))";        
        criarTabela(sql);                                
    }
    
    private static void povoarTabelas() throws SQLException {
        String sql;
        sql = "INSERT INTO " + login + "_FUNCIONARIOS (matricula, nome) VALUES (" +
        		"'1203234', 'Fulano de Tal')";
        criarTabela(sql);
        
    }

    private static void removerTabelas() throws SQLException {
        for (int i=0; i < TABELAS.length; i++) {
            try {
                removerTabela(login+TABELAS[i]);
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("TABELA INDICE "+i);
            }
        }
        String sql;
        
    }

    private static void esvaziarTabelas() throws SQLException {
        for (int i=0; i < TABELAS.length; i++) {
            try {
                esvaziarTabela(login+TABELAS[i]);
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("TABELA INDICE "+i);
            }            
        }
        String sql;

    }    
}