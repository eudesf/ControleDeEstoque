package br.com.ceut.controleestoque.gui;

import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.ceut.controleestoque.fachada.ControleEstoque;
import br.com.ceut.controleestoque.produtos.Produto;
import br.com.ceut.controleestoque.produtos.ProdutoJaCadastradoException;
import br.com.ceut.controleestoque.produtos.ProdutoNaoEncontradoException;

@SuppressWarnings("serial")
public class CadastroProdutosGUI extends JFrame {

	private JPanel contentPane;
	private JTextField descricaoProduto;
	private JTextField quantidadeDisponivel;
	private JTextField valorUnitario;
	private ControleEstoque controleEstoque;

	/**
	 * Create the frame.
	 */
	public CadastroProdutosGUI(ControleEstoque controleEstoque) {
		super();
		this.controleEstoque = controleEstoque;

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setTitle("Manipulando Produtos");
		setBounds(100, 100, 419, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome do produto:");

		JLabel lblQuantidadeDisponivel = new JLabel("Quantidade disponivel:");

		JLabel lblNewLabel = new JLabel("Valor unitario:");

		descricaoProduto = new JTextField();
		descricaoProduto.setColumns(10);

		quantidadeDisponivel = new JTextField();
		quantidadeDisponivel.setColumns(10);

		valorUnitario = new JTextField();
		valorUnitario.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procurar();
			}
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remover();
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizar();
			}
		});
		System.out.println();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addComponent(
												btnCadastrar).addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnProcurar)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnRemover)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnAtualizar).addGap(48))
						.addGroup(
								Alignment.LEADING,
								gl_contentPane
										.createSequentialGroup()
										.addGap(48)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNewLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				valorUnitario,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNome)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				descricaoProduto,
																				GroupLayout.PREFERRED_SIZE,
																				142,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblQuantidadeDisponivel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				quantidadeDisponivel,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(144, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.BASELINE).addComponent(
										lblNome).addComponent(descricaoProduto,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.BASELINE).addComponent(
										lblQuantidadeDisponivel).addComponent(
										quantidadeDisponivel,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.BASELINE).addComponent(
										lblNewLabel).addComponent(
										valorUnitario,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.BASELINE).addComponent(
										btnCadastrar).addComponent(btnProcurar)
										.addComponent(btnRemover).addComponent(
												btnAtualizar)).addContainerGap(
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {
				btnCadastrar, btnProcurar, btnRemover, btnAtualizar });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				descricaoProduto, quantidadeDisponivel, valorUnitario });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				lblNome, lblQuantidadeDisponivel, lblNewLabel });
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnCadastrar, btnProcurar, btnRemover, btnAtualizar });
		contentPane.setLayout(gl_contentPane);
	}

	public void cadastrar() {

		try {
			
			String descricaoProduto = this.descricaoProduto.getText();
			int quantidadeDisponivel = 0;
			String valorUnitario = " ";
			Produto produto = new Produto(descricaoProduto);

			if (!this.quantidadeDisponivel.getText().equals("")) {
				quantidadeDisponivel = Integer
						.parseInt(this.quantidadeDisponivel.getText());
			}

			if (!this.valorUnitario.getText().equals("")) {
				valorUnitario = this.valorUnitario.getText();
			}

			produto.setQuantidadeDisponivel(quantidadeDisponivel);
			produto.setValorUnitario(valorUnitario);

			controleEstoque.cadastrar(produto);

			JOptionPane.showMessageDialog(this,
					"Produto cadastrado com sucesso");
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ProdutoJaCadastradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} finally {
			limparCampos();
		}
	}

	public void procurar() {

		try {	
			String descricaoProduto = this.descricaoProduto.getText();
			Produto produto = controleEstoque.procurarProduto(descricaoProduto);
			this.quantidadeDisponivel.setText(Integer.toString(produto
					.getQuantidadeDisponivel()));
			this.valorUnitario.setText(produto.getValorUnitario());
			JOptionPane.showMessageDialog(this, "Produto encontrado");
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void remover() {

		try {
			String descricaoProduto = this.descricaoProduto.getText();
			controleEstoque.removerProduto(descricaoProduto);
			JOptionPane.showMessageDialog(this, "Produto removido com sucesso");
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} finally {
			limparCampos();
		}

	}

	public void atualizar() {

		try {
			String descricaoProduto = this.descricaoProduto.getText();
			int quantidadeDisponivel = Integer
					.parseInt(this.quantidadeDisponivel.getText());
			String valorUnitario = this.valorUnitario.getText();

			Produto produto = new Produto(descricaoProduto);
			produto.setQuantidadeDisponivel(quantidadeDisponivel);
			produto.setValorUnitario(valorUnitario);

			controleEstoque.atualizar(produto);
			JOptionPane.showMessageDialog(this,
					"Produto atualizado com sucesso");
		} catch (ProdutoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} finally {
			limparCampos();
		}
	}

	public void limparCampos() {
		this.descricaoProduto.setText("");
		this.quantidadeDisponivel.setText("");
		this.valorUnitario.setText("");
	}
}
