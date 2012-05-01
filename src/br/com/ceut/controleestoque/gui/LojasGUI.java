package br.com.ceut.controleestoque.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import br.com.ceut.controleestoque.fachada.ControleEstoque;
import br.com.ceut.controleestoque.fornecedores.Fornecedor;
import br.com.ceut.controleestoque.fornecedores.FornecedorNaoEncontradoException;
import br.com.ceut.controleestoque.funcionarios.Funcionario;
import br.com.ceut.controleestoque.funcionarios.FuncionarioNaoEncontradoException;
import br.com.ceut.controleestoque.lojas.IteratorLojasArray;
import br.com.ceut.controleestoque.lojas.Loja;
import br.com.ceut.controleestoque.lojas.LojaJaCadastradaException;
import br.com.ceut.controleestoque.lojas.LojaNaoEncontradaException;
import br.com.ceut.controleestoque.util.RepositorioException;


@SuppressWarnings("serial")
public class LojasGUI extends JFrame {
//	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private String descricao;
	private String codigo;
	private String nomeFuncionario;
    private String nomeFornecedor;
    private Loja loja;
    private Funcionario funcionario;
    private Fornecedor fornecedor;
    private ControleEstoque controleEstoque;
    
//    CadastroLojas lojas = new CadastroLojas();
//    CadastroFuncionarios funcionarios = new CadastroFuncionarios();
//    CadastroFornecedores fornecedores = new CadastroFornecedores();
    
    DefaultListModel model = new DefaultListModel();
    JList list = new JList(model);
    DefaultListModel model_1 = new DefaultListModel();
    JList list_1 = new JList(model_1);
    DefaultListModel model_2 = new DefaultListModel();
    JList list_2 = new JList(model_2);
    DefaultListModel model_3 = new DefaultListModel();
    JList list_3 = new JList(model_3);
    
    String[] nomesFuncionarios;
    String[] nomesFornecedores;
    private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LojasGUI frame = new LojasGUI();
					frame.setVisible(true);
//					System.out.println("8 "+frame);
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
	public LojasGUI() {
		controleEstoque = new ControleEstoque();
		setTitle("Cadastro de Lojas");
		setResizable(false);
//		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 490);
		getContentPane().setLayout(null);
		
//		((JComponent) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
//		setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(0, 0, 664, 45);
		getContentPane().add(panelBotoes);
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					novo();
				} catch (RepositorioException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		
		JButton btnConsultas = new JButton("Gravar");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrar();
				} catch (FuncionarioNaoEncontradoException e1) {
					System.out.println(e1.getMessage());
				} catch (FornecedorNaoEncontradoException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		
		JButton btnMovimentaes = new JButton("Alterar");
		btnMovimentaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alterar();
				} catch (LojaNaoEncontradaException e1) {
					System.out.println(e1.getMessage());
				} catch (FuncionarioNaoEncontradoException e1) {
					System.out.println(e1.getMessage());
				} catch (FornecedorNaoEncontradoException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		
		JButton btnSair = new JButton("Excluir");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excluir();
				} catch (LojaNaoEncontradaException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		GroupLayout gl_panelBotoes = new GroupLayout(panelBotoes);
		gl_panelBotoes.setHorizontalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addGap(196)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnConsultas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMovimentaes)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair)
					.addContainerGap(196, Short.MAX_VALUE))
		);
		gl_panelBotoes.setVerticalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBotoes.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnConsultas)
						.addComponent(btnMovimentaes)
						.addComponent(btnSair))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelBotoes.linkSize(SwingConstants.VERTICAL, new Component[] {btnNewButton, btnConsultas, btnMovimentaes, btnSair});
		panelBotoes.setLayout(gl_panelBotoes);
		
		JPanel panelRegistro = new JPanel();
		panelRegistro.setBounds(0, 56, 674, 229);
		getContentPane().add(panelRegistro);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblFuncionrios = new JLabel("Funcion\u00E1rios");
		
		JLabel lblFornecedores = new JLabel("Fornecedores");
		
		//carregar lista de funcionarios
		try {
			carregarDadosFuncionarios();
		} catch (RepositorioException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					for(Object obj:list.getSelectedValues()){
						System.out.println(obj.toString());
							model.removeElement(obj);
							model_1.addElement(obj);	
					}
				
			}
		});
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					for(Object obj:list_1.getSelectedValues()){
						System.out.println(obj.toString());
							model_1.removeElement(obj);
							model.addElement(obj);
					}
				
			}
		});
		
		//carregar lista de fornecedores
		try {
			carregarDadosFornecedores();
		} catch (RepositorioException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
		JButton button_2 = new JButton(">");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Object obj:list_2.getSelectedValues()){
					System.out.println(obj.toString());
						model_2.removeElement(obj);
						model_3.addElement(obj);	
				}
			}
		});
		
		JButton button_3 = new JButton("<");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Object obj:list_3.getSelectedValues()){
					System.out.println(obj.toString());
						model_3.removeElement(obj);
						model_2.addElement(obj);	
				}
			}
		});
