package br.com.ceut.controleestoque.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.ceut.controleestoque.funcionarios.CadastroFuncionarios;
import br.com.ceut.controleestoque.funcionarios.Funcionario;
import br.com.ceut.controleestoque.funcionarios.FuncionarioJaCadastradoException;
import br.com.ceut.controleestoque.funcionarios.FuncionarioNaoEncontradoException;
import br.com.ceut.controleestoque.funcionarios.IteratorFuncionarios;
import br.com.ceut.controleestoque.funcionarios.RepositorioFuncionariosArray;
import br.com.ceut.controleestoque.util.RepositorioException;

public class BuscaFuncionarioGUI extends JFrame {

	private static final long serialVersionUID = 3272385599907416L;

	private CadastroFuncionarios cadastroFuncionarios;
	
	private JPanel contentPane;
	private JTable table;
	private List<Funcionario> funcionariosReference;

		/**
	 * Create the frame.
	 * @throws RepositorioException 
	 */
	public BuscaFuncionarioGUI(CadastroFuncionarios cadastroFuncionarios) throws RepositorioException {
		this.cadastroFuncionarios = cadastroFuncionarios;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		panel.add(btnCadastrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		panel.add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		panel.add(btnRemover);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaFuncionarioGUI.this.dispose();
			}
		});
		panel.add(btnSair);
		
		
		table = new JTable() {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scroll = new JScrollPane(table);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		refreshTable();
		setLocationRelativeTo(null);
	}

	private void refreshTable() {
		try {
			IteratorFuncionarios iterator = cadastroFuncionarios.getFuncionarios().getIterator();

			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Matricula");
			columnNames.add("Nome");
			columnNames.add("Endere�o");
			columnNames.add("Data Admiss�o");
			columnNames.add("Data Demiss�o");
			columnNames.add("Ativo?");
		
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			funcionariosReference = new ArrayList<Funcionario>();
			while (iterator.hasNext()) {
				Funcionario funcionario = iterator.next();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				funcionariosReference.add(funcionario);
				Vector<String> funcionarioData = new Vector<String>();
				funcionarioData.add(funcionario.getMatricula());
				funcionarioData.add(funcionario.getNome());
				funcionarioData.add(funcionario.getEndereco());
				if (funcionario.getDataAdmissao() == null) {
					funcionarioData.add("");
				} else {
					funcionarioData.add(dateFormat.format(funcionario.getDataAdmissao()));
				}
				if (funcionario.getDataDemissao() == null) {
					funcionarioData.add("");
				} else {
					funcionarioData.add(dateFormat.format(funcionario.getDataDemissao()));
				}
				funcionarioData.add(funcionario.isAtivo() ? "Sim" : "N�o");
				data.add(funcionarioData);
			}
			table.setModel(new DefaultTableModel(data, columnNames));
		} catch (RepositorioException ex) {
			JOptionPane.showMessageDialog(this, "Foi encontrado um erro de comunica��o com o reposit�rio de dados!");
		}
	}
	
	private void cadastrar() {
		CadastroFuncionarioGUI gui = new CadastroFuncionarioGUI();
		gui.setVisible(true);
		if (gui.getFuncionario() != null) {
			try {
				cadastroFuncionarios.cadastrar(gui.getFuncionario());
				refreshTable();
			} catch (FuncionarioJaCadastradoException e) {
				JOptionPane.showMessageDialog(this, "Funcion�rio J� Cadastrado!");
			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(this, "N�o foi possivel cadastrar este funcion�rio!");
			}
		}
	}
	
	private void editar() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o funcion�rio a ser editado!");
		} else {
			CadastroFuncionarioGUI gui = new CadastroFuncionarioGUI(funcionariosReference.get(table.getSelectedRow()));
			gui.getMatriculaField().setEnabled(false);
			gui.setVisible(true);
			if (gui.getFuncionario() != null) {
				try {
					cadastroFuncionarios.atualizar(gui.getFuncionario());
				} catch (FuncionarioNaoEncontradoException e) {
					JOptionPane.showMessageDialog(this, "Funcion�rio n�o encontrado!");
				} catch (RepositorioException e) {
					JOptionPane.showMessageDialog(this, "N�o foi possivel alterar este funcion�rio");
				}
				refreshTable();
			}
		}
	}
	
	private void remover() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o funcion�rio a ser removido!");
		} else {
			try {
				cadastroFuncionarios.remover(funcionariosReference.get(table.getSelectedRow()).getMatricula());
				refreshTable();
			} catch (FuncionarioNaoEncontradoException e) {
				JOptionPane.showMessageDialog(this, "O funcion�rio selecionado n�o foi encontrado!");
			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(this, "N�o foi possivel remover este funcion�rio!");
			}
		}
	}
}
