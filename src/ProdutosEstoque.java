import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProdutosEstoque extends JFrame {

	String pegaNomeProduto;
	String pegaProdutoExcluido;
	String pegaEstoque;
	String buscaId;
	String testeExcluido = "N";

	JLabel labelNomeProduto;
	JLabel labelEstoque;
	JLabel mensagemErro;

	JLabel labelTelaEstoque;

	BorderLayout layoutTela;

	JButton botaoOk;

	public ProdutosEstoque() {

		try {
			// REGISTRAR A CLASSE QUE CONECTA AO BANCO
			Class.forName("com.mysql.jdbc.Driver");

			// CRIAR CONEXÃO COM O BANCO DE DADOS
			Connection conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/mercado", "root", "1234");

			// CRIA STATEMENT PARA EXECUTAR COMANDOS SQL NO BANCO
			Statement statement = conexao.createStatement();

			buscaId = JOptionPane.showInputDialog("Codigo do produto: ");

			// FAZ CONSULTA
			String sqlConsulta = "Select nomeProduto, excluido, estoque from produtos where Id = "
					+ buscaId;

			// EXECUTAR CONSULTA
			ResultSet rs = statement.executeQuery(sqlConsulta);

			while (rs.next()) {
				pegaNomeProduto = rs.getString(1);
				pegaProdutoExcluido = rs.getString(2);
				pegaEstoque = rs.getString(3);

				if (pegaProdutoExcluido.equals(testeExcluido)) {

					layoutTela = new BorderLayout();
					getContentPane().setLayout(layoutTela);

					labelTelaEstoque = new JLabel("Estoque Produtos!");
					labelTelaEstoque.setFont(new Font("Times", Font.PLAIN, 20));

					labelNomeProduto = new JLabel("Nome do Produto:  "
							+ pegaNomeProduto);

					labelEstoque = new JLabel("Estoque:  " + pegaEstoque);

					botaoOk = new JButton("OK");
					botaoOk.setFont(new Font("Times", Font.PLAIN, 14));
					botaoOk.addActionListener(new ListenerBotaoOk());

					JPanel EstoqueProdutos = (JPanel) getContentPane();
					Container c = getContentPane(); // CONTAINER PARA COLOCAR
													// COR DE FUNDO DA TELA

					EstoqueProdutos.add(labelTelaEstoque).setBounds(70, 5, 200,
							30);
					EstoqueProdutos.add(labelNomeProduto).setBounds(5, 40, 200,
							30);
					EstoqueProdutos.add(labelEstoque).setBounds(5, 70, 150, 30);
					EstoqueProdutos.add(botaoOk).setBounds(90, 120, 100, 30);

					setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO
													// FICAR CORRETO.
					c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
					setSize(300, 200); // TAMANHO DO PAINEL
					setVisible(true); // PAINEL VISIVEL

				} else {
					mensagemErro = new JLabel("Produto indisponível!");
					mensagemErro.setForeground(Color.RED);
					mensagemErro.setFont(new Font("Times", Font.PLAIN, 16));

					botaoOk = new JButton("Voltar");
					botaoOk.addActionListener(new ListenerBotaoOk());

					JPanel produtoVendido = (JPanel) getContentPane();
					Container c = getContentPane();

					produtoVendido.add(mensagemErro).setBounds(20, 10, 200, 30);
					produtoVendido.add(botaoOk).setBounds(60, 70, 70, 30);

					setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO
													// FICAR CORRETO.
					c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
					setSize(200, 150); // TAMANHO DO PAINEL
					setVisible(true);

				}

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// LISTENER BOTÃO OK
	class ListenerBotaoOk implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose(); // FECHA JANELA ABERTA
		}
	}

}
