package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Dados.*;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
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
	 * Chama a tela responsável por gerar o relatório por curso
	 */
	@FXML
	void chamaRelatorioCurso(){
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/"
					+ "RelatorioCurso.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//chamaRelatorio
	
	/**
	 * Chama a tela responsável por gerar o relatório por categoria
	 */
	@FXML
	void chamaRelatorioCat(){
		try {
			SplitPane panel = (SplitPane)FXMLLoader.load(getClass().getResource("../visual/"
					+ "RelatorioQuest.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//chamaRelatorio
	
}
