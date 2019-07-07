package too.trabalho.spa.tipos;

/**
 * Classe para obter quantos de alunos de cada curso respondeu a pesquisa.
 * @author Rafaela
 *
 */
public class PesquisaRelatorio {
	private String nome;
	private int quantidade;
	
	/**
	 * Cria um pesquisaRelatorio
	 */
	public PesquisaRelatorio() {
		nome = "";
	}
	
	/**
	 * Cria um pesquisaRelatorio
	 * @param nome da categoria
	 * @param quantidade de pessoas que responderam a pergunta
	 */
	public PesquisaRelatorio(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	/**
	 * Obtém um nome
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Determina o nome
	 * @param nome da categoria
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Obtém a quantidade
	 * @return quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Determina a quantidade
	 * @param quantidade de pessoas
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return String.format("Nome=%s, quantidade=%s", nome, quantidade);
	}
}