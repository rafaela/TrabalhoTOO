package too.trabalho.spa.dados;

import java.io.File;

/**
 * Interface para constantes literais.
 * @author Rafaela
 *
 */
public interface Constantes {
	public static String ALERTA_SUCESSO_IMPORTACAO = "Importação de dados finalizada com sucesso",
						 IMPORTACAO = "Importação de dados",
						 TITULO_GRAFICO_CURSO = "Relatório de participantes por curso",
						 TITULO_GRAFICO_QUEST = "Relatório de participantes por categoria",
						 TIPO_ARQUIVO = "CSV Files",
						 EXTENSAO = "*.csv",
						 CAMINHO = "arquivos" + File.separator,
						 ARQUIVO_IMPORTACAO = "Arquivo já importado. Deseja inserir os dados novamente?",
						 NOME_PESQUISA = "Nome da pesquisa: ",
						 TITULO_GRAFICO_AV_CURSO = "Relatório de Avaliação por curso",
						 TITULO_GRAFICO_AV_CAT = "Relatório de Avaliação por categoria",
						 TITULO_INST = "Instituto Federal do Sudeste de Minas Gerais - Campus"
						 		+ "Barbacena\nRelatório da",
						 CAMINHO_IMG = "imagens" + File.separator,
						 CAMINHO_PDF = "pdf" + File.separator,
						 ALERTA_PDF = "PDF",
						 ALERTA_PDF_MSG = "Arquivo PDF criado";
}
