package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Dados.inserePesquisa;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("/too/trabalho/spa/visual/"
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
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("/too/trabalho/spa/visual/"
					+ "RelatorioQuest.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//chamaRelatorio
	
	/**
	 * Chama a tela responsável por gerar o relatório com a avaliação
	 */
	@FXML
	void chamaRelatorioAv(){
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("/too/trabalho/spa/visual/"
					+ "RelatorioAvaliacaoCurso.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//chamaRelatorio
	
	/**
	 * Chama a tela responsável por gerar o relatório com a avaliação por categoria
	 */
	@FXML
	void chamaRelAvCategoria(){
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("/too/trabalho/spa/visual/"
					+ "RelatorioAvaliacaoCategoria.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
