package too.trabalho.spa.tipos;


/**
 * Determina o conceito e seu respectivo peso que pode ser respondido durante a pesquisa.
 * @author Rafaela
 *
 */
public class Conceito {
	private int codigo;
	private String conceito;
	private int peso;
	
	public Conceito() {
		conceito = "";
	}

	/**
	 * Inicializa os atributos do objeto com os valores especificados pelo usuário da classe.
	 * @param conceito <code>String</code> com o nome do conceito.
	 * @param peso <code>int</code> com o valor do conceito.
	 */
	public Conceito(String conceito, int peso) {
		this.conceito = conceito;
		this.peso = peso;
	}
	
	
	/**
	 * Obtém o código.
	 */
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o conceito.
	 */
	public String getConceito() {
		return conceito;
	}

	/**
	 * Determina o conceito.
	 */
	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	
	/**
	 * Obtém o peso de cada conceito.
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * Determina o peso que cada conceito possui.
	 * @param peso
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return String.format("Codigo: %s, conceito: %s, peso: %d", codigo, conceito, peso);
	}

	
	
	
}
