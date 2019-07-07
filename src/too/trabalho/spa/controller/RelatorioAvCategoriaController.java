package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Constantes.TITULO_GRAFICO_AV_CURSO;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import too.trabalho.spa.dados.DadosRelatorioAv;
import too.trabalho.spa.dao.TituloPesquisaDAO;
import too.trabalho.spa.tipos.RelatorioAvaliacao;
import too.trabalho.spa.tipos.RelatorioAvaliacao.MediaPergunta;

/**
 * Classe responsável por gerar o relatório da avaliação por cursos
 * @author Rafaela
 *
 */
public class RelatorioAvCategoriaController{

	@FXML TableView<String[]> relatorio;
    
    @FXML private ComboBox<String> comboPesquisa;
    
    @FXML private SwingNode swingNode;
    

	@FXML
	public void initialize(){
		ObservableList<String> opcoesPesquisa = FXCollections.observableArrayList(
				new TituloPesquisaDAO().obtemNome());
		comboPesquisa.setItems(opcoesPesquisa);
		comboPesquisa.getSelectionModel().selectFirst();
		swingNode.setVisible(false);
		geraTabela();
	}
	
	/**
	 * Gera a tabela e o gráfico.
	 */
	public void geraTabela(){
		List<RelatorioAvaliacao> lista = new ArrayList<>();
		lista = new DadosRelatorioAv().obtemDadosCategoria(
				comboPesquisa.getSelectionModel().getSelectedItem());
		tabela(lista);
		geraGrafico(lista);
	}
	
	/**
	 * Obtém os dados e cria a tabela.
	 * @param lista contendo os dados
	 */
	public void tabela(List<RelatorioAvaliacao> lista){
		relatorio.getColumns().clear();
		relatorio.getItems().clear();
		
		int numLinhas = lista.get(0).getMediaList().size();
		if (numLinhas == 0) return ;

		int numColunas = lista.size() + 1;
		TableColumn<String[], String> columnPergunta = new TableColumn<>("Pergunta");
		final int columnInde = 0 ;
		columnPergunta.setCellValueFactory(cellData -> {
			String[] row = cellData.getValue();
		 	return new SimpleStringProperty(row[columnInde]);
		});
		relatorio.getColumns().add(columnPergunta);
		
		for(int i = 1; i < numColunas; i++){
			TableColumn<String[], String> coluna = new TableColumn<>(lista.get(i - 1).getCategoria());
			final int columnIndex = i ;
			coluna.setCellValueFactory(cellData -> {
				String[] row = cellData.getValue();
	            return new SimpleStringProperty(row[columnIndex]);
	        });
			relatorio.getColumns().add(coluna);
		}
		String[] perguntas = new String[lista.get(0).getMediaList().size()];
		for(int i = 0; i < lista.get(0).getMediaList().size(); i++)
			perguntas[i] = lista.get(0).getMediaList().get(i).getPergunta();
		for(int i = 0; i < numLinhas; i++){
			String dados[] = new String[numColunas + 1];
			dados[0] = perguntas[i];
			for(int z = 1; z < numColunas; z++){
				dados[z] = String.format("%.1f", lista.get(z-1).getMediaList().get(i).calculaMedia());
			}
			relatorio.getItems().add(dados);
		}
		String[] dados = new String[numColunas + 1];
    	dados[0] = "Conceito médio geral por categoria";
    	
 	    for (int i = 1; i < numColunas; i++) {
 			dados[i] = String.format("%.0f", lista.get(i-1).getMediaGeral());
 		}
        relatorio.getItems().add(dados);
	}
	
	/**
	 * Gera o gráfico.
	 * @param lista contendo os dados
	 */
	public void geraGrafico(List<RelatorioAvaliacao> lista){
		DefaultCategoryDataset dataset= new DefaultCategoryDataset();
		for(RelatorioAvaliacao rel : lista){
			for(MediaPergunta mp : rel.getMediaList()){
				dataset.addValue(mp.calculaMedia(), rel.getCategoria(), mp.getPergunta());
			}	
		}
		
		JFreeChart chart = ChartFactory.createLineChart(TITULO_GRAFICO_AV_CURSO, "",
				"",  dataset, PlotOrientation.VERTICAL,
				true, true, false);

		chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(
				CategoryLabelPositions.UP_45);
		
		
		swingNode.setContent( new ChartPanel(chart, 620, 250, 0, 0, 700, 320, 
				false, false, false, true, true, true));
		swingNode.setVisible(true);
	}

}
