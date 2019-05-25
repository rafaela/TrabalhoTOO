package too.trabalho.spa.tipos;

import java.util.ArrayList;
import java.util.List;

/**
 * Descreve as perguntas que são feitas em uma pesquisa.
 * @author Rafaela
 *
 */
public class Pesquisa {
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
	 * Obtém a pessoa que foi questionada.
	 */
	public String getQuestionado() {
		return questionado;
	}
	
	/**
	 * Determina a pessoa que foi questionada.
	 */
	public void setQuestionado(String questionado) {
		this.questionado = questionado;
	}
	
	/**
	 * Obtém o campus
	 */
	public String getCampus() {
		return campus;
	}
	
	/**
	 * Determina o campus 
	 */
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	/**
	 * Obtém o curso 
	 */
	public String getCurso() {
		return curso;
	}
	
	/**
	 * Determina o curso 
	 */
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	/**
	 * Obtém a lista de perguntas 
	 */
	public List<Questionario> getQuestionario() {
		return questionario;
	}
	
	/**
	 * Insere uma pergunta no questionário
	 * @param questionario
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
