package ahorcado.puntos;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PuntosModel {
	private ListProperty<Puntuacion> puntosList;
	private ListProperty<String> listaTop;
	public PuntosModel() {
		puntosList=new SimpleListProperty<Puntuacion>(this,"puntosList",FXCollections.observableArrayList());
		listaTop=new SimpleListProperty<String>(this,"puntosList",FXCollections.observableArrayList());
	}
	public final ListProperty<Puntuacion> puntosListProperty() {
		return this.puntosList;
	}
	
	public final ObservableList<Puntuacion> getPuntosList() {
		return this.puntosListProperty().get();
	}
	
	public final void setPuntosList(final ObservableList<Puntuacion> puntosList) {
		this.puntosListProperty().set(puntosList);
	}
	public final ListProperty<String> ListaTopProperty() {
		return this.listaTop;
	}
	
	public final ObservableList<String> getListaTop() {
		return this.ListaTopProperty().get();
	}
	
	public final void setListaTop(final ObservableList<String> ListaTop) {
		this.ListaTopProperty().set(ListaTop);
	}
	
	

}
