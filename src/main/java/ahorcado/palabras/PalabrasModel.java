package ahorcado.palabras;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PalabrasModel {

	private StringProperty selected = new SimpleStringProperty();
	 private ListProperty<String> listPalabras = new SimpleListProperty<String>(this, "listPalabra",
			FXCollections.observableArrayList());

	public PalabrasModel() {

	}

	public final StringProperty selectedProperty() {
		return this.selected;
	}

	public final String getSelected() {
		return this.selectedProperty().get();
	}

	public final void setSelected(final String selected) {
		this.selectedProperty().set(selected);
	}

	public final ListProperty<String> listPalabrasProperty() {
		return this.listPalabras;
	}

	public final ObservableList<String> getListPalabras() {
		return this.listPalabrasProperty().get();
	}

	public final void setListPalabras(final ObservableList<String> listPalabras) {
		this.listPalabrasProperty().set(listPalabras);
	}

}
