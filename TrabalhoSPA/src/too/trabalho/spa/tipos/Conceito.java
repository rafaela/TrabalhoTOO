package too.trabalho.spa.tipos;


/**
 * Determina o conceito e seu respectivo peso que pode ser respondido durante a pesquisa.
 * @author Rafaela
 *
 */
public enum Conceito {
	OTIMO("Ótimo", 5),
	BOM("Bom", 4),
	SATISFATORIO("Satisfatório", 3),
	RUIM("Ruim", 2),
	PESSIMO("Péssimo", 1),
	INEXISTENTE("Inexistente", 0),
	NAO_CONHECO("Não Conheço", 0);
	
	private String conceito;
	private int peso;
	
	/**
	 * Inicializa os atributos do objeto com os valores especificados pelo usuário da classe.
	 * @param conceito <code>String</code> com o nome do conceito.
	 * @param peso <code>int</code> com o valor do conceito.
	 */
	private Conceito(String conceito, int peso) {
		this.conceito = conceito;
		this.peso = peso;
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
}
