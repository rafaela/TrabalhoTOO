package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Constantes.TITULO_GRAFICO_QUEST;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
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
 * Classe resposável por gerar o relatório por categoria.
 * @author Rafaela
 *
 */
public class RelatorioQuestController{
	
	@FXML TableView<PesquisaRelatorio> relatorio;
	@FXML TableColumn<PesquisaRelatorio, String> categoria;
	@FXML TableColumn<PesquisaRelatorio, Integer> participantes;
	
	@FXML ComboBox<String> comboPesquisa;
	@FXML SwingNode swingNode;
	
	/**
	 * Obtém os dados contidos no banco e insere em uma tabela.
	 */
	@FXML
	public void initialize(){
		PesquisaDAO pesquisaDAO = new PesquisaDAO();
		
		ObservableList<String> opcoesPesquisa = FXCollections.observableArrayList(
				new TituloPesquisaDAO().obtemNome());
		swingNode.setVisible(false);
		comboPesquisa.setItems(opcoesPesquisa);
		comboPesquisa.getSelectionModel().selectFirst();
		categoria.setCellValueFactory(new PropertyValueFactory<>("nome"));
		participantes.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		List<PesquisaRelatorio> pesquisaList = pesquisaDAO.obtemQuestionados(
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
		
		JFreeChart chart = ChartFactory.createBarChart(TITULO_GRAFICO_QUEST, "",
				"", dataset, PlotOrientation.VERTICAL,
				false, true, false);
		
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setMaximumBarWidth(.07); 
		
		
		swingNode.setContent( new ChartPanel(chart, 500, 280, 0, 0, 520, 300, false, false, false, 
				true, true, true));
		swingNode.setVisible(true);
	}
	
}