//		System.out.println("1");
		
		JButton button_4 = new JButton(">>");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Object obj:nomesFuncionarios){
					System.out.println(obj.toString());
						model.removeElement(obj);
						if(!model_1.contains(obj))
							model_1.addElement(obj);
				}
			}
		});
		
		JButton button_5 = new JButton("<<");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Object obj:nomesFuncionarios){
					System.out.println(obj.toString());
						model_1.removeElement(obj);
						if(!model.contains(obj))
							model.addElement(obj);	
				}
			}
		});
		
		JButton button_6 = new JButton(">>");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Object obj:nomesFornecedores){
					System.out.println(obj.toString());
						model_2.removeElement(obj);
						if(!model_3.contains(obj))
							model_3.addElement(obj);	
				}
			}
		});
		
		JButton button_7 = new JButton("<<");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Object obj:nomesFornecedores){
					System.out.println(obj.toString());
						model_3.removeElement(obj);
						if(!model_2.contains(obj))
							model_2.addElement(obj);	
				}
			}
		});
		
		JLabel lblProcurar = new JLabel("Descri\u00E7\u00E3o");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				descricao = textField_2.getText();
				String erro = "";
				
				if(descricao == null || descricao.isEmpty())
					erro += "\nCampo obrigatório: descrição";
				
				if(!erro.isEmpty())
					JOptionPane.showMessageDialog(null,
						    erro,
						    "Atenção",
						    JOptionPane.WARNING_MESSAGE);
				else{
					try {
						loja = controleEstoque.procurarLoja(descricao);
					} catch (LojaJaCadastradaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (RepositorioException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (LojaNaoEncontradaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					if(loja!=null){
						removeTodasLinhas();
						adicionaLinha(loja.getCodigo(), loja.getDescricao());
					}else
						JOptionPane.showMessageDialog(null, 
								"A pesquisa não gerou nenhum resultado! Tente novamente.", 
								"Atenção",
							    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				IteratorLojasArray lojasIterator = new IteratorLojasArray(lojas.listarTudo());
					IteratorLojasArray lojasIterator = null;
					try {
						lojasIterator = new IteratorLojasArray(controleEstoque.listarTudoLojas());
					} catch (RepositorioException e1) {
						System.out.println(e1.getMessage());
					}
					
					if(lojasIterator!=null && lojasIterator.hasNext()){
						removeTodasLinhas();
		        		  while(lojasIterator.hasNext()){
//	        			System.out.println("Loja: "+lojasIterator.next().getDescricao());
		        			adicionaLinha(lojasIterator.next().getCodigo(), lojasIterator.next().getDescricao());
		        		  }
	        		  try {
						novo();
					} catch (RepositorioException e1) {
						System.out.println(e1.getMessage());
					}
				}

			}
		});
		GroupLayout gl_panelRegistro = new GroupLayout(panelRegistro);
		gl_panelRegistro.setHorizontalGroup(
			gl_panelRegistro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegistro.createSequentialGroup()
					.addGroup(gl_panelRegistro.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelRegistro.createSequentialGroup()
							.addGroup(gl_panelRegistro.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCdigo)
								.addComponent(lblDescrio))
							.addGap(18)
							.addGroup(gl_panelRegistro.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelRegistro.createSequentialGroup()
							.addGroup(gl_panelRegistro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRegistro.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblProcurar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnListarTodos))
								.addGroup(gl_panelRegistro.createSequentialGroup()
									.addComponent(lblFuncionrios)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelRegistro.createParallelGroup(Alignment.LEADING)
										.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
										.addComponent(button, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
										.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
										.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblFornecedores)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_panelRegistro.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_6)
								.addGroup(gl_panelRegistro.createParallelGroup(Alignment.LEADING, false)
									.addComponent(button_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(button_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(50)))
					.addContainerGap())
		);
		gl_panelRegistro.setVerticalGroup(
			gl_panelRegistro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegistro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRegistro.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCdigo))
					.addGap(7)
					.addGroup(gl_panelRegistro.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio))
					.addGap(11)
					.addGroup(gl_panelRegistro.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelRegistro.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFuncionrios)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panelRegistro.createSequentialGroup()
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addGap(3)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblFornecedores))
						.addGroup(gl_panelRegistro.createParallelGroup(Alignment.BASELINE)
							.addGroup(gl_panelRegistro.createSequentialGroup()
								.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addGap(7)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addGroup(gl_panelRegistro.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProcurar)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar)
						.addComponent(btnListarTodos))
					.addGap(19))
		);
