package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Dados.inserePesquisa;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;


/**
 * Controlador do visual do programa. Tela Principal a ser exibida
 * @author Rafaela
 *
 */

public class SPAController {
	
	@FXML BorderPane root;
	
	/**
	 * Chama a tela responsável por importar os arquivos.
	 */
	@FXML
	void chamaImportaArquivo() {
		inserePesquisa();
	}
	
	/**
	 * Chama a tela responsável por gerar o relatório
	 */
	@FXML
	void chamaRelatorio(){
		try {
			SplitPane panel = (SplitPane)FXMLLoader.load(getClass().getResource("../visual/"
					+ "Relatorio.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//chamaRelatorio
	
}
