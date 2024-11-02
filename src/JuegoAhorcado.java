import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class JuegoAhorcado {
    private final Grupo grupoJugadores;
    private final BancoFrases bancoFrases;
    private String fraseElegida;
    private int metaPuntos;
    private final HashSet<Character> letrasDescubiertas;

    public JuegoAhorcado() {
        grupoJugadores = new Grupo();
        bancoFrases = new BancoFrases();
        letrasDescubiertas = new HashSet<>();
    }

    /**
     * Realiza todos los métodos necesarios para ejecutar un juego de Ahorcado en su totalidad.
     */
    public void iniciarJuego() {
        int cantidadJugadores = introducirEntero("Ingrese el número de jugadores: ",2,4);
        crearYNombrarJugadores(cantidadJugadores);
        metaPuntos = introducirEntero("Ingrese la meta de puntos: ",1, -1);

        while (!juegoTerminado()) {
            inicializarRonda();
            while (!rondaTerminada()) {
                grupoJugadores.cambiarTurno();
                Jugador jugadorActual = grupoJugadores.getJugadorActual();
                System.out.println();
                mostrarPuntajes();
                System.out.println();
                mostrarLetrasDescubiertas();
                System.out.println();
                mostrarFraseDescubierta();
                System.out.println();
                darPuntos(jugadorActual, pedirLetra(jugadorActual));
            }
            System.out.print("La frase era: ");
            mostrarFraseDescubierta();
            grupoJugadores.setGanadorRonda(grupoJugadores.getJugadorActual());
            darPuntosGanadorRonda();
        }
        mostrarPuntajes();
        declararGanadorJuego();
    }

    /**
     * Crea una cantidad de jugadores y nombra a cada uno de ellos.
     * @param cantidad La cantidad elegida de jugadores.
     */
    public void crearYNombrarJugadores(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Jugador jugador = new Jugador(obtenerNombreJugador());
            grupoJugadores.agregarJugador(jugador);
        }
    }

    /**
     * Le pide al jugador indicado una letra para adivinar.
     * @param jugador El jugador al que se le pide la letra.
     * @return La letra que el jugador eligió.
     */
    public char pedirLetra(Jugador jugador) {
        Scanner entrada = new Scanner(System.in);
        char letra;
        do {
            System.out.printf("Jugador %s, ingrese una letra: ", jugador);
            letra = entrada.next().toUpperCase().charAt(0);
            if (!Character.isLetter(letra)) {
                System.out.println("Error: elija una letra y no otro carácter.");
            }
        } while (!Character.isLetter(letra));
        return letra;
    }

    /**
     * Según las reglas de este juego de Ahorcado, se le dan o se le quitan puntos al jugador dependiendo de la letra ingresada.
     * <p>
     * Si el jugador eligió una letra ya adivinada anteriormente, se le restará 3 puntos.
     * <p>
     * Si el jugador elige una letra que está en la frase y no ha sido adivinada, se le sumará 3 puntos por cada vez que aparece en la frase.
     * <p>
     * Si el jugador elige una letra que no está en la frase y no ha sido adivinada, se le restará un solo punto.
     * @param jugador El jugador al que se le atribuyen los puntos.
     * @param letra La letra que el jugador eligió.
     */
    public void darPuntos(Jugador jugador, char letra) {
        int puntosObtenidos;
        if (letrasDescubiertas.contains(letra)) {
            puntosObtenidos = -3;
            System.out.printf("El jugador %s ha perdido 3 puntos por elegir una letra ya existente...%n", jugador);
            jugador.agregarPuntos(puntosObtenidos);
        } else if (fraseContieneLetra(letra) && !letrasDescubiertas.contains(letra)) {
            letrasDescubiertas.add(letra);
            puntosObtenidos = 3* contarRepeticiones(letra);
            System.out.printf("¡El jugador %s ha ganado %d puntos por adivinar una letra!%n", jugador, puntosObtenidos);
            jugador.agregarPuntos(puntosObtenidos);
        } else {
            puntosObtenidos = -1;
            letrasDescubiertas.add(letra);
            System.out.printf("El jugador %s ha perdido 1 punto por no adivinar una letra...%n", jugador);
            jugador.agregarPuntos(puntosObtenidos);
        }
    }

    /**
     * Se le suman 5 puntos al jugador que descubrió la frase completa con su turno.
     */
    public void darPuntosGanadorRonda() {
        System.out.printf("¡El ganador de la ronda es %s! Se te sumó 5 puntos.%n", grupoJugadores.getGanadorRonda());
        grupoJugadores.getGanadorRonda().agregarPuntos(5);
    }

    /**
     * Muestra la frase parcialmente, mostrando solo las letras adivinadas.
     * <p>
     * Las letras que aún no se adivinan se muestran como guiones bajos.
     */
    public void mostrarFraseDescubierta() {
        for (char c : fraseElegida.toCharArray()) {
            if (letrasDescubiertas.contains(c) && Character.isLetter(c)) {
                System.out.print(c);
            } else if (!Character.isLetter(c)) {
                System.out.print(c);
            } else {
                System.out.print('_');
            }
        }
        System.out.println();
    }

    /**
     * Muestra los puntajes de cada jugador del grupo.
     */
    public void mostrarPuntajes() {
        grupoJugadores.mostrarPuntajes();
    }

    /**
     * Muestra una lista con las letras que ya han sido jugadas anteriormente.
     * <p>
     * Esto es particularmente útil para evitar que un jugador vuelva a elegirla, aunque eso no los detendrá de intentarlo...
     */
    public void mostrarLetrasDescubiertas() {
        System.out.println("Letras jugadas:");
        Iterator<Character> iterador = letrasDescubiertas.iterator();
        if (iterador.hasNext()) {
            while (iterador.hasNext()) {
                char c = iterador.next();
                if (Character.isLetter(c)) {
                    System.out.printf("%s ", c);
                }
            }
        } else {
            System.out.println("ninguna aún...");
        }
    }

    /**
     * Inicializa todos los parámetros para iniciar una nueva ronda.
     * <p>
     * Se elige una nueva frase del banco de frases y se limpian las letras descubiertas.
     */
    public void inicializarRonda() {
        fraseElegida = bancoFrases.darFraseAleatoria();
        letrasDescubiertas.clear();
    }

    /**
     * Elige al jugador con mayor puntuación como el ganador del juego y lo muestra en la consola
     */
    public void declararGanadorJuego() {
        grupoJugadores.setGanadorJuego(grupoJugadores.obtenerJugadorMayorPuntaje());
        System.out.printf("¡El ganador del juego es %s!%n", grupoJugadores.getGanadorJuego());
    }

    /**
     * Determina si la ronda ha sido terminada.
     * Una ronda se ha terminado cuando todas las letras de una frase ya han sido descubiertas.
     * @return Verdadero, si la frase ha sido descubierta completamente; falso, si aún no se conoce toda la frase
     */
    public boolean rondaTerminada() {
        Iterator<Character> iterador = fraseElegida.chars().mapToObj(c -> (char) c).iterator();
        while (iterador.hasNext()) {
            char c = iterador.next();
            if (!letrasDescubiertas.contains(c) && Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Indica si el juego ya ha sido terminado.
     * El juego se termina cuando un jugador alcanza la meta de puntos y se termina la ronda en donde lo logra.
     * @return Verdadero, si un jugador alcanza la meta de puntos; falso, si ningún jugador alcanza la meta de puntos al terminar la ronda.
     */
    public boolean juegoTerminado() {
        int[] puntosJugadores = grupoJugadores.obtenerPuntosJugadores();
        for (int i : puntosJugadores) {
            if (i >= metaPuntos) return true;
        }
        return false;
    }

    /**
     * Le pide al usuario ingresar un número para regresarlo.
     * @param mensaje El mensaje que se le quiere mostrar al usuario para que este conozca para que se utilizará este número.
     * @param minimo El intervalo mínimo del número a ingresar.
     * @param maximo El intervalo máximo del número a ingresar. Si se coloca -1, se elimina este intervalo.
     * @return El número que el usuario ingresó.
     */
    private int introducirEntero(String mensaje, int minimo, int maximo) {
        Scanner entrada = new Scanner(System.in);
        int valor;
        do {
            System.out.print(mensaje);
            try {
                valor = entrada.nextInt();
                if (valor < minimo || (maximo > 0 && valor > maximo)) {
                    if (maximo != -1) {
                        System.out.printf("Error: ingrese un valor entre %d y %d.\n", minimo, maximo);
                    } else {
                        System.out.printf("Error: ingrese un valor mayor a %d.\n", minimo-1);
                    }
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese solo números.");
                entrada.nextLine();
            }
        } while (true);
        return valor;
    }

    /**
     * Pide al usuario que ingrese el nombre de un jugador.
     * @return El nombre que ha ingresado el usuario.
     */
    private String obtenerNombreJugador() {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        do {
            System.out.print("Ingrese el nombre: ");
            nombre = entrada.nextLine().trim();
            if (nombre.isEmpty()) System.out.println("Error: ingrese aunque sea un carácter.");
        } while (nombre.isEmpty());
        return nombre;
    }

    /**
     * Cuenta las veces que aparece una determinada letra en la frase elegida.
     * @param letra La letra que se quiere contar.
     * @return La cantidad de veces que aparece la letra en la frase.
     */
    private int contarRepeticiones(char letra) {
        int contador = 0;
        for (int i = 0; i < fraseElegida.length(); i++) {
            if (fraseElegida.charAt(i) == letra) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Permite saber si se encuentra una letra en la frase elegida.
     * @param letra La letra que se quiere comprobar su existencia en la frase.
     * @return Verdadero, si la letra está en la frase; falso, si no lo está.
     */
    private boolean fraseContieneLetra(char letra) {
        return fraseElegida.indexOf(letra) >= 0;
    }
}