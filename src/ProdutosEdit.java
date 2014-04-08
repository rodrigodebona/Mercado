import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProdutosEdit extends JFrame {

	BorderLayout layoutTela;
	JLabel labelEditarProdutos;
	JButton BotaoInserir;
	JButton BotaoAlterar;
	JButton BotaoExcluir;
	JButton BotaoVoltar;

	public ProdutosEdit() {

		layoutTela = new BorderLayout();
		getContentPane().setLayout(layoutTela);

		labelEditarProdutos = new JLabel("Editar Produtos");
		labelEditarProdutos.setFont(new Font("Times", Font.PLAIN, 20));
		labelEditarProdutos.setForeground(Color.BLUE);

		BotaoInserir = new JButton("Inserir");
		BotaoInserir.addActionListener(new ListenerBotaoInserir());
		BotaoAlterar = new JButton("Editar");
		BotaoAlterar.addActionListener(new ListenerBotaoAlterar());
		BotaoExcluir = new JButton("Excluir");
		BotaoExcluir.addActionListener(new ListenerBotaoExcluir());
		BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.addActionListener(new ListenerBotaoVoltar());

		JPanel EditarProdutos = (JPanel) getContentPane();
		Container c = getContentPane(); // CONTAINER PARA COLOCAR COR DE FUNDO
										// DA TELA

		EditarProdutos.add(labelEditarProdutos).setBounds(120, 5, 250, 30);
		EditarProdutos.add(BotaoInserir).setBounds(35, 50, 100, 35);
		EditarProdutos.add(BotaoAlterar).setBounds(35, 90, 100, 35);
		EditarProdutos.add(BotaoExcluir).setBounds(35, 130, 100, 35);
		EditarProdutos.add(BotaoVoltar).setBounds(300, 170, 70, 30);

		setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO FICAR
										// CORRETO.
		c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
		setSize(400, 250); // TAMANHO DO PAINEL
		setVisible(true); // PAINEL VISIVEL

	} // FECHA CONSTRUTOR EDITAR PRODUTOS

	// LISTENER BOTÃO VOLTAR
	class ListenerBotaoVoltar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose(); // FECHA JANELA ABERTA
		}
	}

	// LISTENER BOTAO INSERIR
	class ListenerBotaoInserir implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new ProdutosInserir();
		}
	}

	// LISTENER BOTAO EDITAR
	class ListenerBotaoAlterar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new ProdutosAlterar();
		}
	}

	// LISTENER BOTAO EXCLUIR
	class ListenerBotaoExcluir implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new ProdutosExclui();
		}
	}

}
