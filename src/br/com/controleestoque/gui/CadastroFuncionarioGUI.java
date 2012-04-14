package br.com.controleestoque.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.controleestoque.banco.CadastroFuncionario;
import br.com.controleestoque.banco.Funcionario;
import br.com.controleestoque.dados.FuncionarioJaCadastrado;

public class CadastroFuncionarioGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 7360122424353419645L;

	private JTextField matriculaField = new JTextField(40);
	private JTextField nomeField = new JTextField();
	private JTextField enderecoField = new JTextField();
	
	private JButton cadastrarButton = new JButton("Cadastrar");
	private JButton sairButton = new JButton("Sair");

	private CadastroFuncionario cadastroFuncionario = new CadastroFuncionario();
	
	public CadastroFuncionarioGUI() {
		super("Cadastro de Funcionario");
		setSize(300, 240);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		cadastrarButton.addActionListener(this);
		sairButton.addActionListener(this);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		formPanel.add(new JLabel("Matrícula:"));
		formPanel.add(matriculaField);
		formPanel.add(new JLabel("Nome:"));
		formPanel.add(nomeField);
		formPanel.add(new JLabel("Endereço:"));
		formPanel.add(enderecoField);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(cadastrarButton);
		buttonsPanel.add(sairButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		add(mainPanel);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroFuncionarioGUI().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sairButton) {
			dispose();
		} else if (e.getSource() == cadastrarButton) {
			Funcionario funcionario = new Funcionario();
			funcionario.setMatricula(matriculaField.getText());
			funcionario.setNome(nomeField.getText());
			funcionario.setEndereco(enderecoField.getText());
			try {
				cadastroFuncionario.cadastrar(funcionario);
			} catch (FuncionarioJaCadastrado e1) {
				JOptionPane.showMessageDialog(this, "Funcionário já cadastrado!");
			}
		}
	}
}
