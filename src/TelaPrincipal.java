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

public class TelaPrincipal extends JFrame {

	BorderLayout layoutTela;
	JLabel labelNomeMercado;
	JButton BotaoCaixa;
	JButton BotaoProdutos;
	JButton BotaoEstoque;
	JButton BotaoVender;
	JButton BotaoSair;

	// CONSTRUTOR PARA TELA PRINCIPAL
	public TelaPrincipal() {
		layoutTela = new BorderLayout();

		getContentPane().setLayout(layoutTela);

		labelNomeMercado = new JLabel("Supermercado FACCAT");
		labelNomeMercado.setFont(new Font("Times", Font.PLAIN, 20));
		labelNomeMercado.setForeground(Color.BLUE);

		BotaoCaixa = new JButton("Caixa");
		BotaoCaixa.addActionListener(new ListenerBotaoCaixa());
		BotaoProdutos = new JButton("Produtos");
		BotaoProdutos.addActionListener(new ListenerBotaoEditarProdutos());
		BotaoEstoque = new JButton("Estoque");
		BotaoEstoque.addActionListener(new ListenerBotaoEstoqueProdutos());
		BotaoVender = new JButton("VENDER!");
		BotaoVender.setFont(new Font("Times", Font.PLAIN, 25));
		BotaoVender.addActionListener(new ListenerBotaoVender());
		BotaoSair = new JButton("Sair");
		BotaoSair.setForeground(Color.RED);
		BotaoSair.addActionListener(new ListenerBotaoSair());

		JPanel painelPrincipal = (JPanel) getContentPane();
		Container c = getContentPane(); // CONTAINER PARA COLOCAR COR DE FUNDO
										// DA TELA

		painelPrincipal.add(labelNomeMercado).setBounds(200, 5, 250, 30);
		painelPrincipal.add(BotaoCaixa).setBounds(75, 50, 100, 35);
		painelPrincipal.add(BotaoProdutos).setBounds(75, 90, 100, 35);
		painelPrincipal.add(BotaoEstoque).setBounds(75, 130, 100, 35);
		painelPrincipal.add(BotaoVender).setBounds(75, 170, 225, 55);
		painelPrincipal.add(BotaoSair).setBounds(500, 310, 70, 40);

		setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO FICAR
										// CORRETO.
		c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
		setSize(600, 400); // TAMANHO DO PAINEL
		setVisible(true); // PAINEL VISIVEL
		setDefaultCloseOperation(EXIT_ON_CLOSE); // FECHA APLICAÇÃO NA I.D.E.

	} // FECHA CONSTRUTOR DA TELA PRINCIPAL

	public static void main(String args[]) {

		new TelaPrincipal(); // EXECUTA EM TELA O CONSTRUTOR TelaPrincipal
	}

	// LISTENER BOTÃO SAIR
	class ListenerBotaoSair implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0); // FECHA APLICAÇÃO
		}
	}

	// LISTENER BOTÃO CAIXA
	class ListenerBotaoCaixa implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new Caixa(); // EXECUTA CONSTRUTOR CAIXA
		}
	}

	// LISTENER BOTÃO VENDER
	class ListenerBotaoVender implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new Vendas();
		}
	}

	// LISTENER BOTÃO EDITAR PRODUTOS
	class ListenerBotaoEditarProdutos implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new ProdutosEdit();
		}
	}

	// LISTENER BOTAO ESTOQUE
	class ListenerBotaoEstoqueProdutos implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new ProdutosEstoque();
		}

	}

}
