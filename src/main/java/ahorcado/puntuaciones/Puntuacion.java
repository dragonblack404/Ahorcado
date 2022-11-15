package ahorcado.puntuaciones;

public class Puntuacion implements Comparable<Puntuacion> {

	private String nombre;
	private int puntuacion;
	
	public Puntuacion(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	@Override
	public String toString() {
		return nombre + " ha conseguido " + puntuacion + " puntos";
	}

	public String toCsvString() {
		return nombre + ";" + puntuacion + "\n";
	}

	@Override
	public int compareTo(Puntuacion o) {
		return o.getPuntuacion() - this.getPuntuacion();
	}
}
