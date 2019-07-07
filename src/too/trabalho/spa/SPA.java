package too.trabalho.spa;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/too/trabalho/spa/visual/SPA.fxml"));
			Scene scene = new Scene(root,680, 620);
			scene.getStylesheets().add(getClass().getResource("/too/trabalho/spa/visual/application.css").
					toExternalForm());
			primaryStage.setTitle("SPA - Barbacena");
			
			primaryStage.getIcons().add(new Image(getClass().getResource(""
					+ "/too/trabalho/spa/visual/imagens/icon.png").toExternalForm()));

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
