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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.ceut.controleestoque.fornecedores.CadastroFornecedores;
import br.com.ceut.controleestoque.fornecedores.Fornecedor;
import br.com.ceut.controleestoque.fornecedores.FornecedorJaCadastradoException;
import br.com.ceut.controleestoque.fornecedores.FornecedorNaoEncontradoException;
import br.com.ceut.controleestoque.fornecedores.IteratorFornecedores;
import br.com.ceut.controleestoque.fornecedores.RepositorioFornecedoresArray;
import br.com.ceut.controleestoque.util.RepositorioException;

public class BuscaFornecedorGUI extends JFrame {

	private static final long serialVersionUID = 3272385599907416L;

	private CadastroFornecedores cadastroFornecedores;

	private JPanel contentPane;
	private JTable table;
	private List<Fornecedor> fornecedoresReference;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaFornecedorGUI frame = new BuscaFornecedorGUI(
							new CadastroFornecedores(
									new RepositorioFornecedoresArray()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BuscaFornecedorGUI(CadastroFornecedores cadastroFornecedores)
			throws RepositorioException {
		this.cadastroFornecedores = cadastroFornecedores;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 296);
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
		// ----------------------------------------------------------
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscaFornecedorGUI.this.dispose();
			}
		});
		panel.add(btnSair);

		table = new JTable();
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane(table);
		contentPane.add(scroll, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel lblDigiteUmCdigo = new JLabel("Digite um c\u00F3digo");
		panel_1.add(lblDigiteUmCdigo);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		// -------------------------------------------------------
		JButton btnPesquisar = new JButton("Pesquisar");
		panel_1.add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});

		refreshTable();
		setLocationRelativeTo(null);
	}

	private void refreshTable() {
		try {
			IteratorFornecedores iterator = cadastroFornecedores
					.getFornecedores().getIterator();

			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Código");
			columnNames.add("CNPJ");
			columnNames.add("Nome");
			columnNames.add("Endereço");
			columnNames.add("Data Cadastro");

			Vector<Vector<String>> data = new Vector<Vector<String>>();
			fornecedoresReference = new ArrayList<Fornecedor>();
			while (iterator.hasNext()) {
				Fornecedor fornecedor = iterator.next();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				fornecedoresReference.add(fornecedor);
				Vector<String> fornecedorData = new Vector<String>();
				fornecedorData.add(fornecedor.getCodigo());
				fornecedorData.add(fornecedor.getCNPJ());
				fornecedorData.add(fornecedor.getNome());
				fornecedorData.add(fornecedor.getEndereco());
				if (fornecedor.getDataCadastro() == null) {
					fornecedorData.add("");
				} else {
					fornecedorData.add(dateFormat.format(fornecedor
							.getDataCadastro()));
				}
				data.add(fornecedorData);
			}
			table.setModel(new DefaultTableModel(data, columnNames));
		} catch (RepositorioException ex) {
			JOptionPane.showMessageDialog(this,
					"Falha na comunicação com o repositório de dados!");
		}
	}

	private void cadastrar() {
		CadastroFornecedorGUI gui = new CadastroFornecedorGUI();
		gui.setVisible(true);
		if (gui.getFornecedor() != null) {
			try {
				cadastroFornecedores.cadastrar(gui.getFornecedor());
				refreshTable();
			} catch (FornecedorJaCadastradoException e) {
				JOptionPane
						.showMessageDialog(this, "Fornecedor já cadastrado!");
			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(this,
						"Não foi possível cadastrar o fornecedor!");
			}
		}
	}

	private void editar() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o fornecedor!");
		} else {
			CadastroFornecedorGUI gui = new CadastroFornecedorGUI(
					fornecedoresReference.get(table.getSelectedRow()));
			gui.getCodigoField().setEnabled(false);
			gui.setVisible(true);
			try {
				cadastroFornecedores.atualizar(gui.getFornecedor());
			} catch (FornecedorNaoEncontradoException e) {

			} catch (RepositorioException e) {
				e.printStackTrace();
			}
			refreshTable();
		}
	}

	// -------------------------------------------------------------------------
	private void pesquisar() {
		String codigo = textField.getText();
		if (codigo.equals("")) {
			JOptionPane.showMessageDialog(this,
					"Informe o código do fornecedor.");
		} else {
			try {
				cadastroFornecedores.procurar(codigo);
				JOptionPane.showMessageDialog(this, "Código Encontrado: "
						+ codigo);

			} catch (FornecedorNaoEncontradoException e) {
				JOptionPane.showMessageDialog(this,
						"Fornecedor não encontrado!");

			} catch (RepositorioException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// -------------------------------------------------------------------------
	private void remover() {

		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o fornecedor!");

		} else {
			try {
				cadastroFornecedores.remover(fornecedoresReference.get(
						table.getSelectedRow()).getCodigo());
				refreshTable();
			} catch (FornecedorNaoEncontradoException e) {
				JOptionPane.showMessageDialog(this,
						"Fornecedor não encontrado!");

			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(this,
						"Não foi possível excluir o fornecedor!");
			}
		}
	}
}
