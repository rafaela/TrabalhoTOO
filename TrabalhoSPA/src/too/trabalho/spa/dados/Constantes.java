package too.trabalho.spa.dados;

import java.io.File;

/**
 * Interface para constantes literais.
 * @author Rafaela
 *
 */
public interface Constantes {
	public static String ALERTA_SUCESSO_IMPORTACAO = "Importação de dados finalizada com sucesso",
						 ALERTA_ERRO_IMPORTACAO = "Não foi possível realizar a importação. Verifique "
						 		+ "a integridade do arquivo de importação",
						 IMPORTACAO = "Importação de dados",
						 TITULO_GRAFICO_CURSO = "Relatório de participantes por curso",
						 TITULO_GRAFICO_QUEST = "Relatório de participantes por categoria",
						 TIPO_ARQUIVO = "CSV Files",
						 EXTENSAO = "*.csv",
						 CAMINHO = "arquivos" + File.separator,
						 ARQUIVO_IMPORTACAO = "Arquivo já importado. Deseja substituir?",
						 NOME_PESQUISA = "Nome da pesquisa: ";
}
