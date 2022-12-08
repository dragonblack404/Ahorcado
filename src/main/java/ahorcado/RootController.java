package ahorcado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ahorcado.palabras.PalabrasController;
import ahorcado.partida.PartidaController;
import ahorcado.puntuaciones.Puntuacion;
import ahorcado.puntuaciones.PuntuacionesController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController implements Initializable {
	
	// controllers
	
	private PartidaController partidaController = new PartidaController();
	private PuntuacionesController puntuacionesController = new PuntuacionesController();
	private PalabrasController palabrasController = new PalabrasController();

	// model
	
	private ListProperty<String> palabrasList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Puntuacion> puntuacionesList = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	// view
	
	@FXML
	private TabPane view;
	
	@FXML 
	private Tab partidaTab, palabrasTab, puntuacionesTab;
	
	public RootController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		palabrasTab.setContent(palabrasController.getView());
		partidaTab.setContent(partidaController.getView());
		puntuacionesTab.setContent(puntuacionesController.getView());
		
		// bindings
		
		palabrasController.palabrasProperty().bind(palabrasList);
		partidaController.palabrasProperty().bind(palabrasList);
		puntuacionesController.puntuacionesProperty().bind(puntuacionesList);
		
		// listeners
		
		partidaController.gameOverProperty().addListener((o, ov, nv) -> {
			if(nv == true) {
				String nombre = partidaController.getNombre();
				int puntuacion = partidaController.getPuntosGanados();
				puntuacionesController.addPuntuacion(nombre, puntuacion);
			}
		});
	}
	
	
	public void cargarDatos() {
		partidaController.cargarDatos();
	}
	
	public final ListProperty<String> palabrasProperty() {
		return this.palabrasList;
	}
	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}
	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
	}

		
	public final ListProperty<Puntuacion> puntuacionesProperty() {
		return this.puntuacionesList;
	}
	public final ObservableList<Puntuacion> getPuntuaciones() {
		return this.puntuacionesProperty().get();
	}
	public final void setPuntuaciones(final ObservableList<Puntuacion> puntuaciones) {
		this.puntuacionesProperty().set(puntuaciones);
	}
	
	public TabPane getView() {
		return view;
	}

}