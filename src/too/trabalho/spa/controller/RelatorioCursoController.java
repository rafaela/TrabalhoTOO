package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Constantes.TITULO_GRAFICO_CURSO;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import too.trabalho.spa.dao.PesquisaDAO;
import too.trabalho.spa.dao.TituloPesquisaDAO;
import too.trabalho.spa.tipos.PesquisaRelatorio;

/**
 * Classe resposável por gerar o relatório por curso.
 * @author Rafaela
 *
 */
public class RelatorioCursoController{
	
	@FXML TableView<PesquisaRelatorio> relatorio;
	@FXML TableColumn<PesquisaRelatorio, String> cursos;
	@FXML TableColumn<PesquisaRelatorio, Integer> participantes;
	@FXML SwingNode swingNode;
	@FXML ComboBox<String> comboPesquisa;
	// @FXML Pane pane;
	
	/**
	 * Obtém os dados contidos no banco e insere em uma tabela.
	 */
	@FXML
	public void initialize(){
		PesquisaDAO pesquisaDAO = new PesquisaDAO();
		swingNode.setVisible(false);
		ObservableList<String> opcoesPesquisa = FXCollections.observableArrayList(
				new TituloPesquisaDAO().obtemNome());
		comboPesquisa.setItems(opcoesPesquisa);
		comboPesquisa.getSelectionModel().selectFirst();
		
		cursos.setCellValueFactory(new PropertyValueFactory<>("nome"));
		participantes.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		List<PesquisaRelatorio> pesquisaList = pesquisaDAO.obtemQdtCursos(
				comboPesquisa.getSelectionModel().getSelectedItem());
		ObservableList<PesquisaRelatorio> relatorioList = FXCollections.observableList
				(pesquisaList);
		
		relatorio.setItems(relatorioList);
		geraGrafico(pesquisaList);
	}
	
	/**
	 * Gera o gráfico.
	 * @param lista contendo os dados
	 */
	public void geraGrafico(List<PesquisaRelatorio> lista){
		DefaultCategoryDataset dataset= new DefaultCategoryDataset();
		
		for(PesquisaRelatorio p : lista){
			dataset.addValue(p.getQuantidade(), p.getNome(), p.getNome());
		}
		
		JFreeChart chart = ChartFactory.createBarChart(TITULO_GRAFICO_CURSO, "",
				"", dataset, PlotOrientation.HORIZONTAL,
				false, true, false);
		
		swingNode.setContent( new ChartPanel(chart, 600, 280, 0, 0, 620, 300, false, false, false, 
				true, true, true));
		swingNode.setVisible(true);
	}
}
