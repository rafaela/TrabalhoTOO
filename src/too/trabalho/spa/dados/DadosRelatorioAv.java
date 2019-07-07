package too.trabalho.spa.dados;

import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.dao.PesquisaDAO;
import too.trabalho.spa.dao.QuestionarioDAO;
import too.trabalho.spa.tipos.RelatorioAvaliacao;
import too.trabalho.spa.tipos.RelatorioAvaliacao.MediaPergunta;
import too.trabalho.spa.tipos.Pesquisa;

/**
 * Classe para a obtenção de dados para o relatório de avaliação
 * @author Rafaela
 *
 */
public class DadosRelatorioAv {
	private QuestionarioDAO questDao = new QuestionarioDAO();
	
	/**
	 * Obtém os dados dos cursos
	 * @param curso tipo do curso
	 * @param nomePesquisa nome da pesquisa
	 * @return dados obtidos
	 */
	public List<RelatorioAvaliacao> obtemDadosCurso(String curso, String nomePesquisa){
		List<RelatorioAvaliacao> relatorio = new ArrayList<>();
		List<Pesquisa> listaPesquisa = questDao.obtemAvaliacaoPorCurso(curso, nomePesquisa);
		relatorio = obtemCursosEPerguntas(curso, nomePesquisa);
		for(RelatorioAvaliacao rel : relatorio){
			for(Pesquisa pesquisa : listaPesquisa){
				if(rel.getCategoria().equals(pesquisa.getCurso())){
					for(MediaPergunta md : rel.getMediaList()){
						if(md.getPergunta().equalsIgnoreCase(pesquisa.getQuestionario().get(0).getPergunta())){
							md.setQuantidade();
							md.setSoma(pesquisa.getQuestionario().get(0).getConceito());
						}
						rel.setMediaGeral(md);
					}
				}
			}
			
		}//for externo
		return relatorio;
	}
	
	/**
	 * Obtém os cursos e as perguntas
	 * @param curso tipo do curso
	 * @param nomePesquisa nome da pesquisa
	 * @return dados obtidos sobre os cursos e as perguntas
	 */
	public List<RelatorioAvaliacao> obtemCursosEPerguntas(String curso, String nomePesquisa){
		List<String> listaCursos = new PesquisaDAO().obtemCursos(curso, nomePesquisa);
		List<String> listaPerguntas = questDao.obtemPerguntas(nomePesquisa);
		List<RelatorioAvaliacao> relatorio = new ArrayList<>();
		for(String s : listaCursos){
			RelatorioAvaliacao rel = new RelatorioAvaliacao();
			rel.setCategoria(s);
			for(int i = 0; i < listaPerguntas.size(); i++){
				MediaPergunta mp = new MediaPergunta();
				mp.setPergunta(listaPerguntas.get(i));
				rel.adicionar(mp);
			}
			relatorio.add(rel);
		}
		return relatorio;
	}
	
	/**
	 * Obtém os dados dos cursos
	 * @param nomePesquisa nome da pesquisa
	 * @return dados obtidos
	 */
	public List<RelatorioAvaliacao> obtemDadosCategoria(String nomePesquisa){
		List<RelatorioAvaliacao> relatorio = new ArrayList<>();
		List<Pesquisa> listaPesquisa = questDao.obtemAvaliacaoPorCategoria(nomePesquisa);
		relatorio = obtemCategoriaEPerguntas(nomePesquisa);
		for(RelatorioAvaliacao rel : relatorio){
			for(Pesquisa pesquisa : listaPesquisa){
				if(rel.getCategoria().equals(pesquisa.getQuestionado())){
					for(MediaPergunta md : rel.getMediaList()){
						if(md.getPergunta().equals(pesquisa.getQuestionario().get(0).getPergunta())){
							md.setQuantidade();
							md.setSoma(pesquisa.getQuestionario().get(0).getConceito());
						}
					rel.setMediaGeral(md);
					}
						
				}
			}
		}//for externo
		return relatorio;
	}
	
	/**
	 * Obtém os cursos e as perguntas
	 * @param nomePesquisa nome da pesquisa
	 * @return dados obtidos sobre os cursos e as perguntas
	 */
	public List<RelatorioAvaliacao> obtemCategoriaEPerguntas(String nomePesquisa){
		List<String> listaCategoria = new PesquisaDAO().obtemCategoria(nomePesquisa);
		List<String> listaPerguntas = questDao.obtemPerguntas(nomePesquisa);
		List<RelatorioAvaliacao> relatorio = new ArrayList<>();
		for(String s : listaCategoria){
			RelatorioAvaliacao rel = new RelatorioAvaliacao();
			rel.setCategoria(s);
			for(int i = 0; i < listaPerguntas.size(); i++){
				MediaPergunta mp = new MediaPergunta();
				mp.setPergunta(listaPerguntas.get(i));
				rel.adicionar(mp);
			}
			relatorio.add(rel);
		}
		return relatorio;
	}
}															
