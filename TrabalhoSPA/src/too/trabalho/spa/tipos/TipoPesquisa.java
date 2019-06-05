package too.trabalho.spa.tipos;

/**
 * Classe para obter o título da pesquisa e o arquivo correspondente a ela
 * @author Rafaela
 *
 */
public class TipoPesquisa {
	private int codigo;
	private String nome, hash;
	
	public TipoPesquisa() {
		nome = hash = "";
	}

	public TipoPesquisa(String nome, String hash) {
		this.nome = nome;
		this.hash = hash;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return String.format("Código: %d, nome: %s, hash: %s", codigo, nome, hash);
	}
	
	
	
	
	
	
	
}
