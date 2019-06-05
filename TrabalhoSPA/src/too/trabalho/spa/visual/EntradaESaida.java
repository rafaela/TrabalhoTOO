package too.trabalho.spa.visual;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 * Classe para métodos de entrada e saída de dados e informações
 * @author Rafaela
 *
 */
public class EntradaESaida {
	
	/**
	 * Método para oferecer ao usuário uma informação.
	 * @param título da janela
     * @param texto informação sobre o que deve ser feito.
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
	 
	 /**
	  * Método para que o usuário confirme ou cancele uma decisão.
	  * @param título da janela
	  * @param texto informação sobre o que deve ser feito.
	  * @return
	  */
	 public static String alertaConfirma(String título, String texto){
		 Alert alert = new Alert(AlertType.CONFIRMATION);
		 alert.setTitle(título);
		 alert.setContentText(texto);
		 ButtonType btnSim = new ButtonType("Sim");
		 ButtonType btnNao = new ButtonType("Não");
		 alert.getButtonTypes().setAll(btnSim, btnNao);
		 Optional<ButtonType> resposta = alert.showAndWait();
		 if(resposta.get() == btnSim)
			 return "Sim";
		 else
			 return "Não";
	 }
	 
	 /**
	  * Método para entrada de dados do usuário.
	  * @param título da janela
	  * @param texto informação sobre o que deve ser feito.
	  * @return string fornecida pelo usuário
	  */
	 public static String alertaPergunta(String título, String texto){
		 TextInputDialog tid = new TextInputDialog();
		 
		 tid.setTitle(título);
		 tid.setContentText(texto);
		 
		 Optional<String> resposta = tid.showAndWait();
		 return resposta.get();
	 }

}
