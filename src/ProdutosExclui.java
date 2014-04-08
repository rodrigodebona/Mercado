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

public class ProdutosExclui extends JFrame {

	BorderLayout layoutTela;

	JButton salvar;

	String buscaId;
	JTextField altExcluiProduto;

	String atualizaProdutoExcluido;
	String pegaNomeProduto;

	JLabel labelExcluiProdutos;
	JLabel labelNomeProduto;
	JLabel labelExluiProduto;

	public ProdutosExclui() {

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
			String sqlConsulta = "Select nomeProduto, excluido from produtos where Id = "
					+ buscaId;

			// EXECUTAR CONSULTA
			ResultSet rs = statement.executeQuery(sqlConsulta);

			while (rs.next()) {
				pegaNomeProduto = rs.getString(1);
				atualizaProdutoExcluido = rs.getString(2);

				layoutTela = new BorderLayout();
				getContentPane().setLayout(layoutTela);

				labelExcluiProdutos = new JLabel("Exclui Produtos!");
				labelExcluiProdutos.setFont(new Font("Times", Font.PLAIN, 20));
				labelNomeProduto = new JLabel("Nome do Produto: "
						+ pegaNomeProduto);

				labelExluiProduto = new JLabel("Produto Excluido: ");
				altExcluiProduto = new JTextField(atualizaProdutoExcluido);

				salvar = new JButton("Salvar Alterações!");
				salvar.setFont(new Font("Times", Font.PLAIN, 14));
				salvar.addActionListener(new ListenerBotaoSalvar());

				JPanel AlteraProdutos = (JPanel) getContentPane();
				Container c = getContentPane(); // CONTAINER PARA COLOCAR COR DE
												// FUNDO DA TELA

				AlteraProdutos.add(labelExcluiProdutos).setBounds(120, 5, 150,
						30);
				AlteraProdutos.add(labelNomeProduto).setBounds(5, 40, 150, 25);
				AlteraProdutos.add(labelExluiProduto).setBounds(5, 70, 150, 25);
				AlteraProdutos.add(altExcluiProduto)
						.setBounds(110, 70, 150, 25);
				AlteraProdutos.add(salvar).setBounds(60, 100, 150, 30);

				setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO
												// FICAR CORRETO.
				c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
				setSize(300, 200); // TAMANHO DO PAINEL
				setVisible(true); // PAINEL VISIVEL

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // FECHA CONSTRUTOR

	class ListenerBotaoSalvar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
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
				String sqlAtualizacao = "Update produtos set excluido = '"
						+ atualizaProdutoExcluido + "' where Id = " + buscaId;

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
