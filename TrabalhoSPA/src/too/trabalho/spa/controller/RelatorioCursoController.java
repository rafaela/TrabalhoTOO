package too.trabalho.spa.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import too.trabalho.spa.dados.PesquisaRelatorio;
import too.trabalho.spa.dao.PesquisaDAO;

/**
 * Controlador do visual do programa. Tela Principal a ser exibida
 * @author Rafaela
 *
 */
public class RelatorioCursoController {
	
	@FXML TableView<PesquisaRelatorio> relatorio;
	@FXML TableColumn<PesquisaRelatorio, String> cursos;
	@FXML TableColumn<PesquisaRelatorio, Integer> participantes;
	
	@FXML Button btnGrafico;
	
	@FXML
	public void initialize(){
		PesquisaDAO pesquisaDAO = new PesquisaDAO();
		
		cursos.setCellValueFactory(new PropertyValueFactory<>("nome"));
		participantes.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		ObservableList<PesquisaRelatorio> relatorioList = FXCollections.observableList
				(pesquisaDAO.obtemCursos());
		
		relatorio.setItems(relatorioList);
	}
	
	@FXML
	public void btnClicado(){
		Stage stage = new Stage();
		
		Stage stageGrafico = (Stage) btnGrafico.getScene().getWindow();
		try {
			SplitPane root = FXMLLoader.load(getClass().getResource("../visual/GraficoRel.fxml"));
			 
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);	
			stageGrafico.hide();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
