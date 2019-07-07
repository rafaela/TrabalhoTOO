package too.trabalho.spa.tipos;

/**
 * Determina a pergunta, o conceito e o código que indentifica
 * o tipo da pergunta que está sendo feita.
 * @author Rafaela
 *
 */
public class Questionario {
	private int codigo;
	private String pergunta;
	private String tipoPergunta;
	private int conceito;
	
	/**
	 * Inicializa as variáveis de instância com o valor padrão.
	 */
	public Questionario() {
		pergunta = "";  
	}

	/**
	 * Inicializa os atributos do objeto com os valores especificados pelo usuário da classe. 
	 * @param pergunta <code>String</code> com a pergunta que será feita
	 * @param conceito <code>Enum</code> com a resposta que foi dada a pergunta.
	 * @param tipoPergunta tipo da pergunta
	 */
	public Questionario(String pergunta, String tipoPergunta, int conceito) {
		this.pergunta = pergunta;
		this.tipoPergunta = tipoPergunta;
		this.conceito = conceito;
	}
	
	/**
	 * Obtém o código
	 * @return codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Determina o código
	 * @param codigo código do questionario
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém a pergunta
	 * @return pergunta
	 */
	public String getPergunta() {
		return pergunta;
	}

	
	/**
	 * Determina a pergunta
	 * @param pergunta feita na pesquisa
	 */
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	/**
	 * Obtém o tipo da pergunta 
	 * @return tipo da pergunta
	 * 
	 */
	public String getTipoPergunta() {
		return tipoPergunta;
	}
	
	/**
	 * Determina o tipo da pergunta.
	 * @param tipoPergunta tipo da pergunta 
	 */
	public void setTipoPergunta(String tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}

	/**
	 * Obtém o conceito
	 * @return conceito nota da avaliação
	 */
	public int getConceito() {
		return conceito;
	}

	/**
	 * Determina o conceito
	 * @param conceito nota da avaliação
	 */
	public void setConceito(int conceito) {
		this.conceito = conceito;
	}

	/**
	 * Transforma o objeto Questionário em uma representação <code>String</code>
	 */
	@Override
	public String toString() {
		return String.format("Código: %d, pergunta: %s, tipoPergunta: %s, conceito: %d",
				codigo, pergunta, tipoPergunta, conceito);
	}
}