//		System.out.println("2");
//		System.out.println("3");
		panelRegistro.setLayout(gl_panelRegistro);
		
		//vou desenhar tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 283, 674, 179);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				descricao = getCelulaTabela(table, table.getSelectedRow(), 1).toString();
				textField.setText(descricao);
				codigo = getCelulaTabela(table, table.getSelectedRow(), 0).toString();
				textField_1.setText(codigo);
				
				try {
					loja = controleEstoque.procurarLoja(descricao);
				} catch (LojaJaCadastradaException e) {
					System.out.println(e.getMessage());
				} catch (RepositorioException e) {
					System.out.println(e.getMessage());
				} catch (LojaNaoEncontradaException e) {
					System.out.println(e.getMessage());
				}
				if(loja!=null){
					for(String nome:nomesFuncionarios){
//                		String nome=nmFuncionario.toString();
                		try {
							Funcionario funcionario = controleEstoque.procurarFuncionarioPorNome(nome);
						} catch (FuncionarioNaoEncontradoException e) {
							System.out.println(e.getMessage());
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
                		boolean jaExiteFuncionario=false;
						try {
							jaExiteFuncionario = controleEstoque.existeFuncionarioLoja(loja, nome);
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
	                  	if(jaExiteFuncionario){
	                  		if(!model_1.contains(nome))
	        					model_1.addElement(nome);
	                  	}
                	}
                	
            		for(String nome:nomesFornecedores){
//            			String nome=nmFornecedor.toString();
            			try {
							Fornecedor fornecedor = controleEstoque.procurarFornecedorPorNome(nome);
						} catch (FornecedorNaoEncontradoException e) {
							System.out.println(e.getMessage());
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
            			boolean jaExiteFornecedor=false;
						try {
							jaExiteFornecedor = controleEstoque.existeFornecedorLoja(loja, nome);
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
	                  	if(jaExiteFornecedor){
	                  		if(!model_3.contains(nome))
	        					model_3.addElement(nome);
	                  	}
                	}
				}
			}
		});
//		System.out.println("5");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				descricao = getCelulaTabela(table, table.getSelectedRow(), 1).toString();
				textField.setText(descricao);
				codigo = getCelulaTabela(table, table.getSelectedRow(), 0).toString();
				textField_1.setText(codigo);
				
				try {
					loja = controleEstoque.procurarLoja(descricao);
				} catch (LojaJaCadastradaException e) {
					System.out.println(e.getMessage());
				} catch (RepositorioException e) {
					System.out.println(e.getMessage());
				} catch (LojaNaoEncontradaException e) {
					System.out.println(e.getMessage());
				}
				if(loja!=null){
					for(String nome:nomesFuncionarios){
//                		String nome=nmFuncionario.toString();
                		try {
							Funcionario funcionario = controleEstoque.procurarFuncionarioPorNome(nome);
						} catch (FuncionarioNaoEncontradoException e) {
							System.out.println(e.getMessage());
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
                		boolean jaExiteFuncionario=false;
						try {
							jaExiteFuncionario = controleEstoque.existeFuncionarioLoja(loja, nome);
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
	                  	if(jaExiteFuncionario){
	                  		if(!model_1.contains(nome))
	                  			model_1.addElement(nome);
	                  	}
                	}
                	
            		for(String nome:nomesFornecedores){
//            			String nome=nmFornecedor.toString();
            			try {
							Fornecedor fornecedor = controleEstoque.procurarFornecedorPorNome(nome);
						} catch (FornecedorNaoEncontradoException e) {
							System.out.println(e.getMessage());
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
            			boolean jaExiteFornecedor=false;
						try {
							jaExiteFornecedor = controleEstoque.existeFornecedorLoja(loja, nome);
						} catch (RepositorioException e) {
							System.out.println(e.getMessage());
						}
	                  	if(jaExiteFornecedor){
	                  		if(!model_3.contains(nome))
	        					model_3.addElement(nome);
	                  	}
                	}
				}
			}
		});
//		System.out.println("6");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descri\u00E7\u00E3o"
			}
		));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
