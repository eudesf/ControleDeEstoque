package br.com.ceut.controleestoque.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setResizable(false);
		setBounds(100, 100, 450, 218);
		
		JLabel lblProgramaDesenvolvidoPor = new JLabel("Programa desenvolvido por:");
		lblProgramaDesenvolvidoPor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblEudes = new JLabel("Eudes");
		lblEudes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblJoelson = new JLabel("Joelson");
		lblJoelson.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblAlysson = new JLabel("Alysson");
		lblAlysson.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblWillardy = new JLabel("Willardy");
		lblWillardy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(92)
							.addComponent(lblProgramaDesenvolvidoPor))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(193)
							.addComponent(lblWillardy))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(194)
							.addComponent(lblAlysson))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(195)
							.addComponent(lblJoelson))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(199)
							.addComponent(lblEudes))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProgramaDesenvolvidoPor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEudes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblJoelson)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAlysson)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWillardy)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(115))
		);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnSair);
		getContentPane().setLayout(groupLayout);
	}
}
