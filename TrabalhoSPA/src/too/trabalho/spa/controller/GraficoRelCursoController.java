package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Constantes.TITULO_GRAFICO_REL;

import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import too.trabalho.spa.dados.PesquisaRelatorio;
import too.trabalho.spa.dao.PesquisaDAO;

/**
 * Controlador do visual do programa. Tela Principal a ser exibida
 * @author Rafaela
 *
 */
public class GraficoRelCursoController {
	
	@FXML Pane pane;
	@FXML Button btnTabela;
	
	@FXML
	public void initialize(){
		PesquisaDAO pesquisaDAO = new PesquisaDAO();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<PesquisaRelatorio> relatorios = pesquisaDAO.obtemCursos();
		
		for(PesquisaRelatorio p : relatorios){
			dataset.addValue(p.getQuantidade(),"", p.getNome());
		}
		
		JFreeChart chart = ChartFactory.createBarChart(TITULO_GRAFICO_REL, "",
				"", dataset, PlotOrientation.HORIZONTAL,
				false, true, false);
		
		//mudar tamanho do grafico e as cores.
		
		SwingNode node = new SwingNode();
		node.setContent( new ChartPanel(chart));
		pane.getChildren().add(node);
		pane.setVisible(true);	
	}
	
	@FXML
	public void btnClicado(){
		Stage stage = new Stage();
		
		Stage stageGrafico = (Stage) btnTabela.getScene().getWindow();
		try {
			SplitPane root = FXMLLoader.load(getClass().getResource("../visual/Relatorio.fxml"));
			 
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
