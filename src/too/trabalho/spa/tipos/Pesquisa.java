package too.trabalho.spa.tipos;

import java.util.ArrayList;
import java.util.List;

/**
 * Descreve as perguntas que são feitas em uma pesquisa.
 * @author Rafaela
 *
 */
public class Pesquisa {
	private int codigo, tipoPesquisa;
	private String questionado, campus, curso;
	private List<Questionario> questionario;
	
	/**
	 * Inicializa as variáveis de instância com o valor padrão.
	 */
	public Pesquisa() {
		questionado = campus = curso = "";
		questionario = new ArrayList<>();
	}
	
	/**
	 * Inicializa as variáveis de instância com o valor determinado pelo usuário.
	 *
	 * @param questionado Identifica quem respondeu a pesquisa. Pode ser
	 * Discente, Docente ou Técnico administrativo.
	 * @param campus Identifica o campus que o questionado pertence.
	 * @param curso Identifica o curso que o questionado faz.
	 * @param questionario lista de perguntas que foram feitas.
	 */
	public Pesquisa(String questionado, String campus, String curso, List<Questionario> questionario) {
		this.questionado = questionado;
		this.campus = campus;
		this.curso = curso;
		this.questionario = questionario;
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
	 * @param codigo da pesquisa
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtém o tipo da pesquisa
	 * @return o tipo da pesquisa
	 */
	public int getTipoPesquisa() {
		return tipoPesquisa;
	}
	
	/**
	 * Determina o tipo da pesquisa
	 * @param tipoPesquisa nome da pesquisa
	 */
	public void setTipoPesquisa(int tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * Obtém a pessoa que foi questionada.
	 * @return questionado
	 */
	public String getQuestionado() {
		return questionado;
	}
	

	/**
	 * Determina a pessoa que foi questionada.
	 * @param questionado pessoa que respondeu a pesquisa
	 */
	public void setQuestionado(String questionado) {
		this.questionado = questionado;
	}
	
	/**
	 * Obtém o campus
	 * @return campus
	 */
	public String getCampus() {
		return campus;
	}
	
	/**
	 * Determina o campus
	 * @param campus do questionado
	 */
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	/**
	 * Obtém o curso 
	 * @return curso
	 */
	public String getCurso() {
		return curso;
	}
	
	/**
	 * Determina o curso 
	 * @param curso do questionado
	 */
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	/**
	 * Obtém a lista de perguntas 
	 * @return a lista de questionario
	 */
	public List<Questionario> getQuestionario() {
		return questionario;
	}
	
	/**
	 * Deternima a lista de questionario
	 * @param questionario a ser inserido
	 */
	public void setQuestionario(List<Questionario> questionario) {
		this.questionario = questionario;
	}

	/**
	 * Insere uma pergunta no questionário
	 * @param q questionario que será inserido
	 */
	public void insere(Questionario q){
		questionario.add(q);
	}
	
	/**
	 * Transforma o objeto Questionário em uma representação <code>String</code>
	 */
	@Override
	public String toString() {
		return String.format("Questionado: %s, campus: %s, curso: %s, questionario: %s", questionado, 
				campus, curso, questionario);
	}
	
	
	
	
	
	
}
