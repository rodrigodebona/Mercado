public class Produtos {

	private int codigoProduto;
	private String nomeProduto;
	private String valorProtudo;
	private String excluido;
	private String estoqueProduto;

	public Produtos(String excluido, int codigoProduto, String nomeProduto,
			String valorProduto, String estoqueProduto) {

		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.valorProtudo = valorProduto;
		this.excluido = excluido;
		this.estoqueProduto = estoqueProduto;

	}

	public Produtos() {
		// CONSTRUTOR PRODUTOS
	}

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
		this.excluido = excluido;
	}

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getValorProtudo() {
		return valorProtudo;
	}

	public void setValorProtudo(String valorProtudo) {
		this.valorProtudo = valorProtudo;
	}

	public String getEstoqueProduto() {
		return estoqueProduto;
	}

	public void setEstoqueProduto(String estoqueProduto) {
		this.estoqueProduto = estoqueProduto;
	}

}