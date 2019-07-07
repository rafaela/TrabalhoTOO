package too.trabalho.spa.tipos;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para o relatório de avaliações
 * @author Rafaela
 *
 */
public class RelatorioAvaliacao {
	private String categoria;
	private List<MediaPergunta> mediaList;
	private MediaPergunta mediaGeral;
	
	public RelatorioAvaliacao() {
		mediaList = new ArrayList<>();
		categoria = "";
	}
	
	/**
	 * Obtém a categoria
	 * @return categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Determina a categoria
	 * @param categoria pessoa referente aos dados
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * Obtém a categoria
	 * @return lista de médias
	 */
	public List<MediaPergunta> getMediaList() {
		return mediaList;
	}
	
	/**
	 * Determina a lista de médias
	 * @param mediaList lista de médias
	 */
	public void setMediaList(List<MediaPergunta> mediaList) {
		this.mediaList = mediaList;
	}
	
	/**
	 * Adiciona uma média à lista
	 * @param media a ser inserida
	 */
	public void adicionar(MediaPergunta media){
		mediaList.add(media);
	}
	
	/**
	 * Obtém a média geral dos cursos
	 * @return a média
	 */
	public double getMediaGeral() {
		for(MediaPergunta mp : mediaList){
			mediaGeral.quantidade += mp.getQuantidade();
			mediaGeral.soma += mp.getSoma();
		}
		return Math.round(mediaGeral.calculaMedia());
	}

	/**
	 * Determina a média geral
	 * @param mediaGeral de cada curso
	 */
	public void setMediaGeral(MediaPergunta mediaGeral) {
		this.mediaGeral = mediaGeral;
	}

	@Override
	public String toString() {
		return String.format("Curso: %s,mediaGeral: %s, %s", categoria,mediaGeral.calculaMedia(),  mediaList);
	}

	/**
	 * Classe para calcular a media da avaliação de cada pergunta
	 * @author Rafaela
	 *
	 */
	public static class MediaPergunta {
		private String pergunta;
		private int quantidade;
		private double soma;

		/**
		 * Obtém a pergunta
		 * @return pergunta
		 */
		public String getPergunta() {
			return pergunta;
		}
		
		/**
		 * Determina uma pergunta
		 * @param pergunta da avaliação
		 */
		public void setPergunta(String pergunta) {
			this.pergunta = pergunta;
		}
		
		/**
		 * Obtém a quantidade
		 * @return quantidade
		 */
		public int getQuantidade() {
			return quantidade;
		}

		/**
		 * Determina a quantidade
		 */
		public void setQuantidade() {
			quantidade++;
		}

		/**
		 * Obtém a soma
		 * @return soma
		 */
		public double getSoma() {
			return soma;
		}

		/**
		 * Determina a soma
		 * @param soma valor a ser somado
		 */
		public void setSoma(double soma) {
			this.soma += soma;
		}
		
		/**
		 * Calcula a média
		 * @return media
		 */
		public double calculaMedia(){
			return soma/quantidade;
		}

		@Override
		public String toString() {
			return String.format("\n%s, quantidade: %d, soma: %.1f, media: %.1f", pergunta, quantidade,
					soma, calculaMedia());
		}
	}

}