//		System.out.println("7");
//		SelectionListener listener = new SelectionListener(table);
//		table.getSelectionModel().addListSelectionListener(listener);
//		table.getColumnModel().getSelectionModel()
//		    .addListSelectionListener(listener);
		
	}
	
	/** 
	 * Remove a linha do modelo. 
	 * @param linha 
	 */  
	public void removeLinha(int linha){  
	      
	    // Obtem o modelo da JTable  
	    DefaultTableModel modelo = (DefaultTableModel)table.getModel();  
	      
	    // Remove a linha   
	    modelo.removeRow(linha);  
	  
	}  
	  
	public void removeTodasLinhas(){
		// Obtem o modelo da JTable  
	    DefaultTableModel modelo = (DefaultTableModel)table.getModel();  
	      
	    // Remove todas as linhas da tabela   
	    for(int i=0; i<modelo.getRowCount(); i++)
	    	modelo.removeRow(i); 
	}
	
	/** 
	 * Obtem a linha selecionada e chama o método para remover 
	 * do modelo 
	 */  
	public void removeLinha(){
	    // Obtem a linha selecionada na tabela e chama o método  
	    // para excluir a linha  
	    int linhaSelecionada = table.getSelectedRow();  
	      
	    // Verificamos se existe realmente alguma linha selecionada  
	    if( linhaSelecionada < 0 ){  
	        return;  
	    }else{  
	        // Remove a linha do modelo  
	        removeLinha(linhaSelecionada);  
	    }   
	}
	
	/** 
	 * Método para adicionar uma nova linha na JTable 
	 */  
	public void adicionaLinha(String codigo, String descricao) {  
	 // Obtem o modelo da JTable  
	 DefaultTableModel modelo = (DefaultTableModel)table.getModel();  
	 // Adiciona uma nova linha em branco no modelo  
	 modelo.addRow( new String [] {codigo, descricao} );  
	}  
	
	public int substituirValor(String oldValue, String newValue, int column){  
	    // Flag para saber se algum valor foi  
	    int total = 0;  
	    // Obtem o modelo da JTable  
	    DefaultTableModel modelo = (DefaultTableModel)table.getModel();  
	    // Faz um looping em cima das linhas do modelo  
	    for( int linha=0; linha<modelo.getRowCount(); linha++){  
	        // Obtem o valor atual na coluna  
	        String valorAtual = (String)modelo.getValueAt(linha, column);  
	        if( valorAtual.equals(oldValue) ){  
	            // Substitui pelo novo valor na linha e coluna  
	            modelo.setValueAt(newValue, linha, column);  
	            // Adiciona mais um no numero de linhas atualizadas           
	            total++;  
	        }  
	    }  
	    return total;  
	}
	
	public Object getCelulaTabela(JTable table, int row_index, int col_index){
	  return table.getModel().getValueAt(row_index, col_index);
    }
	
	public void carregarDadosFuncionarios() throws RepositorioException{
		Funcionario[] listaFuncionarios = controleEstoque.listarTudoFuncionarios();
			nomesFuncionarios = new String[listaFuncionarios.length];
			int count = 0;
				for(Funcionario f:listaFuncionarios){
					if(f!=null && f.getNome()!=null){
						nomesFuncionarios[count] = f.getNome();
						count++;
						if(!model.contains(f.getNome()))
							model.addElement(f.getNome());
					}
				}
//		nomesFuncionarios = new String[]{"Joelson", "Maurício", "Carlos"};
		
//		if(!model.contains("Joelson"))
//		model.addElement("Joelson");
//		if(!model.contains("Maurício"))
//		model.addElement("Maurício");
//		if(!model.contains("Carlos"))
//		model.addElement("Carlos");
		
//		list = new JList( nomesFuncionarios ); //multiselect de funcionarios
	    list.setVisibleRowCount( 7 ); // show five rows
	    list.setSelectionMode( 
	         ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
	    
	    model_1.removeAllElements();
		list_1.setVisibleRowCount( 7 ); // show 5 rows
//			list_1.setFixedCellWidth( 100 ); // set width
//			list_1.setFixedCellHeight( 15 ); // set height
		list_1.setSelectionMode( 
         ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
	}
	
	public void carregarDadosFornecedores() throws RepositorioException{
		Fornecedor[] listaFornecedores = controleEstoque.listarTudoFornecedores();
		nomesFornecedores = new String[listaFornecedores.length];
		int count = 0;
			for(Fornecedor f:listaFornecedores){
				if(f!=null && f.getNome()!=null){
					nomesFornecedores[count] = f.getNome();
					count++;
					if(!model_2.contains(f.getNome()))
						model_2.addElement(f.getNome());
				}
			}
		
//		nomesFornecedores = new String[]{"Joelson", "Maurício", "Carlos"};
//		if(!model_2.contains("Joelson"))
//		model_2.addElement("Joelson");
//		if(!model_2.contains("Maurício"))
//		model_2.addElement("Maurício");
//		if(!model_2.contains("Carlos"))
//		model_2.addElement("Carlos");
		
//		list_2 = new JList( nomesFornecedores );
			
		list_2.setVisibleRowCount( 7 ); // show five rows
	    list_2.setSelectionMode( 
	         ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		
	    model_3.removeAllElements();
		list_3.setVisibleRowCount( 7 ); // show 5 rows
//		list_3.setFixedCellWidth( 100 ); // set width
//		list_3.setFixedCellHeight( 15 ); // set height
		list_3.setSelectionMode( 
         ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
	}
	
	public void novo() throws RepositorioException{
		textField_1.setText("");
		textField.setText("");
		carregarDadosFuncionarios();
		carregarDadosFornecedores();
	}
	
	public void cadastrar() throws FuncionarioNaoEncontradoException, FornecedorNaoEncontradoException{
		codigo = textField_1.getText();
		descricao = textField.getText();
		String erro = "";
		
		if(codigo == null || codigo.isEmpty())
			erro += "Campo obrigatório: código";
		
			if(descricao == null || descricao.isEmpty())
				erro += "\nCampo obrigatório: descrição";
		
			if(!erro.isEmpty()){
				JOptionPane.showMessageDialog(this,
					    erro,
					    "Atenção",
					    JOptionPane.WARNING_MESSAGE);
			}
			else{
				loja = new Loja(descricao, codigo);
                try {
                	
                	for(Object nmFuncionario:list_1.getSelectedValues()){
                		String nome=nmFuncionario.toString();
                		Funcionario funcionario = controleEstoque.procurarFuncionarioPorNome(nome);
                		boolean jaExiteFuncionario = controleEstoque.existeFuncionarioLoja(loja, nome);
	                  	if(funcionario!=null && !jaExiteFuncionario){
		                  loja.inserirFuncionario(funcionario);
	                  	}
                	}
                	
            		for(Object nmFornecedor:list_3.getSelectedValues()){
            			String nome=nmFornecedor.toString();
            			Fornecedor fornecedor = controleEstoque.procurarFornecedorPorNome(nome);
            			boolean jaExiteFornecedor = controleEstoque.existeFornecedorLoja(loja, nome);
	                  	if(fornecedor!=null && !jaExiteFornecedor){
		                  loja.inserirFornecedor(fornecedor);
	                  	}
                	}
                	
					controleEstoque.cadastrar(loja);
					JOptionPane.showMessageDialog(this,
							"Loja cadastrada com sucesso",
						    "Sucesso",
						    JOptionPane.PLAIN_MESSAGE);
					adicionaLinha(codigo, descricao);
					novo();
				} catch (LojaJaCadastradaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this,
							"Loja já cadastrada, escolha outra descrição para a loja",
						    "Atenção",
						    JOptionPane.WARNING_MESSAGE);
					e1.getMessage();
				} catch (RepositorioException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			}
	}
	
	public void alterar() throws LojaNaoEncontradaException, FuncionarioNaoEncontradoException, FornecedorNaoEncontradoException{
		codigo = textField_1.getText();
		descricao = textField.getText();
		String erro = "";
		int linhaSelecionada = table.getSelectedRow();
		
		if(codigo == null || codigo.isEmpty())
			erro += "Campo obrigatório: código";
		
			if(descricao == null || descricao.isEmpty())
				erro += "\nCampo obrigatório: descrição";
		
		if(linhaSelecionada<0)
		    erro += "\nObs: Selecione um registro na tabela";
			
			if(!erro.isEmpty()){
				JOptionPane.showMessageDialog(this,
					    erro,
					    "Atenção",
					    JOptionPane.WARNING_MESSAGE);
			}
			else{
                try {
                	String descricaoAntiga = getCelulaTabela(table, linhaSelecionada, 1).toString();
                	loja = controleEstoque.procurarLoja(descricaoAntiga);
	                	if(loja!=null){
	                		loja.setDescricao(descricao);
	                		loja.setCodigo(codigo);
	                		
	                		for(Object nmFuncionario:list_1.getSelectedValues()){
	                    		String nome=nmFuncionario.toString();
	                    		Funcionario funcionario = controleEstoque.procurarFuncionarioPorNome(nome);
	                    		boolean jaExiteFuncionario = controleEstoque.existeFuncionarioLoja(loja, nome);
	    	                  	if(funcionario!=null && !jaExiteFuncionario){
	    		                  loja.inserirFuncionario(funcionario);
	    	                  	}
	                    	}
	                    	
	                		for(Object nmFornecedor:list_3.getSelectedValues()){
	                			String nome=nmFornecedor.toString();
	                			Fornecedor fornecedor = controleEstoque.procurarFornecedorPorNome(nome);
	                			boolean jaExiteFornecedor = controleEstoque.existeFornecedorLoja(loja, nome);
	    	                  	if(fornecedor!=null && !jaExiteFornecedor){
	    		                  loja.inserirFornecedor(fornecedor);
	    	                  	}
	                    	}
	                		
	                		controleEstoque.atualizar(loja);
	                		JOptionPane.showMessageDialog(this,
									"Loja alterada com sucesso",
								    "Sucesso",
								    JOptionPane.PLAIN_MESSAGE);
	                		DefaultTableModel modelo = (DefaultTableModel)table.getModel();
	                		modelo.setValueAt(codigo, linhaSelecionada, 0);
	                		modelo.setValueAt(descricao, linhaSelecionada, 1);
	                	}
	                	else
	                		JOptionPane.showMessageDialog(this,
	                		"Loja não encontrada!",
						    "Erro",
						    JOptionPane.ERROR_MESSAGE);
				} catch (LojaJaCadastradaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RepositorioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
	
	public void excluir() throws LojaNaoEncontradaException{
		int linhaSelecionada = table.getSelectedRow();
		descricao = textField.getText();
			if( (descricao == null || descricao.isEmpty()) || linhaSelecionada<0)
				JOptionPane.showMessageDialog(this,
					    "Obrigatório selecionar o registro\n da tabela que se deseja excluir",
					    "Atenção",
					    JOptionPane.WARNING_MESSAGE);
			else{
                try {
                	descricao = getCelulaTabela(table, linhaSelecionada, 1).toString();
                	loja = controleEstoque.procurarLoja(descricao);
	                	if(loja!=null){
	                		int n = JOptionPane.showConfirmDialog(
	                			    this,
	                			    "Deseja excluir a loja '"+descricao+"' definitivamente?",
	                			    "Confirma exclusão",
	                			    JOptionPane.YES_NO_OPTION);
	                		System.out.println(n);
	                		if(n==JOptionPane.YES_OPTION){
	                			controleEstoque.removerLoja(descricao);
		                		JOptionPane.showMessageDialog(this,
										"Loja removida com sucesso",
									    "Sucesso",
									    JOptionPane.PLAIN_MESSAGE);
		                		removeLinha(linhaSelecionada);
		                		novo();
	                		}
	                	}
	                	else
	                		JOptionPane.showMessageDialog(this,
	                		"Loja não encontrada!",
						    "Erro",
						    JOptionPane.ERROR_MESSAGE);
				} catch (LojaJaCadastradaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RepositorioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
}
