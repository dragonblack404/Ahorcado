package ahorcado.palabras;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PalabrasController implements Initializable {

	@FXML
	private BorderPane rootBorder;

	@FXML
	private VBox operationVbox;

	@FXML
	private Button addButton;

	@FXML
	private Button removeButton;

	@FXML
	private ListView<String> listaPalabras;

	private PalabrasModel model = new PalabrasModel();

	public PalabrasController() throws IOException {
		System.out.println(getClass().getResource("PalabrasView.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PalabrasView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		listaPalabras.itemsProperty().bind(model.listPalabrasProperty());
		model.selectedProperty().bind(listaPalabras.getSelectionModel().selectedItemProperty());
	}

	@FXML
	void onAddButtonAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Palabra");
		dialog.setHeaderText("Palabras ADD");
		dialog.setContentText("Nueva Palabra:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Pattern p = Pattern.compile("[^0-9]*");
			Matcher m = p.matcher(result.get());
			StringBuffer sb = new StringBuffer();
			if (m.find())
				model.getListPalabras().add(result.get().toUpperCase());
			System.out.println("Palabra: " + result.get());
		}
	}

	@FXML
	void onRemoveButtonAction(ActionEvent event) {
		if (model.getSelected() != null)
			model.getListPalabras().remove(model.getSelected());
		else if (model.getListPalabras().size() != 0) {
			model.getListPalabras().remove(model.getListPalabras().size() - 1);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("No quedán palabras.");
			alert.show();
		}
	}

	public BorderPane getView() {
		return rootBorder;
	}

	public PalabrasModel getModel() {
		return model;
	}
}
