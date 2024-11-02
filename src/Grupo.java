import java.util.ArrayList;
import java.util.Iterator;

public class Grupo {
    private final ArrayList<Jugador> jugadores;
    private Iterator<Jugador> iterador;
    private Jugador jugadorActual;
    private Jugador ganadorRonda;
    private Jugador ganadorJuego;

    /**
     * Constructor predeterminado de un grupo de jugadores.
     */
    public Grupo() {
        jugadores = new ArrayList<>();
        jugadorActual = null;
        ganadorRonda = null;
        ganadorJuego = null;
    }

    /**
     * Cambia el turno al siguiente jugador.
     * Si no hay jugadores en el grupo, no se realizará nada.
     * Si hay jugadores y aún no se ha jugado un turno, se elige al primer jugador para continuar los turnos.
     * Después de esto, se ciclarán los turnos entre los jugadores del grupo.
     */
    public void cambiarTurno() {
        if (jugadores.isEmpty()) {
            return;
        }

        if (jugadorActual == null || !iterador.hasNext()) {
            iterador = jugadores.iterator();
        }

        jugadorActual = iterador.next();
    }

    /**
     * Agrega un jugador al grupo.
     * @param jugador El jugador que será agregado al grupo.
     */
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Muestra los puntos que tiene cada jugador del grupo
     */
    public void mostrarPuntajes() {
        jugadores.forEach(Jugador::mostrarPuntaje);
    }

    /**
     * Regresa un arreglo con todas las puntuaciones de los jugadores.
     * Se utiliza primordialmente para determinar si se alcanzó la meta de puntos.
     * @return Un arreglo de int con los puntos de los jugadores.
     */
    public int[] obtenerPuntosJugadores() {
        int[] puntos = new int[jugadores.size()];
        int i = 0;
        for (Jugador jugador : jugadores) {
            puntos[i++] = jugador.getPuntaje();
        }
        return puntos;
    }

    /**
     * Regresa al jugador con el mayor puntaje del grupo.
     * Si ocurre un empate, el último jugador en tener este puntaje mayor es el que es elegido.
     * @return El jugador con el mayor puntaje.
     */
    public Jugador obtenerJugadorMayorPuntaje() {
        int puntajeMayor = Integer.MIN_VALUE;
        Jugador jugadorMayor = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntaje() >= puntajeMayor) {
                jugadorMayor = jugador;
                puntajeMayor = jugador.getPuntaje();
            }
        }
        return jugadorMayor;
    }

    /**
     * Obtiene al jugador que tiene el turno actual.
     * @return Jugador con el turno.
     */
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    /**
     * Obtiene al jugador que ha ganado la última ronda.
     * @return Jugador ganador de la última ronda.
     */
    public Jugador getGanadorRonda() {
        return ganadorRonda;
    }

    /**
     * Establece al jugador que ha ganado la última ronda.
     * @param ganadorRonda Jugador ganador de la última ronda.
     */
    public void setGanadorRonda(Jugador ganadorRonda) {
        this.ganadorRonda = ganadorRonda;
    }

    /**
     * Obtiene al jugador que ha ganado el juego.
     * @return Jugador ganador del juego.
     */
    public Jugador getGanadorJuego() {
        return ganadorJuego;
    }

    /**
     * Establece al jugador que ha ganado el juego en su totalidad.
     * @param ganadorJuego Jugador ganador del juego.
     */
    public void setGanadorJuego(Jugador ganadorJuego) {
        this.ganadorJuego = ganadorJuego;
    }
}