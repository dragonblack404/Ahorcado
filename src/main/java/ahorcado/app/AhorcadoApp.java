package ahorcado.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application{

	public static Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		AhorcadoApp.primaryStage = primaryStage;
		RootController controller = new RootController();
		Scene scene = new Scene(controller.getView(), 500, 320);
		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(scene);
		primaryStage.show();
//		primaryStage.setScene(new Scene(rootController.));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
