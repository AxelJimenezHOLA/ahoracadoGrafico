import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panelInfo;
    private JPanel panelBotonesLetras;
    private JPanel panelConfirmacion;
    private JButton usarLetraButton;
    private JLabel letraSeleccionadaLabel;
    private JLabel letrasUtilizadasLabel;
    private JPanel panelFrase;
    private JLabel fraseLabel;
    private JPanel panelPuntos;
    private JLabel puntajesLabel;
    private JLabel turnoActualLabel;
    private final HashMap<String, JButton> botonesLetras = new HashMap<>();

    private final ReglasAhorcado reglasAhorcado;

    public GUI() {
        // Inicializando algunos parámetros del JFrame
        setTitle("Ahoracado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        // Agrega los botones con las letras y su ActionListener
        agregarBotonesLetras();

        // Crea una instancia de las reglas del juego del ahorcado
        int cantidadJugadores = ingresarCantidadJugadores();
        int metaPuntos = ingresarMetaPuntos();
        reglasAhorcado = new ReglasAhorcado(cantidadJugadores, metaPuntos);

        //La acción que se realiza al presionar el botón de "Usar letra".
        usarLetraButton.addActionListener(this::hacerTurnoJuego);

        reglasAhorcado.inicializarRonda();
        actualizarGUI();
    }

    private void hacerTurnoJuego(ActionEvent e) {
        reglasAhorcado.jugarTurno(obtenerLetraSeleccionada());
        actualizarGUI();
        if (reglasAhorcado.rondaTerminada()) {
            if (reglasAhorcado.metaPuntosAlcanzada()) {
                mostrarMensajesFinalRonda();
                mostrarMensajesFinalJuego();
                actualizarGUI();
                System.exit(0);
            }
            mostrarMensajesFinalRonda();
            reglasAhorcado.inicializarRonda();
            actualizarGUI();
        }
    }

    private void agregarBotonesLetras() {
        panelBotonesLetras.setLayout(new GridLayout(3, 9, 5, 5));

        ActionListener letraBotonListener = e -> {
            JButton boton = (JButton) e.getSource();
            letraSeleccionadaLabel.setText(boton.getText());
            usarLetraButton.setEnabled(true);
        };

        for (char ch = 'A'; ch <= 'N'; ch++) {
            String letra = String.valueOf(ch);
            JButton button = new JButton(letra);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            button.setPreferredSize(new Dimension(45, 45));
            button.addActionListener(letraBotonListener);
            botonesLetras.put(letra, button);
            panelBotonesLetras.add(button);
        }

        JButton nyeButton = new JButton("Ñ");
        nyeButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nyeButton.setPreferredSize(new Dimension(45, 45));
        nyeButton.addActionListener(letraBotonListener);
        botonesLetras.put("Ñ", nyeButton);
        panelBotonesLetras.add(nyeButton);

        for (char ch = 'O'; ch <= 'Z'; ch++) {
            String letra = String.valueOf(ch);
            JButton button = new JButton(letra);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            button.setPreferredSize(new Dimension(45, 45));
            button.addActionListener(letraBotonListener);
            botonesLetras.put(letra, button);
            panelBotonesLetras.add(button);
        }
    }

    private int ingresarCantidadJugadores() {
        int cantidadJugadoresElegida = 0;
        boolean cantidadValida;
        boolean esUnNumero;
        do {
            try {
                cantidadJugadoresElegida = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "Elige la cantidad de jugadores",
                        "Cantidad de jugadores",
                        JOptionPane.QUESTION_MESSAGE
                ));
                esUnNumero = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese un número.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
                esUnNumero = false;
            }
            if ((cantidadJugadoresElegida < 2 || cantidadJugadoresElegida > 4) && esUnNumero) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese una cantidad de jugadores entre 2 y 4.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
                cantidadValida = false;
            } else {
                cantidadValida = true;
            }
        } while (!cantidadValida || !esUnNumero);
        return cantidadJugadoresElegida;
    }

    private int ingresarMetaPuntos() {
        int metaPuntosElegida = 0;
        boolean cantidadValida;
        boolean esUnNumero;
        do {
            try {
                metaPuntosElegida = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "Elige la meta de puntos",
                        "Meta de puntos",
                        JOptionPane.QUESTION_MESSAGE
                ));
                esUnNumero = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese un número.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
                esUnNumero = false;
            }
            if (metaPuntosElegida <= 0 && esUnNumero) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese una meta de puntos positiva.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
                cantidadValida = false;
            } else {
                cantidadValida = true;
            }
        } while (!cantidadValida || !esUnNumero);
        return metaPuntosElegida;
    }

    private char obtenerLetraSeleccionada() {
        return letraSeleccionadaLabel.getText().charAt(0);
    }

    private void mostrarMensajesFinalRonda() {
        String ganadorString = reglasAhorcado.obtenerJugadorGanador().toString();
        String fraseDescubierta = reglasAhorcado.mostrarFraseDescubierta();

        JOptionPane.showMessageDialog(
                null,
                String.format("El ganador de la ronda fue el %s%nLa frase era: %s", ganadorString, fraseDescubierta),
                "Ronda terminada",
                JOptionPane.INFORMATION_MESSAGE
        );

        JOptionPane.showMessageDialog(
                null,
                String.format("El %s ha ganado 5 puntos por ganar la ronda", ganadorString),
                "Puntos para el ganador",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void mostrarMensajesFinalJuego() {
        JOptionPane.showMessageDialog(
                null,
                "¡El juego se acabó!",
                "Juego terminado",
                JOptionPane.INFORMATION_MESSAGE
        );
        JOptionPane.showMessageDialog(
                null,
                reglasAhorcado.mostrarPuntajes("\n"),
                "Tabla de puntajes",
                JOptionPane.PLAIN_MESSAGE
        );
        JOptionPane.showMessageDialog(
                null,
                String.format("El ganador del juego es %s",reglasAhorcado.obtenerJugadorGanador()),
                "Ganador del juego",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void actualizarGUI() {
        letrasUtilizadasLabel.setText(reglasAhorcado.mostrarLetrasDescubiertas());
        fraseLabel.setText(reglasAhorcado.mostrarFraseDescubierta());
        puntajesLabel.setText(reglasAhorcado.mostrarPuntajes("        "));
        turnoActualLabel.setText(String.format("Turno del %s", reglasAhorcado.regresarNombreJugadorActual()));
        usarLetraButton.setEnabled(false);
        letraSeleccionadaLabel.setText(" ");
        pack();
        setLocationRelativeTo(null);
    }
}