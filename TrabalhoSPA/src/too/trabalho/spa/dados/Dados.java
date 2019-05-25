package too.trabalho.spa.dados;

import static too.trabalho.spa.arquivo.Arquivo.abreELeDados;
import static too.trabalho.spa.arquivo.Arquivo.obtemArquivos;
import static too.trabalho.spa.visual.EntradaESaida.*;
import static too.trabalho.spa.dados.Constantes.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.dao.PesquisaDAO;
import too.trabalho.spa.dao.QuestionarioDAO;
import too.trabalho.spa.tipos.Pesquisa;
import too.trabalho.spa.tipos.Questionario;

/**
 * Classe para obtenção de dados.
 * @author Rafaela
 *
 */
public class Dados {
	public static PesquisaDAO pesquisaDAO = new PesquisaDAO();
	public static QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	/**
	 * Lê os dados do arquivo de texto
	 * @return <code>String</code> dados do texto do arquivo
	 */
	public static String dados(){
		List<File> lista = new ArrayList<>();
		String conteudo = "";
		lista = obtemArquivos();
		if(lista != null)
			for(File file : lista){
				conteudo += abreELeDados(file.getAbsolutePath());
			}
		return conteudo;
	}
	
	/**
	 * Obtém as perguntas que foram feitas durante a pesquisa.
	 * @return um <code>Array</code> contendo as perguntas que foram feitas 
	 * na pesquisa
	 */
	public static String[] obtemPerguntas(String dados){
		String linha1 = dados.split("\n")[0];
		return linha1.replace("- ", ".").split(";");
	}
	
	/**
	 * Obtém as respostas que foram ditas durante a pesquisa.
	 * @return uma lista contendo todas as respostas que foram obtidas durante
	 * a pesquisa.
	 */
	public static List<Pesquisa> obtemRespostas(){
		List<Pesquisa> pesquisaList = new ArrayList<>();
		String dados = dados();
		String perguntas[] = obtemPerguntas(dados);
		String linhas[] = dados.split("\n");
		for(int i = 1; i < linhas.length; i++){
			Pesquisa pesquisa = new Pesquisa();
			pesquisa.setQuestionado(linhas[i].split(";")[0]);
			pesquisa.setCampus(linhas[i].split(";")[1]);
			if(linhas[i].split(";")[2] != "")
				pesquisa.setCurso(linhas[i].split(";")[2]);
			else
				pesquisa.setCurso("");
			for(int j = 3; j < perguntas.length; j++){
				Questionario questionario = new Questionario();
				questionario.setCodigo(Integer.parseInt(perguntas[j].substring(0, 3).
						replace(".", "").replace(". ", "").replace(" ", "")));
				questionario.setPergunta(perguntas[j].substring(3));
				questionario.setConceito(linhas[i].split(";")[j]);
				pesquisa.insere(questionario);
			}
			pesquisaList.add(pesquisa);
		}
		return pesquisaList;
	}
	/**
	 * Insere a pesquisa no banco de dados.
	 */
	public static void inserePesquisa(){
		List<Pesquisa> pesquisaList = new ArrayList<>();
		pesquisaList = obtemRespostas();
		
		for(Pesquisa pesquisa : pesquisaList)
			pesquisaDAO.insere(pesquisa);
		
		insereResposta(pesquisaList);
		alertaInformacao(IMPORTACAO, ALERTA_SUCESSO_IMPORTACAO);
	}
	/**
	 * insere as respostas da pesquisa no banco
	 * @param pesquisaList 
	 */
	public static void insereResposta(List<Pesquisa> pesquisaList){
		List<Integer> codigoslist = pesquisaDAO.obtemCodigo();
		int count = 0;
		for(Pesquisa pesquisa : pesquisaList)
			for(int i = 0; i < pesquisa.getQuestionario().size(); i++){
				questionarioDAO.insere(pesquisa.getQuestionario().get(i), codigoslist.get(count));
			}
	}//insereResposta
}
