import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ReglasAhorcado {
    private final ArrayList<Jugador> grupoJugadores;
    private Jugador jugadorActual;
    private Jugador ganadorRonda;
    private Jugador ganadorJuego;

    private final BancoFrases bancoFrases;
    private String frase;
    private final HashSet<Character> letrasDescubiertas;

    private final int cantidadJugadores;
    private final int metaPuntos;

    public ReglasAhorcado(int cantidadJugadores, int metaPuntos) {
        this.cantidadJugadores = cantidadJugadores;
        this.metaPuntos = metaPuntos;

        grupoJugadores = new ArrayList<>();
        bancoFrases = new BancoFrases();
        letrasDescubiertas = new HashSet<>();
        crearYAgregarJugadores(cantidadJugadores);
        jugadorActual = grupoJugadores.getFirst();
    }

    public void jugarTurno(char letraSeleccionada) {
        if (!rondaTerminada()) {
            int puntosObtenidos = darPuntos(jugadorActual, letraSeleccionada);
            if (puntosObtenidos <= 0) cambiarTurno();
        }

        if (rondaTerminada()) {
            ganadorRonda = jugadorActual;
            darPuntosGanadorRonda();
        }

        if (metaPuntosAlcanzada()) {
            ganadorJuego = obtenerJugadorGanador();
        }
    }

//    public void iniciarJuego() {
//        while (!metaPuntosAlcanzada()) {
//            inicializarRonda();
//            while (!rondaTerminada()) {
//                cambiarTurno();
//                System.out.println();
//                mostrarPuntajes();
//                System.out.println();
//                mostrarLetrasDescubiertas();
//                System.out.println();
//                mostrarFraseDescubierta();
//                System.out.println();
//                darPuntos(jugadorActual, pedirLetra(jugadorActual));
//            }
//            System.out.print("La frase era: ");
//            mostrarFraseDescubierta();
//            grupoJugadores.setGanadorRonda(grupoJugadores.getJugadorActual());
//            darPuntosGanadorRonda();
//        }
//        mostrarPuntajes();
//        declararGanadorJuego();
//    }

    private void crearYAgregarJugadores(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            grupoJugadores.add(new Jugador());
        }
    }

    public int darPuntos(Jugador jugador, char letra) {
        int puntosObtenidos;
        if (letrasDescubiertas.contains(letra)) {
            puntosObtenidos = -3;
            jugador.agregarPuntos(puntosObtenidos);
        } else if (fraseContieneLetra(letra) && !letrasDescubiertas.contains(letra)) {
            letrasDescubiertas.add(letra);
            puntosObtenidos = 3* contarRepeticiones(letra);
            jugador.agregarPuntos(puntosObtenidos);
        } else {
            puntosObtenidos = -1;
            letrasDescubiertas.add(letra);
            jugador.agregarPuntos(puntosObtenidos);
        }
        return puntosObtenidos;
    }

    public void darPuntosGanadorRonda() {
        ganadorRonda.agregarPuntos(5);
    }

    public String mostrarFraseDescubierta() {
        StringBuilder sb = new StringBuilder();
        for (char c : frase.toCharArray()) {
            if (letrasDescubiertas.contains(c) && Character.isLetter(c)) {
                sb.append(c);
            } else if (!Character.isLetter(c)) {
                sb.append(c);
            } else {
                sb.append("_");
            }
        }
        return sb.toString();
    }

    public String mostrarPuntajes(String separador) {
        StringBuilder sb = new StringBuilder();
        for (Jugador jugador : grupoJugadores) {
            sb.append(jugador.mostrarPuntaje());
            if (jugador != grupoJugadores.getLast()) sb.append(separador);
        }
        return sb.toString();
    }


    public String mostrarLetrasDescubiertas() {
        StringBuilder sb = new StringBuilder();
        Iterator<Character> iterador = letrasDescubiertas.iterator();
        if (iterador.hasNext()) {
            while (iterador.hasNext()) {
                char c = iterador.next();
                if (Character.isLetter(c)) {
                    sb.append(c).append(" ");
                }
            }
        } else {
            sb.append("ninguna aún...");
        }
        return sb.toString();
    }

    public void inicializarRonda() {
        frase = bancoFrases.darFraseAleatoria();
        letrasDescubiertas.clear();
    }

    public String regresarNombreJugadorActual() {
        return jugadorActual.toString();
    }

    public Jugador obtenerJugadorGanador() {
        int puntajeMayor = Integer.MIN_VALUE;
        Jugador ganador = null;
        for (Jugador jugador : grupoJugadores) {
            if (jugador.getPuntaje() >= puntajeMayor) {
                ganador = jugador;
                puntajeMayor = jugador.getPuntaje();
            }
        }
        return ganador;
    }

    public boolean rondaTerminada() {
        Iterator<Character> iterador = frase.chars().mapToObj(c -> (char) c).iterator();
        while (iterador.hasNext()) {
            char c = iterador.next();
            if (!letrasDescubiertas.contains(c) && Character.isLetter(c)) return false;
        }
        return true;
    }

    public void cambiarTurno() {
        if (jugadorActual == null) {
            jugadorActual = grupoJugadores.getFirst();
        }

        if (jugadorActual == grupoJugadores.getLast()) {
            jugadorActual = grupoJugadores.getFirst();
        } else {
            int indiceJugadorActual = grupoJugadores.indexOf(jugadorActual);
            jugadorActual = grupoJugadores.get(indiceJugadorActual+1);
        }
    }

    public boolean metaPuntosAlcanzada() {
        return grupoJugadores.stream().anyMatch(jugador -> jugador.getPuntaje() >= metaPuntos);
    }

    private int contarRepeticiones(char letra) {
        int contador = 0;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == letra) {
                contador++;
            }
        }
        return contador;
    }

    private boolean fraseContieneLetra(char letra) {
        return frase.indexOf(letra) >= 0;
    }
}