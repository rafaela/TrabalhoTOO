package too.trabalho.spa.arquivo;

import java.io.File;
import java.io.FileNotFoundException;

import static too.trabalho.spa.dados.Constantes.*;
import javafx.stage.FileChooser;

public class Arquivo {	
	
	/** 
	 * Exibe uma caixa de diálogo para o usuário indicar o nome do diretório e arquivo que será aberto. 
	 * 
	 * @param titulo <code>String</code> com o nome da barra de título da caixa de diálogo.
	 *        
	 * @return <code>List</code> com o nome dos arquivos a serem abertos. Se o usuário cancelar a 
	 * operação (clicar no botão "Cancelar") será retornado <code>null</code>.
	 *        
	 */
	
	public static File dialogoAbrirArquivo( String titulo) {
		FileChooser dialogoAbrir = new FileChooser();
		dialogoAbrir.setTitle(titulo);
		dialogoAbrir.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(TIPO_ARQUIVO,
				EXTENSAO));
		dialogoAbrir.setInitialDirectory(new File(CAMINHO));
		File arquivo = dialogoAbrir.showOpenDialog(null).getAbsoluteFile();
		return arquivo;
  
	}

	/**
	 * Le os dados do arquivo de texto
	 * @param pathName nome do arquivo a ser lido
	 * @return <code>String</code> dados do texto do arquivo
	 */
	public static String abreELeDados(String pathName){
		ArquivoTexto arquivoTexto = new ArquivoTexto();
		String dados = null;
		
		try {
			arquivoTexto.abrir(pathName);
			dados = arquivoTexto.ler();
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possivel abrir o arquivo " + pathName);
		}
		return dados;
	}
}
