package too.trabalho.spa.tipos;

/**
 * Classe para obter o título da pesquisa e o arquivo correspondente a ela
 * @author Rafaela
 *
 */
public class TipoPesquisa {
	private int codigo;
	private String nome, hash;
	
	/**
	 * Cria uma pesquisa
	 */
	public TipoPesquisa() {
		nome = hash = "";
	}
	
	/**
	 * Cria uma pesquisa
	 * @param nome nome da pesquisa
	 * @param hash do arquivo
	 */
	public TipoPesquisa(String nome, String hash) {
		this.nome = nome;
		this.hash = hash;
	}
	
	/**
	 * Obtém o nome
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Determina o nome
	 * @param nome da pesquisa
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Obtém o hash
	 * @return hash do arquivo
	 */
	public String getHash() {
		return hash;
	}
	
	/**
	 * Determina o hash
	 * @param hash do arquivo
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	/**
	 * Obtém o código
	 * @return código
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Determina codigo
	 * @param codigo da pesquisa
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return String.format("Código: %d, nome: %s, hash: %s", codigo, nome, hash);
	}
}
