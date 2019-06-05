package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Constantes.TITULO_GRAFICO_CURSO;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import too.trabalho.spa.dao.PesquisaDAO;
import too.trabalho.spa.tipos.PesquisaRelatorio;

/**
 * Exibe o gráfico relacionado a quantidade de alunos de cada curso.
 * @author Rafaela
 *
 */
public class GraficoCursoController {
	
	@FXML AnchorPane pane;
	
	/**
	 * Gera o gráfico com os dados contido no banco de dados.
	 */
	@FXML
	public void initialize(){
		PesquisaDAO pesquisaDAO = new PesquisaDAO();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<PesquisaRelatorio> relatorios = pesquisaDAO.obtemCursos();
		
		for(PesquisaRelatorio p : relatorios){
			dataset.addValue(p.getQuantidade(), p.getNome(), p.getNome());
		}
		
		JFreeChart chart = ChartFactory.createBarChart(TITULO_GRAFICO_CURSO, "",
				"", dataset, PlotOrientation.HORIZONTAL,
				false, true, false);
		
		//mudar tamanho do grafico
		
		SwingNode node = new SwingNode();
		node.setContent( new ChartPanel(chart, 700, 400, 0, 0, 700, 400, false, false, false, true, true, true));
		pane.getChildren().add(node);
		pane.setVisible(true);	
	}
	
}
