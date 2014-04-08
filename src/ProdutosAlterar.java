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

public class ProdutosAlterar extends JFrame {

	BorderLayout layoutTela;

	String buscaId;
	String atualizaProdutoExcluido;
	String atualizaNomeProduto;
	String atualizaValorProduto;
	String atualizaEstoqueProduto;

	JButton salvar;

	JLabel labelAlterarProdutos;
	JLabel labelNomeProduto;
	JTextField altNomeProduto;
	JLabel labelValorProduto;
	JTextField altValorProduto;
	JLabel labelEstoqueProduto;
	JTextField altEstoqueProduto;
	JLabel labelExluiProduto;
	JTextField altExcluiProduto;

	public ProdutosAlterar() {

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
			String sqlConsulta = "Select nomeProduto, valorProduto, excluido, estoque from produtos where Id = "
					+ buscaId;

			// EXECUTAR CONSULTA
			ResultSet rs = statement.executeQuery(sqlConsulta);

			while (rs.next()) {
				atualizaNomeProduto = rs.getString(1);
				atualizaValorProduto = rs.getString(2);
				atualizaProdutoExcluido = rs.getString(3);
				atualizaEstoqueProduto = rs.getString(4);

				layoutTela = new BorderLayout();
				getContentPane().setLayout(layoutTela);

				labelAlterarProdutos = new JLabel("Alterar Produtos!");
				labelAlterarProdutos.setFont(new Font("Times", Font.PLAIN, 20));
				labelNomeProduto = new JLabel("Nome do Produto: ");
				altNomeProduto = new JTextField(atualizaNomeProduto);

				labelValorProduto = new JLabel("Valor do Produto: ");
				altValorProduto = new JTextField(atualizaValorProduto);

				labelEstoqueProduto = new JLabel("Estoque do Produto: ");
				altEstoqueProduto = new JTextField(atualizaEstoqueProduto);

				labelExluiProduto = new JLabel("Produto Excluido: ");
				altExcluiProduto = new JTextField(atualizaProdutoExcluido);

				salvar = new JButton("Salvar Alterações!");
				salvar.setFont(new Font("Times", Font.PLAIN, 14));
				salvar.addActionListener(new ListenerBotaoSalvar());

				JPanel AlteraProdutos = (JPanel) getContentPane();
				Container c = getContentPane(); // CONTAINER PARA COLOCAR COR DE
												// FUNDO DA TELA

				AlteraProdutos.add(labelAlterarProdutos).setBounds(120, 5, 150,
						30);
				AlteraProdutos.add(labelNomeProduto).setBounds(5, 70, 150, 25);
				AlteraProdutos.add(altNomeProduto).setBounds(130, 70, 150, 25);
				AlteraProdutos.add(labelValorProduto)
						.setBounds(5, 100, 150, 25);
				AlteraProdutos.add(altValorProduto)
						.setBounds(130, 100, 150, 25);
				AlteraProdutos.add(labelEstoqueProduto).setBounds(5, 130, 150,
						25);
				AlteraProdutos.add(altEstoqueProduto).setBounds(130, 130, 150,
						25);
				AlteraProdutos.add(labelExluiProduto)
						.setBounds(5, 160, 150, 25);
				AlteraProdutos.add(altExcluiProduto).setBounds(130, 160, 150,
						25);
				AlteraProdutos.add(salvar).setBounds(120, 200, 150, 30);

				setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO
												// FICAR CORRETO.
				c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
				setSize(400, 300); // TAMANHO DO PAINEL
				setVisible(true); // PAINEL VISIVEL

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // FECHA CONSTRUTOR PRODUTOS ALTERAR

	class ListenerBotaoSalvar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			atualizaNomeProduto = altNomeProduto.getText();
			atualizaValorProduto = altValorProduto.getText();
			atualizaEstoqueProduto = altEstoqueProduto.getText();
			atualizaProdutoExcluido = altExcluiProduto.getText();

			try {
				// REGISTRAR A CLASSE QUE CONECTA AO BANCO
				Class.forName("com.mysql.jdbc.Driver");

				// CRIAR CONEXÃO COM O BANCO DE DADOS
				Connection conexao = DriverManager.getConnection(
						"jdbc:mysql://localhost/mercado", "root", "1234");

				// CRIA STATEMENT PARA EXECUTAR COMANDOS SQL NO BANCO
				Statement statement = conexao.createStatement();

				// FAZ ATUALIZAÇÃO
				String sqlAtualizacao = "Update produtos set nomeProduto = '"
						+ atualizaNomeProduto + "', valorProduto = '"
						+ atualizaValorProduto + "', excluido = '"
						+ atualizaProdutoExcluido + "', estoque = '"
						+ atualizaEstoqueProduto + "' where Id = " + buscaId;

				statement.execute(sqlAtualizacao);

				// FECHA JANELA APÓS SALVAR
				dispose();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
