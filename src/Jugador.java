public class Jugador {
    private final String nombre;
    private int puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        puntaje = 0;
    }

    /**
     * Obtiene la puntuación del jugador.
     * @return Puntos del jugador.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Agrega una cantidad determinada de puntos.
     * Esta puede ser positiva o negativa, dependiendo
     * si se quiere beneficiar o perjudicar al jugador según las reglas del juego
     * @param puntos La cantidad de puntos para agregar.
     */
    public void agregarPuntos(int puntos) {
        puntaje += puntos;
    }

    /**
     * Muestra en la consola la puntuación de este jugador, junto con su nombre.
     */
    public void mostrarPuntaje() {
        System.out.printf("Puntos de %s: %d%n", nombre, puntaje);
    }

    /**
     * Simplemente, regresa una cadena con el nombre del jugador.
     * @return Nombre del jugador en String.
     */
    @Override
    public String toString() {
        return nombre;
    }
}