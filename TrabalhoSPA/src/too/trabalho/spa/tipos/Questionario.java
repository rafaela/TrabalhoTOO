package too.trabalho.spa.tipos;

/**
 * Determina a pergunta, o conceito e o código que indentifica
 * o tipo da pergunta que está sendo feita.
 * @author Rafaela
 *
 */
public class Questionario {
	private String pergunta;
	private String conceito;
	private int codigo;
	
	/**
	 * Inicializa as variáveis de instância com o valor padrão.
	 */
	public Questionario() {
		pergunta = conceito = "";  
	}

	/**
	 * Inicializa os atributos do objeto com os valores especificados pelo usuário da classe. 
	 * @param pergunta <code>String</code> com a pergunta que será feita
	 * @param conceito <code>Enum</code> com a resposta que foi dada a pergunta.
	 * @param codigo <code>int</code> com o código de identificação da pergunta
	 */
	public Questionario(String pergunta, String conceito, int codigo) {
		this.pergunta = pergunta;
		this.conceito = conceito;
		this.codigo = codigo;
	}

	/**
	 * Obtém a pergunta
	 */
	public String getPergunta() {
		return pergunta;
	}

	/**
	 * Determina a pergunta
	 */
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	/**
	 * Obtém o conceito
	 */
	public String getConceito() {
		return conceito;
	}

	/**
	 * Determina o conceito
	 */
	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	/**
	 * Obtém o código da pergunta
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Determina o código
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Transforma o objeto Questionário em uma representação <code>String</code>
	 */
	@Override
	public String toString() {
		return String.format("Pergunta: %s, conceito: %s, codigo: %s\n", pergunta, conceito, codigo);
	}
	
	

	
}
