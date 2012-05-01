package br.com.ceut.controleestoque.funcionarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.ceut.controleestoque.util.PersistenceMechanismException;
import br.com.ceut.controleestoque.util.PersistenceMechanismRDBMS;
import br.com.ceut.controleestoque.util.RepositorioException;

public class RepositorioFuncionariosBDR implements RepositorioFuncionarios {

	private static RepositorioFuncionariosBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public RepositorioFuncionariosBDR() {
        try {
            pm = PersistenceMechanismRDBMS.getInstance();
            pm.connect();
        }
        catch (Exception ex) {
            //System.out.println(ex.getMessage()); 
            ex.printStackTrace();
        }
	}
	
    public synchronized static RepositorioFuncionariosBDR getInstance() {
        if (instance == null) {
            instance = new RepositorioFuncionariosBDR();
        }
        return instance;
    }

    @Override
	public void inserir(Funcionario funcionario) throws RepositorioException {
    	try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            statement.executeUpdate(
            		String.format("INSERT INTO root_funcionarios VALUES " +
            				"('%s', %s, '%s', '%s', '%s', %s, %s, '%s')",
            				funcionario.getNome(),
            				trataData(funcionario.getDataCadastro()),
            				funcionario.getEndereco(),
            				funcionario.getMatricula(),
            				funcionario.getCPF(),
            				trataData(funcionario.getDataAdmissao()),
            				trataData(funcionario.getDataDemissao()),
            				funcionario.isAtivo() ? "1" : "0"));
        } catch(PersistenceMechanismException e){
            throw new RepositorioException(e);
        } catch (SQLException e) {
            throw new RepositorioException(e);
	    } finally {
		    try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositorioException(ex);
			}
        }		
	}

	@Override
	public void remover(String matricula)
			throws FuncionarioNaoEncontradoException, RepositorioException {
        try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM root_funcionarios WHERE matricula='" + matricula + "'");
            if (i == 0) {
            	throw new FuncionarioNaoEncontradoException(matricula);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositorioException(e);
		} catch(SQLException e){
            throw new RepositorioException(e);            
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositorioException(ex);
			}
		}
	}

	private Funcionario montaFuncionario(ResultSet resultSet) throws SQLException {
        Funcionario funcionario = new Funcionario(resultSet.getString("matricula"));
        funcionario.setNome(resultSet.getString("nome"));
        funcionario.setDataCadastro(resultSet.getDate("datacadastro"));
        funcionario.setEndereco(resultSet.getString("endereco"));
        funcionario.setCPF(resultSet.getString("cpf"));
        funcionario.setDataAdmissao(resultSet.getDate("dataAdmissao"));
        funcionario.setDataDemissao(resultSet.getDate("dataDemissao"));
        funcionario.setAtivo(resultSet.getString("ativo") != null && resultSet.getString("ativo").equals("1"));
        return funcionario;
	}
	
	@Override
	public Funcionario procurar(String matricula)
			throws FuncionarioNaoEncontradoException, RepositorioException {
		Funcionario funcionario = null;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM root_funcionarios WHERE matricula = '" + matricula + "'");
            if (resultSet.next()) {
            	funcionario = montaFuncionario(resultSet);
            } else {
            	throw new FuncionarioNaoEncontradoException(matricula);
            }
			resultSet.close();
		} catch(PersistenceMechanismException e){
            throw new RepositorioException(e);
        } catch (SQLException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositorioException(ex);
			}
		}
		return funcionario;
	}

	private String trataData(Date date) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String strData = "null";
		if (date != null) {
			strData = "'" + dateFormat.format(date) + "'";
		}
		return strData;
	}
	
	@Override
	public void atualizar(Funcionario funcionario)
			throws FuncionarioNaoEncontradoException, RepositorioException {
	    try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
            int i = statement.executeUpdate(String.format(
            		"UPDATE root_funcionarios SET " +
            		"nome = '%s', " +
            		"datacadastro = %s, " +
            		"endereco = '%s', " +
            		"matricula = '%s', " +
            		"cpf = '%s', " +
            		"dataAdmissao = %s, " +
            		"dataDemissao = %s, " +
            		"ativo = '%s' where matricula = '%s'",
    				funcionario.getNome(),
    				trataData(funcionario.getDataCadastro()),
    				funcionario.getEndereco(),
    				funcionario.getMatricula(),
    				funcionario.getCPF(),
    				trataData(funcionario.getDataAdmissao()),
    				trataData(funcionario.getDataDemissao()),
    				funcionario.isAtivo() ? "1" : "0",
    				funcionario.getMatricula()));
            if (i == 0) {
            	throw new FuncionarioNaoEncontradoException(funcionario.getMatricula());
            }
		} catch(PersistenceMechanismException e){
            throw new RepositorioException(e);
		} catch (SQLException e) {
		    throw new RepositorioException(e);
	    } finally {
	    	try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositorioException(ex);
			}
		}
	}

	@Override
	public boolean existe(String matricula) throws RepositorioException {
        boolean resposta = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM root_funcionarios WHERE matricula = '" + matricula + "'");
            resposta = resultset.next();
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositorioException(e);
        } catch (SQLException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositorioException(ex);
			}
		}
        return resposta;
	}

	@Override
	public RepositorioFuncionarios getFuncionarios()
			throws RepositorioException {
        RepositorioFuncionarios resposta = new RepositorioFuncionariosArray();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM root_funcionarios");
            while (resultSet.next()) {
                resposta.inserir(montaFuncionario(resultSet));
            }
			resultSet.close();
		} catch(PersistenceMechanismException e){
            throw new RepositorioException(e);
        } catch (SQLException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositorioException(ex);
			}
		}
        return resposta;
	}

	@Override
	public IteratorFuncionarios getIterator() throws RepositorioException {
		return this.getFuncionarios().getIterator();
	}

}
