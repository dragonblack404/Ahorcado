package ahorcado.puntos;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;


import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


public class PuntosController implements Initializable {

	@FXML
	private ListView<Puntuacion> listView;

	private PuntosModel model = new PuntosModel();

	public PuntosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PuntosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		listView.itemsProperty().bindBidirectional(model.puntosListProperty());
//		model.puntosListProperty().addListener((o,ov,nv)-> nuevaPuntuacion()); 
	}

	public ListView<Puntuacion> getView() {
		return listView;
	}

	public void nuevaPuntuacion() {
		
		
		if (model.getPuntosList().size()>=1){
		FXCollections.sort(model.getPuntosList());
		}
		///__TERMINAR
	}
	public PuntosModel getModel() {
		return model;
	}
}
