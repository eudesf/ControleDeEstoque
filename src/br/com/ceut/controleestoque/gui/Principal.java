package br.com.ceut.controleestoque.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.ceut.controleestoque.fachada.ControleEstoque;
import br.com.ceut.controleestoque.fornecedores.CadastroFornecedores;
import br.com.ceut.controleestoque.funcionarios.CadastroFuncionarios;
import br.com.ceut.controleestoque.util.RepositorioException;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	private ControleEstoque controleEstoque = new ControleEstoque();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setResizable(false);
		setTitle("Controle de Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPrincipal = new JMenu("Principal");
		menuBar.add(mnPrincipal);

		JMenuItem mntmManipularProdutos = new JMenuItem("Manipular Produtos");
		mntmManipularProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProdutosGUI cadastroProdutosGUI = new CadastroProdutosGUI(controleEstoque);
				cadastroProdutosGUI.setVisible(true);
			}
		});
		mnPrincipal.add(mntmManipularProdutos);

		JMenuItem mntmManipularLoja = new JMenuItem("Manipular Loja");
		mntmManipularLoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LojasGUI lojasGUI = new LojasGUI();
				lojasGUI.setVisible(true);
			}
		});
		mnPrincipal.add(mntmManipularLoja);

		JMenuItem mntmManipularFornecedores = new JMenuItem(
				"Manipular Fornecedores");
		mntmManipularFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BuscaFornecedorGUI buscaFornecedorGUI = new BuscaFornecedorGUI(new CadastroFornecedores(controleEstoque.getFornecedores()));
					buscaFornecedorGUI.setVisible(true);
				} catch (RepositorioException e) {
					 
				}
			}
		});
		mnPrincipal.add(mntmManipularFornecedores);

		JMenuItem mntmManipularFuncionrios = new JMenuItem(
				"Manipular Funcion\u00E1rios");
		mntmManipularFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BuscaFuncionarioGUI buscaFuncionarioGUI = new BuscaFuncionarioGUI(new CadastroFuncionarios(controleEstoque.getFuncionarios()));
					buscaFuncionarioGUI.setVisible(true);
				} catch (RepositorioException e) {
					
				}
			}
		});
		mnPrincipal.add(mntmManipularFuncionrios);
		
		JSeparator separator = new JSeparator();
		mnPrincipal.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnPrincipal.add(mntmSair);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobreOPrograma = new JMenuItem("Desenvolvedores");
		mntmSobreOPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		mnSobre.add(mntmSobreOPrograma);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addGap(15).addGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE,
										484, Short.MAX_VALUE).addComponent(
										panel_1, GroupLayout.DEFAULT_SIZE, 484,
										Short.MAX_VALUE)).addGap(5)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addGap(75).addComponent(
						panel, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap(
								104, Short.MAX_VALUE)));

		JLabel label_1 = new JLabel(
				"Para acessar as funcionalidades do sistema clique no menu PRINCIPAL");
		panel_1.add(label_1);

		JLabel label = new JLabel("Bem vindo ao sistema de Controle de Estoque");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label);
		contentPane.setLayout(gl_contentPane);
	}
}
