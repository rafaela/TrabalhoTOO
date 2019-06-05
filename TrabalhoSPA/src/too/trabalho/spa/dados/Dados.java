package too.trabalho.spa.dados;

import static too.trabalho.spa.arquivo.Arquivo.abreELeDados;
import static too.trabalho.spa.arquivo.Arquivo.dialogoAbrirArquivo;
import static too.trabalho.spa.dados.Constantes.*;
import static too.trabalho.spa.visual.EntradaESaida.*;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.dao.ConceitoDAO;
import too.trabalho.spa.dao.PerguntaDAO;
import too.trabalho.spa.dao.PesquisaDAO;
import too.trabalho.spa.dao.QuestionarioDAO;
import too.trabalho.spa.dao.TituloPesquisaDAO;
import too.trabalho.spa.tipos.Conceito;
import too.trabalho.spa.tipos.Pesquisa;
import too.trabalho.spa.tipos.Questionario;
import too.trabalho.spa.tipos.TipoPesquisa;

/**
 * Classe para obtenção de dados de um arquivo e armazenar no banco de dados.
 * @author Rafaela
 *
 */
public class Dados {
	public static PesquisaDAO pesquisaDAO = new PesquisaDAO();
	public static QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	public static ConceitoDAO conceitoDAO = new ConceitoDAO();
	public static TituloPesquisaDAO tpDAO = new TituloPesquisaDAO();
	public static PerguntaDAO perguntaDAO = new PerguntaDAO();
	
	/**
	 * Lê os dados do arquivo de texto, verifica se o arquivo já foi lido,
	 * se já, pergunta se deseja substituir os dados.
	 * @return <code>String</code> dados do texto do arquivo
	 */
	public static String dados(){
		String nomePesquisa = alertaPergunta(IMPORTACAO, NOME_PESQUISA);
		//TODO melhorar
		if(nomePesquisa != null){
			File arquivo =  dialogoAbrirArquivo(IMPORTACAO);
			if(arquivo != null){
				String hash = geraHash(arquivo);
				String conteudo = "";
				//Verifica se o arquivo escolhido já está cadastrado
				TipoPesquisa tp = tpDAO.pesquisar(hash);
				if(tp.getHash().equals("")){
					TipoPesquisa tipoP = new TipoPesquisa(nomePesquisa, hash);
					tpDAO.insere(tipoP);
					conteudo += abreELeDados(arquivo.getAbsolutePath());
					conteudo += "\n" + tipoP.getCodigo();
					return conteudo;
				}else{
					if(alertaConfirma(IMPORTACAO, ARQUIVO_IMPORTACAO).equals("Sim")){
						conteudo = abreELeDados(arquivo.getAbsolutePath());
						conteudo += "\n" + tp.getCodigo();
					}
					return conteudo;
				}	
			}
			else
				return "";
		}else
			return "";
	}
	
	/**
	 * Gera uma hash md5 do arquivo escolhido pelo usuário
	 * @param file arquivo que irá gerar o hash
	 * @return <code>String</code> contendo o hash
	 */
	public static String geraHash(File file){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(file.getAbsolutePath().getBytes()));
			return hash.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "";
	}


	/**
	 * Obtém o tópico e o subtópico das perguntas que foram feitas durante a pesquisa.
	 * @return um <code>Array</code> contendo as perguntas que foram feitas 
	 * na pesquisa
	 */
	public static List<Questionario> obtemPerguntas(String dados){
		List<Questionario> questionarioList = new ArrayList<>();
		String linha1 = dados.split("\n")[0];
		String perguntas[] = linha1.replace("- ", ".").split(";");
		for(int i = 0;i < perguntas.length; i++){
			Questionario questionario = new Questionario();
			if(perguntas[i].contains("[")){
				questionario.setPergunta(perguntas[i].substring(0, perguntas[i].indexOf("[")));
				questionario.setTipoPergunta(perguntas[i].substring(perguntas[i].indexOf("[") + 1,
						perguntas[i].indexOf("]")));
			}
			else
				questionario.setPergunta(perguntas[i]);
			questionarioList.add(questionario);	
			perguntaDAO.insere(questionario);
		}
		return questionarioList;		
		
	}
	
	/**
	 * Obtém as respostas que foram ditas durante a pesquisa.
	 * @return uma lista contendo todas as respostas que foram obtidas durante
	 * a pesquisa.
	 */
	public static List<Pesquisa> obtemRespostas(){
		List<Pesquisa> pesquisaList = new ArrayList<>();
		String dados = dados();
		List<Questionario> questionarioList = obtemPerguntas(dados);
		String linhas[] = dados.split("\n");
		for(int i = 1; i < (linhas.length - 2); i++){
			Pesquisa pesquisa = new Pesquisa();
			pesquisa.setQuestionado(linhas[i].split(";")[0]);
			pesquisa.setCampus(linhas[i].split(";")[1]);
			if(linhas[i].split(";")[2] != "")
				pesquisa.setCurso(linhas[i].split(";")[2]);
			else
				pesquisa.setCurso("");
			for(int j = 3; j < questionarioList.size(); j++){
				int conceito = obtemConceito(linhas[i].split(";")[j]);
				if(conceito != -1)
					questionarioList.get(j).setConceito(obtemConceito(linhas[i].split(";")[j]));
				pesquisa.setQuestionario(questionarioList);
			}
			pesquisa.setTipoPesquisa(Integer.parseInt(linhas[linhas.length - 1]));
			pesquisaList.add(pesquisa);
		}
		return pesquisaList;
	}
	
	/**
	 * Obtém, no banco de dados, os conceitos cadastrados e compara com o que foi
	 * respondido no questionário
	 * @param conceito 
	 * @return o valor inteiro correspondente ao conceito respondido
	 */
	public static int obtemConceito(String conceito){
		List<Conceito> conceitos = conceitoDAO.listaConceito();
		for(Conceito c : conceitos){
			if(conceito.equalsIgnoreCase(c.getConceito()))
				return c.getCodigo();
		}
		return -1;
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
	}
	/**
	 * Insere as respostas da pesquisa no banco
	 * @param pesquisaList 
	 */
	public static void insereResposta(List<Pesquisa> pesquisaList){
		List<Integer> codigosPesquisaList = pesquisaDAO.obtemCodigo();
		List<Integer> codigosPerguntaList = perguntaDAO.obtemCodigo();
		//fazer validação de código de pergunta
		int count = 0;
		for(Pesquisa pesquisa : pesquisaList){
			for(int i = 3; i < pesquisa.getQuestionario().size(); i++)
				questionarioDAO.insere(codigosPesquisaList.get(count), codigosPerguntaList.get(i),
						 pesquisa.getQuestionario().get(i));
			count++;
		}
		if(pesquisaList.size() > 0)	
			alertaInformacao(IMPORTACAO, ALERTA_SUCESSO_IMPORTACAO);
		else
			alertaInformacao(IMPORTACAO, ALERTA_ERRO_IMPORTACAO);
	}//insereResposta
	
}
