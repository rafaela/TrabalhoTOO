package too.trabalho.spa;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Classe principal do programa.
 * @author Rafaela
 *
 */
public class SPA extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("visual/SPA.fxml"));
			Scene scene = new Scene(root,680,500);
			scene.getStylesheets().add(getClass().getResource("visual/application.css").
					toExternalForm());
			primaryStage.setTitle("SPA - Barbacena");
			/*
			Image image = new Image("/../imagens/logo-if.png");
			primaryStage.getIcons().add(image);
			*/
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
