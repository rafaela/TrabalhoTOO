package too.trabalho.spa.controller;

import static too.trabalho.spa.dados.Constantes.ALERTA_PDF;
import static too.trabalho.spa.dados.Constantes.ALERTA_PDF_MSG;
import static too.trabalho.spa.dados.Constantes.CAMINHO_IMG;
import static too.trabalho.spa.dados.Constantes.TITULO_GRAFICO_AV_CURSO;
import static too.trabalho.spa.visual.EntradaESaida.alertaInformacao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import too.trabalho.spa.arquivo.PDF;
import too.trabalho.spa.dados.DadosRelatorioAv;
import too.trabalho.spa.dao.TituloPesquisaDAO;
import too.trabalho.spa.tipos.RelatorioAvaliacao;
import too.trabalho.spa.tipos.RelatorioAvaliacao.MediaPergunta;

/**
 * Classe responsável por gerar o relatório da avaliação por cursos
 * @author Rafaela
 *
 */
public class RelatorioAvaliacaoController{

	@FXML TableView<String[]> relatorio;
	
	@FXML private RadioButton tec;
    @FXML private RadioButton licenciatura;
    @FXML private RadioButton bacharel;
    @FXML private RadioButton tecnologia;
    
    @FXML private ComboBox<String> comboPesquisa;
    
    @FXML private SwingNode swingNode;
    
    List<RelatorioAvaliacao> lista = new ArrayList<>();
    String nomeIMG = "";

	@FXML
	public void initialize(){
		ObservableList<String> opcoesPesquisa = FXCollections.observableArrayList(
				new TituloPesquisaDAO().obtemNome());
		comboPesquisa.setItems(opcoesPesquisa);
		comboPesquisa.getSelectionModel().selectFirst();
		swingNode.setVisible(false);
		bacharel.setSelected(true);
		geraTabela();
	}
	
	/**
	 * Gera a tabela de acordo com a opção do usuário.
	 */
	public void geraTabela(){
		ToggleGroup tg = new ToggleGroup();
		tec.setToggleGroup(tg);
		bacharel.setToggleGroup(tg);
		licenciatura.setToggleGroup(tg);
		tecnologia.setToggleGroup(tg);
		
		relatorio.getColumns().clear();
		relatorio.getItems().clear();
		
		
		
		if(tec.isSelected()){
			lista = new DadosRelatorioAv().obtemDadosCurso("Técnico",
					comboPesquisa.getSelectionModel().getSelectedItem());
			tabela(lista);
			nomeIMG = "Técnico";
			geraGrafico(lista);
			
		}else if (bacharel.isSelected()){
			lista = new DadosRelatorioAv().obtemDadosCurso("Bacharelado",
					comboPesquisa.getSelectionModel().getSelectedItem());
			tabela(lista);
			nomeIMG = "Bacharelado";
			geraGrafico(lista);
		}else if(licenciatura.isSelected()){
			lista = new DadosRelatorioAv().obtemDadosCurso("Licenciatura",
					comboPesquisa.getSelectionModel().getSelectedItem());
			tabela(lista);
			nomeIMG = "Licenciatura";
			geraGrafico(lista);
			
		}else if(tecnologia.isSelected()){
			lista = new DadosRelatorioAv().obtemDadosCurso("Tecnologia",
					comboPesquisa.getSelectionModel().getSelectedItem());
			tabela(lista);
			nomeIMG = "Tecnologia";
			geraGrafico(lista);
		}
	}
	
	/**
	 * Obtém os dados e cria a tabela.
	 * @param lista contendo os dados
	 */
	public void tabela(List<RelatorioAvaliacao> lista){
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
			TableColumn<String[], String> coluna = new TableColumn<>(
					lista.get(i - 1).getCategoria().contains("em") ? lista.get(i - 1).
							getCategoria().split("em ")[1] : lista.get(i - 1).getCategoria());
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
    	dados[0] = "Conceito médio geral por curso";
    	
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
		try {
			ChartUtilities.saveChartAsJPEG(new File(CAMINHO_IMG + nomeIMG+".jpg"),
					chart, 620, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ChartPanel cp = new ChartPanel(chart, 620, 250, 0, 0, 700, 320, 
				false, false, false, true, true, true);
		
		swingNode.setContent(cp);
		swingNode.setVisible(true);
	}
	
	/**
	 * Gera o arquivo PDF
	 */
	@FXML
	public void gerarPDF(){
		PDF.exportaPDF(lista, comboPesquisa.getSelectionModel().getSelectedItem(), nomeIMG);
		alertaInformacao(ALERTA_PDF, ALERTA_PDF_MSG);
	}

}
