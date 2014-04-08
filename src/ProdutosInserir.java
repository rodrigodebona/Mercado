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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProdutosInserir extends JFrame {

	Produtos p1 = new Produtos();

	int codigoProdutoBanco;
	String pegaCodigoBanco;
	String nomeProdutoBanco;
	String valorProdutoBanco;
	String estoqueProdutoBanco;
	String excluidoBanco;

	BorderLayout layoutTela;
	JLabel labelInserirProdutos;
	JLabel labelCodigoProduto;
	JTextField CodigoProduto;
	JLabel labelNomeProduto;
	JTextField NomeProduto;
	JLabel labelValorProduto;
	JTextField ValorProduto;
	JLabel labelEstoqueProduto;
	JTextField EstoqueProduto;
	JButton BotaoSalvar;
	JButton BotaoVoltar;

	public ProdutosInserir() {

		layoutTela = new BorderLayout();
		getContentPane().setLayout(layoutTela);

		labelInserirProdutos = new JLabel("Inserir Produtos");
		labelInserirProdutos.setFont(new Font("Times", Font.PLAIN, 20));
		labelInserirProdutos.setForeground(Color.BLUE);

		try {
			// REGISTRAR A CLASSE QUE CONECTA AO BANCO
			Class.forName("com.mysql.jdbc.Driver");

			// CRIAR CONEXÃO COM O BANCO DE DADOS
			Connection conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/mercado", "root", "1234");

			// CRIA STATEMENT PARA EXECUTAR COMANDOS SQL NO BANCO
			Statement statement = conexao.createStatement();

			// FAZ UMA CONSULTA
			String sqlConsulta = "Select Id from produtos";

			// EXECUTAR CONSULTA
			ResultSet rs = statement.executeQuery(sqlConsulta);

			while (rs.next()) { // LAÇO PARA PEGAR ULTIMO ID
				pegaCodigoBanco = rs.getString(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int intCodigoProdutoBanco = Integer.parseInt(pegaCodigoBanco);
		intCodigoProdutoBanco = intCodigoProdutoBanco + 1;

		labelCodigoProduto = new JLabel("Codigo do Produto: "
				+ intCodigoProdutoBanco);
		labelNomeProduto = new JLabel("Nome do Produto: ");
		NomeProduto = new JTextField(22);
		labelValorProduto = new JLabel("Valor Produto: ");
		ValorProduto = new JTextField(7);
		labelEstoqueProduto = new JLabel("Estoque Produto: ");
		EstoqueProduto = new JTextField(7);
		BotaoSalvar = new JButton("Salvar");
		BotaoSalvar.addActionListener(new ListenerBotaoSalvar());
		BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.addActionListener(new ListenerBotaoVoltar());

		JPanel InserirProdutos = (JPanel) getContentPane();
		Container c = getContentPane(); // CONTAINER PARA COLOCAR COR DE FUNDO
										// DA TELA

		InserirProdutos.add(labelInserirProdutos).setBounds(120, 5, 150, 30);
		InserirProdutos.add(labelCodigoProduto).setBounds(5, 60, 150, 25);
		InserirProdutos.add(labelNomeProduto).setBounds(5, 90, 150, 25);
		InserirProdutos.add(NomeProduto).setBounds(120, 90, 150, 25);
		InserirProdutos.add(labelValorProduto).setBounds(5, 120, 150, 25);
		InserirProdutos.add(ValorProduto).setBounds(120, 120, 150, 25);
		InserirProdutos.add(labelEstoqueProduto).setBounds(5, 150, 150, 25);
		InserirProdutos.add(EstoqueProduto).setBounds(120, 150, 150, 25);
		InserirProdutos.add(BotaoSalvar).setBounds(300, 220, 70, 30);
		InserirProdutos.add(BotaoVoltar).setBounds(220, 220, 70, 30);

		setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO FICAR
										// CORRETO.
		c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
		setSize(400, 300); // TAMANHO DO PAINEL
		setVisible(true); // PAINEL VISIVEL

	}

	// LISTENER BOTÃO VOLTAR
	class ListenerBotaoVoltar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose(); // FECHA JANELA ABERTA
		}
	}

	class ListenerBotaoSalvar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int intCodigoProdutoBanco = Integer.parseInt(pegaCodigoBanco);
			intCodigoProdutoBanco = intCodigoProdutoBanco + 1;
			p1.setCodigoProduto(intCodigoProdutoBanco);
			p1.setNomeProduto(NomeProduto.getText());
			p1.setValorProtudo(ValorProduto.getText());
			p1.setEstoqueProduto(EstoqueProduto.getText());
			codigoProdutoBanco = p1.getCodigoProduto();
			nomeProdutoBanco = p1.getNomeProduto();
			valorProdutoBanco = p1.getValorProtudo();
			estoqueProdutoBanco = p1.getEstoqueProduto();
			excluidoBanco = "N";

			try {
				// REGISTRAR A CLASSE QUE CONECTA AO BANCO
				Class.forName("com.mysql.jdbc.Driver");

				// CRIAR CONEXÃO COM O BANCO DE DADOS
				Connection conexao = DriverManager.getConnection(
						"jdbc:mysql://localhost/mercado", "root", "1234");

				// CRIA STATEMENT PARA EXECUTAR COMANDOS SQL NO BANCO
				Statement statement = conexao.createStatement();

				// FAZ INSERCAO DE DADOS
				String sqlInsercao = "insert into produtos (Id, nomeProduto, valorProduto, excluido, estoque)"
						+ "values ('"
						+ codigoProdutoBanco
						+ "', '"
						+ nomeProdutoBanco
						+ "', '"
						+ valorProdutoBanco
						+ "', '"
						+ excluidoBanco
						+ "', '"
						+ estoqueProdutoBanco
						+ "')";

				statement.execute(sqlInsercao); // EXECUTAR A INSERCAO
				dispose(); // FECHA JANELA

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // FECHA LISTENER

	} // FECHA CONTRUTOR

}
