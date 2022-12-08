package ahorcado;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

import ahorcado.puntuaciones.Puntuacion;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application {
	
	private static final File PALABRAS_FILE = new File("files/palabras.txt");
	private static final File PUNTUACIONES_FILE = new File("files/puntuaciones.csv");

	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Puntuacion> puntuaciones = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	public static Stage primaryStage;
	
	private RootController rootController;
	
	@Override
	public void init() throws Exception {
		System.out.println("inicializando...");

		if (PALABRAS_FILE.exists()) {
			palabras.addAll(
				Files.readAllLines(
					PALABRAS_FILE.toPath(), 
					StandardCharsets.UTF_8
				)
			);
		}
		
		if (PUNTUACIONES_FILE.exists()) {
			puntuaciones.addAll(
				Files.readAllLines(
					PUNTUACIONES_FILE.toPath(), 
					StandardCharsets.UTF_8
				)
				.stream()
				.map(total -> total.split(";"))
				.map(parts -> {
					String nombre = parts[0];
					int puntuacion = Integer.parseInt(parts[1]);
					return new Puntuacion(nombre, puntuacion);
				})
				.collect(Collectors.toList())
			);
		}
		
		rootController = new RootController();
		
		rootController.palabrasProperty().bind(palabras);
		rootController.puntuacionesProperty().bind(puntuaciones);
		
		rootController.cargarDatos();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		AhorcadoApp.primaryStage = primaryStage;
		
		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(new Scene(rootController.getView()));
		primaryStage.show();
		
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("terminando...");
		final StringBuffer contenidoPalabras = new StringBuffer();
		rootController.getPalabras().forEach(palabra -> contenidoPalabras.append(palabra + "\n"));
		Files.writeString(
				PALABRAS_FILE.toPath(), 
				contenidoPalabras.toString().trim(), 
				StandardCharsets.UTF_8, 
				StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING
		);
		
		String contenidoPuntuaciones = "";
		for(Puntuacion puntuacion:rootController.getPuntuaciones()) {
			contenidoPuntuaciones += puntuacion.toCsvString();
		}
		Files.writeString(
			PUNTUACIONES_FILE.toPath(),
			contenidoPuntuaciones.trim(),
			StandardCharsets.UTF_8,
			StandardOpenOption.CREATE,
			StandardOpenOption.TRUNCATE_EXISTING
		);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}