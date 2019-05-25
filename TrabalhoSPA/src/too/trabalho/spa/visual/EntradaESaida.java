package too.trabalho.spa.visual;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe para métodos de entrada e saída de dados e informações
 * @author Rafaela
 *
 */
public class EntradaESaida {
	
	/**
	 * Método para oferecer ao usuário uma informação.
	 */
	 public static void alertaInformacao(String título, String texto){
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle(título);
		 alert.setContentText(texto);
		 alert.showAndWait();
	 }
	 
	 /**
	  * Método para alertar o usuário de um erro.
	  */
	 public static void alertaErro(String título, String texto){
		 Alert alert = new Alert(AlertType.ERROR);
		 alert.setTitle(título);
		 alert.setContentText(texto);
		 alert.showAndWait();
	 }

}
