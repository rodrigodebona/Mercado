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

public class Caixa extends JFrame {

	String valorCaixa;
	BorderLayout layoutTela;
	JLabel labelCaixa;
	JLabel labelValorCaixa;
	JButton BotaoVoltar;

	public Caixa() {

		try {
			// REGISTRAR A CLASSE QUE CONECTA AO BANCO
			Class.forName("com.mysql.jdbc.Driver");

			// CRIAR CONEXÃO COM O BANCO DE DADOS
			Connection conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/mercado", "root", "1234");

			// CRIA STATEMENT PARA EXECUTAR COMANDOS SQL NO BANCO
			Statement statement = conexao.createStatement();

			// FAZ UMA CONSULTA. NESSE CASO, RETORNA O NOME E SOBRENOME DA
			// TABELA DE AUTORES
			String sqlConsulta = "Select valorCaixa from caixa";

			// EXECUTAR CONSULTA
			ResultSet rs = statement.executeQuery(sqlConsulta);

			// JOGA O NOME EM UMA VARIAVEL PROVISÓRIA PARA EXIBIR EM TELA
			while (rs.next()) { // LAÇO PARA EXIBIR NOMES DO BANCO
				valorCaixa = rs.getString(1); // JOGA O NOME EM UMA VARIAVEL
												// PROVISÓRIA PARA EXIBIR EM
												// TELA
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		layoutTela = new BorderLayout();

		getContentPane().setLayout(layoutTela);

		labelCaixa = new JLabel("CAIXA");
		labelCaixa.setFont(new Font("Times", Font.PLAIN, 20));
		labelValorCaixa = new JLabel("R$ " + valorCaixa);
		labelValorCaixa.setFont(new Font("Times", Font.PLAIN, 30));
		labelValorCaixa.setForeground(Color.BLUE);
		BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.addActionListener(new ListenerBotaoVoltar());

		JPanel Caixa = (JPanel) getContentPane();
		Container c = getContentPane(); // CONTAINER PARA COLOCAR COR DE FUNDO
										// DA TELA

		Caixa.add(labelCaixa).setBounds(110, 0, 70, 30);
		Caixa.add(labelValorCaixa).setBounds(50, 40, 200, 30);
		Caixa.add(BotaoVoltar).setBounds(205, 80, 70, 25);

		setLayout(new BorderLayout()); // COMANDO PARA ÚLTIMO BOTÃO FICAR
										// CORRETO.
		c.setBackground(Color.WHITE); // COR DE FUNDO DO PAINEL
		setSize(300, 150); // TAMANHO DO PAINEL
		setVisible(true); // PAINEL VISIVEL

	} // FECHA CONSTRUTOR CAIXA

	// LISTENER BOTÃO VOLTAR
	class ListenerBotaoVoltar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose(); // FECHA JANELA ABERTA
		}
	}

} // FECHA CLASSE
