package too.trabalho.spa.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import too.trabalho.spa.dao.PesquisaDAO;
import too.trabalho.spa.tipos.PesquisaRelatorio;

/**
 * Classe resposável por gerar o relatório por categoria.
 * @author Rafaela
 *
 */
public class RelatorioQuestController extends Application{
	
	@FXML TableView<PesquisaRelatorio> relatorio;
	@FXML TableColumn<PesquisaRelatorio, String> categoria;
	@FXML TableColumn<PesquisaRelatorio, Integer> participantes;
	
	@FXML Button btnGrafico;
	
	/**
	 * Obtém os dados contidos no banco e insere em uma tabela.
	 */
	@FXML
	public void initialize(){
		PesquisaDAO pesquisaDAO = new PesquisaDAO();
		
		categoria.setCellValueFactory(new PropertyValueFactory<>("nome"));
		participantes.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		ObservableList<PesquisaRelatorio> relatorioList = FXCollections.observableList
				(pesquisaDAO.obtemQuestionados());
		
		relatorio.setItems(relatorioList);
	}
	
	/**
	 * Botão que ao ser clicado irá chamar a janela que contém o gráfico.
	 */
	@FXML
	public void btnClicado(){
		Stage stage = new Stage();
		
		try {
			start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Cria a janela que contém o gráfico.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../visual/GraficoQuest.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);	
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
