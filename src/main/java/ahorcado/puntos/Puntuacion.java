package ahorcado.puntos;

public class Puntuacion implements Comparable<Puntuacion> {
	
		String nombre;
		int puntos;
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getPuntos() {
			return puntos;
		}
		public void setPuntos(int puntos) {
			this.puntos = puntos;
		}
		
	
		@Override
		public String toString() {
		String x=this.puntos+"-"+this.nombre;
		return x;
		}
		@Override
		public int compareTo(Puntuacion o) {
			if (this.puntos > o.puntos) 
				return -1;
			 if (this.puntos < o.puntos)
				return 1;
			return 0;
		}
}
