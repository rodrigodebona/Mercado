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
import javax.swing.JTextField;

public class Vendas extends JFrame {

	JLabel labelVendido;
	JLabel labelProduto;

	JLabel labelProdutoVender;
	JLabel labelProdutoVendido;
	JLabel labelQuantidade;
	JLabel labelValorUnitario;
	JLabel labelValorTotal;
	JLabel labelDinheiro;
	JLabel labelTroco;
	JLabel mensagemErro;

	JButton BotaoOK;
	BorderLayout layoutTela;

	String vendaProdutos;
	String nomeProduto;
	String valorProduto;
	String produtoExcluido;
	String estoqueProduto;
	String valorCaixa;
	String buscaId;
	String quantidadeVendida;
	String dinheiro;
	String testeExcluido = "N";

	int valorAtualzaEstoque;
	float floatEstoqueProduto;
	float valorAtualizaCaixa;
	float floatValorProduto;
	float floatValorCaixa;
	float floatQuantidadeVendida;
	float valorTotal;
	float estoqueAtual;
	float floatDinheiro;
	float troco;

	public Vendas() {

		try {
			// REGISTRAR A CLASSE QUE CONECTA AO BANCO
			Class.forName("com.mysql.jdbc.Driver");

			// CRIAR CONEXÃO COM O BANCO DE DADOS
			Connection conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/mercado", "root", "1234");

			// CRIA STATEMENT PARA EXECUTAR COMANDOS SQL NO BANCO
			Statement statement = conexao.createStatement();

			buscaId = JOptionPane.showInputDialog("Codigo do produto: ");
			quantidadeVendida = JOptionPane.showInputDialog("Quantidade: ");
			dinheiro = JOptionPane.showInputDialog("Dinheiro: ");

			// FAZ CONSULTA
			String sqlConsulta = "Select nomeProduto, valorProduto, excluido, estoque, valorCaixa from produtos, caixa where produtos.Id = "
					+ buscaId;

			// EXECUTAR CONSULTA
			ResultSet rs = statement.executeQuery(sqlConsulta);

			while (rs.next()) {
				nomeProduto = rs.getString(1);
				valorProduto = rs.getString(2);
				produtoExcluido = rs.getString(3);
				estoqueProduto = rs.getString(4);
				valorCaixa = rs.getString(5);

				floatValorProduto = Float.parseFloat(valorProduto);
				floatQuantidadeVendida = Float.parseFloat(quantidadeVendida);
				floatEstoqueProduto = Float.parseFloat(estoqueProduto);

				floatDinheiro = Float.parseFloat(dinheiro);
				valorTotal = floatValorProduto * floatQuantidadeVendida;
				troco = floatDinheiro - valorTotal;

				if (produtoExcluido.equals(testeExcluido)) {
					if (floatQuantidadeVendida < floatEstoqueProduto) {
						if (floatDinheiro > valorTotal) {

							floatEstoqueProduto = Float
									.parseFloat(estoqueProduto);
							estoqueAtual = floatEstoqueProduto
									- floatQuantidadeVendida;

							layoutTela = new BorderLayout();
							getContentPane().setLayout(layoutTela);

							labelVendido = new JLabel("Produtos vendidos!");
							labelVendido.setFont(new Font("Times", Font.PLAIN,
									22));

							labelProduto = new JLabel("Produto: " + nomeProduto);
							labelProduto.setFont(new Font("Times", Font.PLAIN,
									14));

							labelQuantidade = new JLabel("Quantidade: "
									+ quantidadeVendida);
							labelQuantidade.setFont(new Font("Times",
									Font.PLAIN, 14));

							labelValorUnitario = new JLabel(
									"Valor unitario R$: " + valorProduto);
							labelValorUnitario.setFont(new Font("Times",
									Font.PLAIN, 14));

							labelValorTotal = new JLabel("Valor total R$: "
									+ valorTotal);
							labelValorTotal.setFont(new Font("Times",
									Font.PLAIN, 16));

							labelDinheiro = new JLabel("Dinheiro R$: "
									+ floatDinheiro);
							labelDinheiro.setFont(new Font("Times", Font.PLAIN,
									16));
							labelTroco = new JLabel("Troco R$: " + troco);
							labelTroco
									.setFont(new Font("Times", Font.PLAIN, 16));

							BotaoOK = new JButton("OK");
							BotaoOK.addActionListener(new ListenerBotaoOK());

							JPanel produtoVendido = (JPanel) getContentPane();
							Container c = getContentPane();

							produtoVendido.add(labelVendido).setBounds(95, 5,
									200, 30);
							produtoVendido.add(labelProduto).setBounds(20, 50,
									150, 20);
							produtoVendido.add(labelQuantidade).setBounds(20,
									85, 150, 20);
							produtoVendido.add(labelValorUnitario).setBounds(
									20, 120, 150, 20);
							produtoVendido.add(labelValorTotal).setBounds(20,
									155, 200, 20);
							produtoVendido.add(labelDinheiro).setBounds(20,
									190, 200, 20);
							produtoVendido.add(labelTroco).setBounds(20, 225,
									200, 20);
							produtoVendido.add(BotaoOK).setBounds(300, 225, 70,
									30);

							setLayout(new BorderLayout()); // COMANDO PARA
															// ÚLTIMO BOTÃO
															// FICAR CORRETO.
							c.setBackground(Color.WHITE); // COR DE FUNDO DO
															// PAINEL
							setSize(400, 300); // TAMANHO DO PAINEL
							setVisible(true);

						} else {
							mensagemErro = new JLabel("Valor insuficiente");
							mensagemErro.setForeground(Color.RED);
							mensagemErro.setFont(new Font("Times", Font.PLAIN,
									16));

							BotaoOK = new JButton("Voltar");
							BotaoOK.addActionListener(new ListenerBotaoOK());

							JPanel produtoVendido = (JPanel) getContentPane();
							Container c = getContentPane();

							produtoVendido.add(mensagemErro).setBounds(15, 10,
									200, 30);
							produtoVendido.add(BotaoOK).setBounds(60, 70, 70,
									30);

							setLayout(new BorderLayout()); // COMANDO PARA
															// ÚLTIMO BOTÃO
															// FICAR CORRETO.
							c.setBackground(Color.WHITE); // COR DE FUNDO DO
															// PAINEL
							setSize(200, 150); // TAMANHO DO PAINEL
							setVisible(true);

						}

					} else {
						mensagemErro = new JLabel("Produto indisponível!");
						mensagemErro.setForeground(Color.RED);
						mensagemErro.setFont(new Font("Times", Font.PLAIN, 16));

						BotaoOK = new JButton("Voltar");
						BotaoOK.addActionListener(new ListenerBotaoOK());

						JPanel produtoVendido = (JPanel) getContentPane();
						Container c = getContentPane();

						produtoVendido.add(mensagemErro).setBounds(20, 10, 200,
								30);
						produtoVendido.add(BotaoOK).setBounds(60, 70, 70, 30);

						setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO
														// BOTÃO FICAR CORRETO.
						c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
						setSize(200, 150); // TAMANHO DO PAINEL
						setVisible(true);

					}

				} else {
					mensagemErro = new JLabel("Produto indisponível!");
					mensagemErro.setForeground(Color.RED);
					mensagemErro.setFont(new Font("Times", Font.PLAIN, 16));

					BotaoOK = new JButton("Voltar");
					BotaoOK.addActionListener(new ListenerBotaoOK());

					JPanel produtoVendido = (JPanel) getContentPane();
					Container c = getContentPane();

					produtoVendido.add(mensagemErro).setBounds(20, 10, 200, 30);
					produtoVendido.add(BotaoOK).setBounds(60, 70, 70, 30);

					setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO
													// FICAR CORRETO.
					c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
					setSize(200, 150); // TAMANHO DO PAINEL
					setVisible(true);

				}

			}

			floatValorCaixa = Float.parseFloat(valorCaixa);
			valorAtualizaCaixa = valorTotal + floatValorCaixa;

			if (produtoExcluido.equals(testeExcluido)) {
				if (floatQuantidadeVendida < floatEstoqueProduto) {
					if (floatDinheiro > valorTotal) {

						String sqlAtualizacaoCaixa = "Update caixa set valorCaixa = "
								+ valorAtualizaCaixa + " where Id = 1";

						String sqlAtualizacaoEstoque = "Update produtos set estoque = "
								+ estoqueAtual + " where Id = " + buscaId;

						// FAZ INSERCAO DE DADOS NA TABELA DE DADOS
						String sqlInsercao = "insert into vendidos (ProdutosVendidos, quantidade, valorUN, valorTotal)"
								+ "values ('"
								+ nomeProduto
								+ "', '"
								+ quantidadeVendida
								+ "', '"
								+ valorProduto
								+ "', '" + valorTotal + "')";

						// EXECUTAR A ATUALIZACAO
						statement.execute(sqlAtualizacaoCaixa);

						statement.execute(sqlAtualizacaoEstoque);

						// EXECUTAR A INSERCAO
						statement.execute(sqlInsercao);
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // FECHA CONSTRUTOR VENDAS

	// LISTENER BOTÃO OK
	class ListenerBotaoOK implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose(); // FECHA JANELA ABERTA
		}

	}

}
