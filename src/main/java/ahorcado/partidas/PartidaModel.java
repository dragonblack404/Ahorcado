package ahorcado.partidas;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PartidaModel {
	private ListProperty<Image> lista =new SimpleListProperty<Image>(FXCollections.observableArrayList());
	private StringProperty texto, oculta, palabra, topPlayer,player,falladasLetras;
	private ListProperty<String> palabrasJuego;
	private IntegerProperty vidas, maxPoint, point;
	private BooleanProperty enJuego;
	private ObjectProperty<Image> imagen;
	public PartidaModel() {
		player=new SimpleStringProperty();
		texto=new SimpleStringProperty();
		oculta=new SimpleStringProperty();
		palabra=new SimpleStringProperty();
		topPlayer=new SimpleStringProperty();
		falladasLetras=new SimpleStringProperty("");
		palabrasJuego=new SimpleListProperty<String>(this,"palabrasJuego",FXCollections.observableArrayList());
		vidas=new SimpleIntegerProperty(); 
		maxPoint=new SimpleIntegerProperty();
		point=new SimpleIntegerProperty();
		enJuego=new SimpleBooleanProperty(this,"enJuego",true);
		imagen=new SimpleObjectProperty<Image>();
		
	}

	 
	public final StringProperty textoProperty() {
		return this.texto;
	}
	

	public final String getTexto() {
		return this.textoProperty().get();
	}
	

	public final void setTexto(final String texto) {
		this.textoProperty().set(texto);
	}
	

	public final StringProperty ocultaProperty() {
		return this.oculta;
	}
	

	public final String getOculta() {
		return this.ocultaProperty().get();
	}
	

	public final void setOculta(final String oculta) {
		this.ocultaProperty().set(oculta);
	}
	

	public final StringProperty palabraProperty() {
		return this.palabra;
	}
	

	public final String getPalabra() {
		return this.palabraProperty().get();
	}
	

	public final void setPalabra(final String palabra) {
		this.palabraProperty().set(palabra);
	}
	

	public final StringProperty topPlayerProperty() {
		return this.topPlayer;
	}
	

	public final String getTopPlayer() {
		return this.topPlayerProperty().get();
	}
	

	public final void setTopPlayer(final String topPlayer) {
		this.topPlayerProperty().set(topPlayer);
	}
	

	public final StringProperty playerProperty() {
		return this.player;
	}
	

	public final String getPlayer() {
		return this.playerProperty().get();
	}
	

	public final void setPlayer(final String player) {
		this.playerProperty().set(player);
	}
	

	public final ListProperty<String> palabrasJuegoProperty() {
		return this.palabrasJuego;
	}
	

	public final ObservableList<String> getPalabrasJuego() {
		return this.palabrasJuegoProperty().get();
	}
	

	public final void setPalabrasJuego(final ObservableList<String> palabrasJuego) {
		this.palabrasJuegoProperty().set(palabrasJuego);
	}
	

	public final IntegerProperty vidasProperty() {
		return this.vidas;
	}
	

	public final int getVidas() {
		return this.vidasProperty().get();
	}
	

	public final void setVidas(final int vidas) {
		this.vidasProperty().set(vidas);
	}
	

	public final IntegerProperty maxPointProperty() {
		return this.maxPoint;
	}
	

	public final int getMaxPoint() {
		return this.maxPointProperty().get();
	}
	

	public final void setMaxPoint(final int maxPoint) {
		this.maxPointProperty().set(maxPoint);
	}
	

	public final IntegerProperty pointProperty() {
		return this.point;
	}
	

	public final int getPoint() {
		return this.pointProperty().get();
	}
	

	public final void setPoint(final int point) {
		this.pointProperty().set(point);
	}

	public final StringProperty falladasLetrasProperty() {
		return this.falladasLetras;
	}
	

	public final String getFalladasLetras() {
		return this.falladasLetrasProperty().get();
	}
	

	public final void setFalladasLetras(final String falladasLetras) {
		this.falladasLetrasProperty().set(falladasLetras);
	}

	public final BooleanProperty enJuegoProperty() {
		return this.enJuego;
	}
	

	public final boolean isEnJuego() {
		return this.enJuegoProperty().get();
	}
	

	public final void setEnJuego(final boolean enJuego) {
		this.enJuegoProperty().set(enJuego);
	}


	public final ObjectProperty<Image> imagenProperty() {
		return this.imagen;
	}
	


	public final Image getImagen() {
		return this.imagenProperty().get();
	}
	


	public final void setImagen(final Image imagen) {
		this.imagenProperty().set(imagen);
	}


	public final ListProperty<Image> listaProperty() {
		return this.lista;
	}
	


	public final ObservableList<Image> getLista() {
		return this.listaProperty().get();
	}
	


	public final void setLista(final ObservableList<Image> lista) {
		this.listaProperty().set(lista);
	}
	
	
	
	
	

	
	
}
