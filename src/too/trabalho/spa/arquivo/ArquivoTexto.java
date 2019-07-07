package too.trabalho.spa.arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
/**
 * Fornece vários métodos para manipular um arquivo texto em disco.
 * @author Prof. Márlon Oliveira da Silva
 * @version 0.1
 */
public class ArquivoTexto {
	private Scanner inputScanner; 
	private FileInputStream fileInputStream; 
	private Formatter fileOutputFormatter;
	  
	/** 
	 * Abre um arquivo texto armazenado em disco somente para leitura.
	 * 
	 * @param nomeArquivo nome do arquivo a ser aberto.
	 * 
	 * @throws FileNotFoundException se o nome do arquivo n�o for encontrado.
	 */
	  public void abrir(String nomeArquivo) throws FileNotFoundException {
		  fileInputStream = new FileInputStream(nomeArquivo);
		  inputScanner = new Scanner(fileInputStream);
	  } 

	  /** 
	   * Cria um arquivo texto em disco. 
	   * @param nomeArquivo nome do arquivo a ser criado.
	   * 
	   * @throws FileNotFoundException se o nome do arquivo n�o for encontrado.
	   */
	  public void criar(String nomeArquivo) throws FileNotFoundException {
		  fileOutputFormatter = new Formatter(nomeArquivo);
	  } 

	  /** 
	   * Escreve no arquivo texto o conte�do do objeto <code>String</code> armazenado no
       * parâmetro conteudo. 
	   * 
	   * @param conteudo conteúdo a ser escrito no arquivo texto.
	   */
	  public void escrever(String conteudo) {
		  fileOutputFormatter.format("%s\n", conteudo);
	  } 
	  
	  /** 
	   * Lê o conteúdo completo do arquivo texto.
	   * 
	   * @return um <code>String</code> com o conte�do lido do arquivo texto.
	   *
	   * @throws IllegalStateException ocorre se o arquivo estiver fechado.  
	   */
	  public String ler() throws IllegalStateException {
		  String conteudo = "";
		  while (inputScanner.hasNextLine()) 
			  conteudo += inputScanner.nextLine() + "\n";
		  
		  return conteudo; 
	  } 
	  
	  /**
	   * Fecha os arquivos que foram criados para manipulação do arquivo texto.
	   * 
	   * @throws IOException se ocorrer algum erro de E/S ao tentar fechar o arquivo.
	   */
	  public void fechar() throws IOException {
		  if (fileInputStream != null) fileInputStream.close();
		  if (inputScanner != null) inputScanner.close();
		  if (fileOutputFormatter != null) fileOutputFormatter.close();
	  } // fecharArquivo()
	  
	  
} // class ArquivoTexto
