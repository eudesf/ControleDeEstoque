package br.com.ceut.controleestoque.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.ceut.controleestoque.fornecedores.Fornecedor;

public class CadastroFornecedorGUI extends JDialog {

	private static final long serialVersionUID = 4929460184522905721L;

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField enderecoField;
	private JTextField codigoField;
	private JTextField dataCadastroField;
	
	private Fornecedor fornecedor;
	private JTextField cnpjField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFornecedorGUI frame = new CadastroFornecedorGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroFornecedorGUI() {
		setTitle("Cadastro de Fornecedor");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("Cadastra");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		
		panel.add(btnOK);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFornecedorGUI.this.dispose();
			}
		});
		
		panel.add(btnSair);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(21, 67, 41, 16);
		panel_1.add(lblNome);
		
		nomeField = new JTextField();
		nomeField.setBounds(118, 61, 270, 28);
		panel_1.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(21, 144, 61, 16);
		panel_1.add(lblEndereco);
		
		enderecoField = new JTextField();
		enderecoField.setBounds(118, 138, 270, 28);
		panel_1.add(enderecoField);
		enderecoField.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(21, 28, 71, 16);
		panel_1.add(lblCodigo);
		
		codigoField = new JTextField();
		codigoField.setBounds(118, 22, 134, 28);
		panel_1.add(codigoField);
		codigoField.setColumns(10);
		
		JLabel lblDataCadastro = new JLabel("Data Cadastro:");
		lblDataCadastro.setBounds(20, 183, 99, 16);
		panel_1.add(lblDataCadastro);
		
		dataCadastroField = new JTextField();
		dataCadastroField.setBounds(118, 177, 134, 28);
		panel_1.add(dataCadastroField);
		dataCadastroField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CNPJ:");
		lblNewLabel.setBounds(21, 105, 61, 16);
		panel_1.add(lblNewLabel);
		
		cnpjField = new JTextField();
		cnpjField.setBounds(118, 99, 134, 28);
		panel_1.add(cnpjField);
		cnpjField.setColumns(10);
		
		JLabel lblExDdmmaaaa = new JLabel("Ex.: DD/MM/AAAA");
		lblExDdmmaaaa.setBounds(278, 183, 134, 16);
		panel_1.add(lblExDdmmaaaa);

		setLocationRelativeTo(null);
	}
	
	public CadastroFornecedorGUI(Fornecedor fornecedor) {
		this();
		if (fornecedor != null) {
			publicaFornecedorTela(fornecedor);
		}
	}

	private void publicaFornecedorTela(Fornecedor fornecedor) {
		DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);

		codigoField.setText(fornecedor.getCodigo());
		cnpjField.setText(fornecedor.getCNPJ());
		nomeField.setText(fornecedor.getNome());
		enderecoField.setText(fornecedor.getEndereco());
		if (fornecedor.getDataCadastro() != null) {
			dataCadastroField.setText(dateFormat.format(fornecedor.getDataCadastro()));
		}
	}
	
	private void cadastrar() {
		if (codigoField.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Digite um código!");
			return;
		}

		DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
		
		fornecedor = new Fornecedor(codigoField.getText());
		fornecedor.setNome(nomeField.getText());
		fornecedor.setCNPJ(cnpjField.getText());
		fornecedor.setEndereco(enderecoField.getText());
		if (dataCadastroField.getText().trim().length() > 0) {
			try {
				fornecedor.setDataCadastro(dateFormat.parse(dataCadastroField.getText()));
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Data inválida!");
				return;
			}
		}
		dispose();
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	JTextField getCodigoField() {
		return codigoField;
	}
}
