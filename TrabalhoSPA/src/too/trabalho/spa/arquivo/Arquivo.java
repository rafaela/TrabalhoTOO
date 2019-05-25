package too.trabalho.spa.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;

public class Arquivo {	
	
	/** 
	 * Exibe uma caixa de di�logo para o usu�rio indicar o nome do diret�rio e arquivo que ser� aberto. 
	 * 
	 * @param titulo <code>String</code> com o nome da barra de t�tulo da caixa de di�logo.
	 *        
	 * @return <code>List</code> com o nome dos arquivos a serem abertos. Se o usu�rio cancelar a 
	 * opera��o (clicar no bot�o "Cancelar") ser� retornado <code>null</code>.
	 *        
	 */
	
	public static List<File> dialogoAbrirArquivo( String titulo) {
		List<File> arquivos = new ArrayList<>();
		 FileChooser dialogoAbrir = new FileChooser();
		  dialogoAbrir.setTitle(titulo);
		  //dialogoAbrir.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
		  dialogoAbrir.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		  dialogoAbrir.setInitialDirectory(new File("arquivos" + File.separator));
		  //arquivos.addAll(dialogoAbrir.showOpenMultipleDialog(null));
		  arquivos.add(dialogoAbrir.showOpenDialog(null).getAbsoluteFile());
		  if(!arquivos.isEmpty())
			  return arquivos;
		  return null;
	}
	
	/**
	 * Obt�m os arquivos que foram selecionados pelo usu�rio
	 * @return array contendo dados dos arquivos.
	 */
	public static List<File> obtemArquivos(){
		//ArquivoTexto arquivoTexto = new ArquivoTexto();
		List<File> arquivos = dialogoAbrirArquivo("Escolha os arquivos");
		if(arquivos != null)
			return arquivos;
		
		return null;
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
