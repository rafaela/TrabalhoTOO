package too.trabalho.spa.dados;

import static too.trabalho.spa.arquivo.Arquivo.abreELeDados;
import static too.trabalho.spa.arquivo.Arquivo.dialogoAbrirArquivo;
import static too.trabalho.spa.dados.Constantes.ALERTA_SUCESSO_IMPORTACAO;
import static too.trabalho.spa.dados.Constantes.ARQUIVO_IMPORTACAO;
import static too.trabalho.spa.dados.Constantes.IMPORTACAO;
import static too.trabalho.spa.dados.Constantes.NOME_PESQUISA;
import static too.trabalho.spa.visual.EntradaESaida.alertaConfirma;
import static too.trabalho.spa.visual.EntradaESaida.alertaInformacao;
import static too.trabalho.spa.visual.EntradaESaida.alertaPergunta;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import too.trabalho.spa.dao.ConceitoDAO;
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
	public static TituloPesquisaDAO tpDAO = new TituloPesquisaDAO();
	public static ConceitoDAO conceitoDAO = new ConceitoDAO();
	private static int cod_pesquisa = 0;
	
	/**
	 * Lê os dados do arquivo de texto, verifica se o arquivo já foi lido,
	 * se já, pergunta se deseja substituir os dados.
	 * @return <code>String</code> dados do texto do arquivo
	 */
	public static String dados(){
		String nomePesquisa = alertaPergunta(IMPORTACAO, NOME_PESQUISA);
		if(nomePesquisa != ""){
			File arquivo =  dialogoAbrirArquivo(IMPORTACAO);
			if(arquivo != null){
				String hash = geraHash(arquivo);
				//Verifica se o arquivo escolhido já está cadastrado
				TipoPesquisa tp = tpDAO.pesquisar(hash);
				String conteudo = "";
				if(tp == null){
					tp = new TipoPesquisa();
					TipoPesquisa tipoP = new TipoPesquisa(nomePesquisa, hash);
					tpDAO.insere(tipoP);
					tp = tpDAO.pesquisar(hash);
					cod_pesquisa = tp.getCodigo();
					conteudo += abreELeDados(arquivo.getAbsolutePath());
					return conteudo;
				}else{
					if(alertaConfirma(IMPORTACAO, ARQUIVO_IMPORTACAO).equals("Sim")){
						cod_pesquisa = tp.getCodigo();
						conteudo = abreELeDados(arquivo.getAbsolutePath());
					}
					return "";
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
	 * @param dados obtidos na leitura do arquivo
	 * @return um <code>Array</code> contendo as perguntas que foram feitas 
	 * na pesquisa
	 */
	public static List<Questionario> obtemPerguntas(String dados){
		List<Questionario> questionarioList = new ArrayList<>();
		String linha1 = dados.split("\n")[0];
		String perguntas[] = linha1.replace("- ", ".").split(";");
		for(int i = 3;i < perguntas.length; i++){
			Questionario questionario = new Questionario();
			
			if(perguntas[i].contains("[")){
				questionario.setPergunta(perguntas[i].substring(0, perguntas[i].indexOf("[")));
				questionario.setTipoPergunta(perguntas[i].substring(perguntas[i].indexOf("[") + 1,
						perguntas[i].indexOf("]")));
			}
			else
				questionario.setPergunta(perguntas[i]);
			questionarioList.add(questionario);	
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
		if(dados != ""){
			List<Questionario> questionarioList = obtemPerguntas(dados);
			String linhas[] = dados.split("\n");
			for(int i = 1; i < linhas.length; i++){
				Pesquisa pesquisa = new Pesquisa();
				String palavras[] = linhas[i].split(";");
				pesquisa.setQuestionado(palavras[0]);
				pesquisa.setCampus(palavras[1]);
				if(palavras[2] != "")
					pesquisa.setCurso(palavras[2]);
				else
					pesquisa.setCurso("");
				int posicao = 3;
				List<Questionario> questList = new ArrayList<>();
				for(Questionario quest : questionarioList){
					Questionario questNovo = new Questionario();
					questNovo.setPergunta(quest.getPergunta());
					questNovo.setTipoPergunta(quest.getTipoPergunta());
					
					int conceito = obtemConceito(palavras[posicao]);
					if(conceito == -1){
						System.out.println("igual "+ quest.getPergunta() + palavras[posicao]);
					}
						
					
					if(conceito != -1){
						questNovo.setConceito(conceito);
						System.out.println("Diferente");
					}
					
					questList.add(questNovo);
					posicao++;
				}
				pesquisa.setQuestionario(questList);
				pesquisa.setTipoPesquisa(cod_pesquisa);
				pesquisaList.add(pesquisa);
			}
			return pesquisaList;
		}//if
		return null;
	}//obtemRespostas()
	
	/**
	 * Obtém, no banco de dados, os conceitos cadastrados e compara com o que foi
	 * respondido no questionário
	 * @param conceito nota da avaliação
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
		if(pesquisaList != null){
			for(Pesquisa pesquisa : pesquisaList){
				pesquisaDAO.insere(pesquisa);
			}
			insereResposta(pesquisaList);
		}
			
	}
	/**
	 * Insere as respostas da pesquisa no banco
	 * @param pesquisaList lista contendo a pesquisa
	 */
	public static void insereResposta(List<Pesquisa> pesquisaList){
		List<Integer> codigosPesquisaList = pesquisaDAO.obtemCodigo();
		int count = 0;
		for(Pesquisa pesquisa : pesquisaList){
			for(Questionario quest :  pesquisa.getQuestionario())
				questionarioDAO.insere(codigosPesquisaList.get(count),quest);
			
			count++;
		}
		if(pesquisaList.size() > 0)	
			alertaInformacao(IMPORTACAO, ALERTA_SUCESSO_IMPORTACAO);
		
		System.out.println("OK");
	}//insereResposta
	
}
