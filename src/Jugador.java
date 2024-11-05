public class Jugador {
    private static int ID_COUNTER = 1;
    private final int id;
    private int puntaje;

    public Jugador() {
        puntaje = 0;
        id = ID_COUNTER++;
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
     * Muestra en la consola la puntuación de este jugador, junto con su número de jugador.
     *
     * @return
     */
    public String mostrarPuntaje() {
        return String.format("Puntos J%d: %d", id, puntaje);
    }

    @Override
    public String toString() {
        return String.format("Jugador %d", id);
    }
}