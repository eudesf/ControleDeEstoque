package br.com.ceut.controleestoque.gui.funcionario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.ceut.controleestoque.funcionarios.Funcionario;

public class CadastroFuncionarioGUI extends JDialog {

	private static final long serialVersionUID = 4929460184522905721L;

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField enderecoField;
	private JTextField matriculaField;
	private JTextField dataAdmissaoField;
	private JTextField dataDemissaoField;
	private JCheckBox ativoCheck;
	
	private Funcionario funcionario;
	private JTextField cpfField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionarioGUI frame = new CadastroFuncionarioGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroFuncionarioGUI() {
		setTitle("Cadastro de Funcion\u00E1rio");
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
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		
		
		panel.add(btnOK);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionarioGUI.this.dispose();
			}
		});
		panel.add(btnSair);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(17, 56, 41, 16);
		panel_1.add(lblNome);
		
		nomeField = new JTextField();
		nomeField.setBounds(121, 50, 270, 28);
		panel_1.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(17, 112, 61, 16);
		panel_1.add(lblEndereo);
		
		enderecoField = new JTextField();
		enderecoField.setBounds(121, 106, 270, 28);
		panel_1.add(enderecoField);
		enderecoField.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(17, 28, 71, 16);
		panel_1.add(lblMatrcula);
		
		matriculaField = new JTextField();
		matriculaField.setBounds(121, 22, 134, 28);
		panel_1.add(matriculaField);
		matriculaField.setColumns(10);
		
		JLabel lblDataAdmisso = new JLabel("Data Admiss\u00E3o:");
		lblDataAdmisso.setBounds(17, 142, 99, 16);
		panel_1.add(lblDataAdmisso);
		
		JLabel lblDataDemisso = new JLabel("Data Demiss\u00E3o:");
		lblDataDemisso.setBounds(17, 170, 99, 16);
		panel_1.add(lblDataDemisso);
		
		dataAdmissaoField = new JTextField();
		dataAdmissaoField.setBounds(121, 136, 134, 28);
		panel_1.add(dataAdmissaoField);
		dataAdmissaoField.setColumns(10);
		
		dataDemissaoField = new JTextField();
		dataDemissaoField.setBounds(121, 164, 134, 28);
		panel_1.add(dataDemissaoField);
		dataDemissaoField.setColumns(10);
		
		ativoCheck = new JCheckBox("Ativo");
		ativoCheck.setSelected(true);
		ativoCheck.setBounds(6, 198, 128, 23);
		panel_1.add(ativoCheck);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(17, 84, 61, 16);
		panel_1.add(lblNewLabel);
		
		cpfField = new JTextField();
		cpfField.setBounds(121, 77, 134, 28);
		panel_1.add(cpfField);
		cpfField.setColumns(10);

		setLocationRelativeTo(null);
	}
	
	public CadastroFuncionarioGUI(Funcionario funcionario) {
		this();
		if (funcionario != null) {
			publicaFuncionarioTela(funcionario);
		}
	}
	
	private void publicaFuncionarioTela(Funcionario funcionario) {
		DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);

		matriculaField.setText(funcionario.getMatricula());
		cpfField.setText(funcionario.getCPF());
		nomeField.setText(funcionario.getNome());
		enderecoField.setText(funcionario.getEndereco());
		if (funcionario.getDataAdmissao() != null) {
			dataAdmissaoField.setText(dateFormat.format(funcionario.getDataAdmissao()));
		}
		if (funcionario.getDataDemissao() != null) {
			dataDemissaoField.setText(dateFormat.format(funcionario.getDataDemissao()));
		}
		ativoCheck.setSelected(funcionario.isAtivo());
	}
	
	private void cadastrar() {
		if (matriculaField.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "ƒ necess‡rio digitar a matr’cula!");
			return;
		}

		DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
		
		funcionario = new Funcionario(matriculaField.getText());
		funcionario.setNome(nomeField.getText());
		funcionario.setCPF(cpfField.getText());
		funcionario.setEndereco(enderecoField.getText());
		if (dataAdmissaoField.getText().trim().length() > 0) {
			try {
				funcionario.setDataAdmissao(dateFormat.parse(dataAdmissaoField.getText()));
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Data de admiss‹o inv‡lida");
				return;
			}
		}
		if (dataDemissaoField.getText().trim().length() > 0) {
			try {
				funcionario.setDataDemissao(dateFormat.parse(dataDemissaoField.getText()));
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Data de demiss‹o inv‡lida");
				return;
			}
		}
		funcionario.setAtivo(ativoCheck.isSelected());
		dispose();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	JTextField getMatriculaField() {
		return matriculaField;
	}
}
