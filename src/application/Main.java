package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{

	private FlowPane root;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {

			root = (FlowPane)FXMLLoader.load(getClass().getResource("/fxml/Examen_1.fxml"));
			stage.setTitle("TALLER");
			Scene scene = new Scene(root,830,650);
			stage.setScene(scene);
			stage.setMinWidth(830);
			stage.setMinHeight(650);
			stage.setMaxWidth(830);
			stage.setMaxHeight(650);
			//stage.getIcons().add(new Image(getClass().getResource("/img/avion.png").toString()));
			stage.show();	
		}catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
