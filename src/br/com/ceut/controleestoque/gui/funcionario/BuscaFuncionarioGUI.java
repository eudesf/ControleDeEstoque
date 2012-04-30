package br.com.ceut.controleestoque.gui.funcionario;

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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.ceut.controleestoque.funcionario.CadastroFuncionarios;
import br.com.ceut.controleestoque.funcionario.Funcionario;
import br.com.ceut.controleestoque.funcionario.FuncionarioJaCadastradoException;
import br.com.ceut.controleestoque.funcionario.FuncionarioNaoEncontradoException;
import br.com.ceut.controleestoque.funcionario.IteratorFuncionarios;
import br.com.ceut.controleestoque.funcionario.RepositorioFuncionariosArray;
import br.com.ceut.controleestoque.util.RepositorioException;
import javax.swing.JScrollPane;

public class BuscaFuncionarioGUI extends JFrame {

	private static final long serialVersionUID = 3272385599907416L;

	private CadastroFuncionarios cadastroFuncionarios;
	
	private JPanel contentPane;
	private JTable table;
	private List<Funcionario> funcionariosReference;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaFuncionarioGUI frame = new BuscaFuncionarioGUI(new CadastroFuncionarios(new RepositorioFuncionariosArray()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws RepositorioException 
	 */
	public BuscaFuncionarioGUI(CadastroFuncionarios cadastroFuncionarios) throws RepositorioException {
		this.cadastroFuncionarios = cadastroFuncionarios;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		
		JScrollPane scroll = new JScrollPane(table);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		refreshTable();
		setLocationRelativeTo(null);
	}

	private void refreshTable() {
		try {
			IteratorFuncionarios iterator = cadastroFuncionarios.getFuncionarios().getIterator();

			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Matrícula");
			columnNames.add("Nome");
			columnNames.add("Endereço");
			columnNames.add("Data Admissão");
			columnNames.add("Data Demissão");
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
				funcionarioData.add(funcionario.isAtivo() ? "Sim" : "Não");
				data.add(funcionarioData);
			}
			table.setModel(new DefaultTableModel(data, columnNames));
		} catch (RepositorioException ex) {
			JOptionPane.showMessageDialog(this, "Foi encontrado um erro de comunicação com o repositório de dados!");
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
				JOptionPane.showMessageDialog(this, "Funcionário Já Cadastrado!");
			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(this, "Não foi possível cadastrar este funcionário!");
			}
		}
	}
	
	private void editar() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o funcionário a ser editado!");
		} else {
			CadastroFuncionarioGUI gui = new CadastroFuncionarioGUI(funcionariosReference.get(table.getSelectedRow()));
			if (gui.getFuncionario() != null) {
				gui.getMatriculaField().setEnabled(false);
				gui.setVisible(true);
				try {
					cadastroFuncionarios.atualizar(gui.getFuncionario());
				} catch (FuncionarioNaoEncontradoException e) {
					JOptionPane.showMessageDialog(this, "Funcionário não encontrado!");
				} catch (RepositorioException e) {
					JOptionPane.showMessageDialog(this, "Não foi possível alterar este funcionário");
				}
				refreshTable();
			}
		}
	}
	
	private void remover() {
		try {
			cadastroFuncionarios.remover(funcionariosReference.get(table.getSelectedRow()).getMatricula());
			refreshTable();
		} catch (FuncionarioNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, "O funcionário selecionado não foi encontrado!");
		} catch (RepositorioException e) {
			JOptionPane.showMessageDialog(this, "Não foi possível remover este funcionário!");
		}
	}
}
