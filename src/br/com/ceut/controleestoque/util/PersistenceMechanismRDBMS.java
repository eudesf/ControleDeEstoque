package br.com.ceut.controleestoque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class PersistenceMechanismRDBMS implements IPersistenceMechanism {

    private static PersistenceMechanismRDBMS singleton;
    private static int numConexoes = 2;
        
    private Connection conexoesCriadas[];
    private Connection conexoesLivres[];
    private HashMap conexoesAlocadas;
    private String classeDoDriver;
    private String url;
    private String login;
    private String senha;
    private boolean indisponivel;

    private PersistenceMechanismRDBMS(String url, String login, String senha, String classeDoDriver)
            throws PersistenceMechanismException {
        conexoesAlocadas = new HashMap();
        this.classeDoDriver = classeDoDriver;
        this.url = url;
        this.login = login;
        this.senha = senha;
        indisponivel = false;
        try {
            Class.forName(classeDoDriver);
        } catch (ClassNotFoundException e) {
            throw new PersistenceMechanismException("EXC_CLASSE_NAO_ENCONTRADA");
        }
    }
    
	public static synchronized PersistenceMechanismRDBMS getInstance()
			throws PersistenceMechanismException {
		if (singleton==null) {
			singleton = new PersistenceMechanismRDBMS(
					//"jdbc:mysql://lab3-11.laboratorio.local:3306/mysql",
					// "jdbc:mysql://10.0.3.2:3307/mysql",
					"jdbc:mysql://127.0.0.1:3306/mysql",
				    "root", "",
					//"jdbc:mysql://www.freesql.org:3306/scbs",
				    //"scbs", "sergio", 
					"com.mysql.jdbc.Driver");
		}
		return singleton;
	}
    
    public synchronized void connect() throws PersistenceMechanismException {
        if (conexoesCriadas == null) {
        	System.out.println("Connecting to database");
            try {
                conexoesLivres = new Connection[PersistenceMechanismRDBMS.numConexoes];
                conexoesCriadas = new Connection[PersistenceMechanismRDBMS.numConexoes];
                for (int i = 0; i < PersistenceMechanismRDBMS.numConexoes; i++) {
                    conexoesCriadas[i] = DriverManager.getConnection(url, login, senha);
                    conexoesLivres[i] = conexoesCriadas[i];
                }

            } catch (Exception e) {     
            	e.printStackTrace();
                throw new PersistenceMechanismException(ExceptionMessages.EXC_CONECTAR);
            }
        }
    }
        
    public synchronized void disconnect() throws PersistenceMechanismException {
    	System.out.println("Disconnecting to database");
        try {
            if (conexoesCriadas != null) {
                int fechadas = 0;
                for (int i = 0; i < PersistenceMechanismRDBMS.numConexoes; i++) {
                }
                for (int i = 0; i < PersistenceMechanismRDBMS.numConexoes; i++) {
                    if (conexoesCriadas[i] != null) {
                        conexoesCriadas[i].close();
                        fechadas++;
                    }
                }

                conexoesCriadas = null;
            }
        } catch (Exception e) {
            
            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_DESCONECTAR);
        }
    }
    
    /**
     * Retorna um java.sql.Statement
     */
    public synchronized Object getCommunicationChannel() throws PersistenceMechanismException {
        try {
            return getCommunicationChannel(false).createStatement();
        } catch (Exception ex) {
            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_GET_CANAL_COMUNICACAO);
        }            
    }
    /**
     * Libera o canal de comunicação
     */
	public synchronized void releaseCommunicationChannel() throws PersistenceMechanismException {
		releaseCommunicationChannel(false);
	}
   
	public synchronized void beginTransaction() throws TransactionException {
		try {
			while (indisponivel)  {
				wait();
			}
			Connection con = (Connection)getCommunicationChannel(true);
			con.setAutoCommit(false);            
		} catch (Exception e) {           
			throw new TransactionException("EXC_INICIAR_TRANSACAO");
		}
	}
	
	public synchronized void commitTransaction() throws TransactionException {
		try {
			Connection con = (Connection)getCommunicationChannel(true);
			con.commit();
			con.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {            
			throw new TransactionException("EXC_CONFIRMAR_TRANSACAO");
		} finally {
			notifyAll();
		}
	}
	
	public synchronized void rollbackTransaction() throws TransactionException {
		try {
			Connection con = (Connection)getCommunicationChannel(true);
			con.rollback();
			con.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {           
			throw new TransactionException("EXC_CANCELAR_TRANSACAO");
		} finally {
			try {
				notifyAll();
			} catch (Exception e) {
                
			}
		}
	}
	
    /**
     * Retorna um java.sql.Connection
     */
    private synchronized Connection getCommunicationChannel(boolean porTransacao) 
            throws PersistenceMechanismException {
        Connection resposta = null;
        try {
            Thread currentThread = Thread.currentThread();
            int threadId = currentThread.hashCode();
            if (conexoesAlocadas.containsKey("" + threadId)) {
                resposta = (Connection) conexoesAlocadas.get("" + threadId);
            } else {
                boolean achou = false;
                do {
                    if (achou) {
                        break;
                    }
                    for (int i = 0; !achou && i < PersistenceMechanismRDBMS.numConexoes; i++) {
                        if (conexoesLivres[i] == null) {
                            continue;
                        }
                        achou = true;
                        resposta = conexoesLivres[i];
                        conexoesLivres[i] = null;
                        conexoesAlocadas.put("" + threadId, resposta);
                        if (porTransacao) {
                            conexoesAlocadas.put("T" + threadId, null);
                        }
                    }

                    if (!achou) {
                        indisponivel = true;
                        wait();
                    }
                } while (true);
            }
        } catch (Exception ex) {
            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_GET_CANAL_COMUNICACAO);
        }
        return resposta;
    }
    
    private synchronized void releaseCommunicationChannel(boolean porTransacao)  throws PersistenceMechanismException {
        try {
            Thread currentThread = Thread.currentThread();
            int threadId = currentThread.hashCode();
            if (porTransacao || !porTransacao && !conexoesAlocadas.containsKey("T" + threadId)) {
                Object canal = conexoesAlocadas.get("" + threadId);
                boolean achou = false;
                for (int i = 0; !achou && i < PersistenceMechanismRDBMS.numConexoes; i++) {
                    if (conexoesLivres[i] != null) {
                        continue;
                    }
                    achou = true;
                    conexoesAlocadas.remove("" + threadId);
                    if (conexoesAlocadas.containsKey("T" + threadId)) {
                        conexoesAlocadas.remove("T" + threadId);
                    }
                    conexoesLivres[i] = (java.sql.Connection)canal;
                }

                indisponivel = false;
            }
        } catch (Exception ex) {
            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_LIBERAR_CANAL_COMUNICACAO);
        } finally {
            notifyAll();
        }
    }
   
    public void finalize() {
    	try {
			this.disconnect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
    }
    }
