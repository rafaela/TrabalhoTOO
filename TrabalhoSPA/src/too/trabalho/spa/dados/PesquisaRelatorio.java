package too.trabalho.spa.dados;

/**
 * Classe para obter quantos de alunos de cada curso respondeu a pesquisa.
 * @author Rafaela
 *
 */
public class PesquisaRelatorio {
	private String nome;
	private int quantidade;
	
	public PesquisaRelatorio() {
		nome = "";
	}
	
	public PesquisaRelatorio(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return String.format("Nome=%s, quantidade=%s", nome, quantidade);
	}
}