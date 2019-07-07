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
	 * @param titulo da janela
     * @param texto informação sobre o que deve ser feito.
	 */
	 public static void alertaInformacao(String titulo, String texto){
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle(titulo);
		 alert.setContentText(texto);
		 alert.showAndWait();
	 }
	 
	 /**
	  * Método para alertar o usuário de um erro.
	  * @param titulo titulo da janela
	  * @param texto texto da janela
	  */
	 public static void alertaErro(String titulo, String texto){
		 Alert alert = new Alert(AlertType.ERROR);
		 alert.setTitle(titulo);
		 alert.setContentText(texto);
		 alert.showAndWait();
	 }
	 
	 /**
	  * Método para que o usuário confirme ou cancele uma decisão.
	  * @param titulo titulo da janela
	  * @param texto informação sobre o que deve ser feito.
	  * @return a opção do usuário
	  */
	 public static String alertaConfirma(String titulo, String texto){
		 Alert alert = new Alert(AlertType.CONFIRMATION);
		 alert.setTitle(titulo);
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
	  * @param titulo título da janela
	  * @param texto informação sobre o que deve ser feito.
	  * @return String fornecida pelo usuário
	  */
	 public static String alertaPergunta(String titulo, String texto){
		 try{
			 TextInputDialog tid = new TextInputDialog();
			 
			 tid.setTitle(titulo);
			 tid.setContentText(texto);
			 
			 Optional<String> resposta = tid.showAndWait();
			 return resposta.get();
		 }catch (Exception e) {
			 return "";
		 }
	 }

}
