import java.util.ArrayList;
import java.util.Random;

public class BancoFrases {
    private final ArrayList<String> frases;

    /**
     * Constructor predeterminado del banco de frases.
     */
    public BancoFrases() {
        frases = new ArrayList<>();
        agregarFrases();
    }

    /**
     * Agrega una serie de 61 frases genéricas de 4 palabras o más.
     */
    private void agregarFrases() {
        frases.add("EL SOL BRILLA FUERTE");
        frases.add("CORRIENDO POR EL PARQUE");
        frases.add("LLEVAME HACIA LA CIMA");
        frases.add("COMO AGUA ENTRE ROCAS");
        frases.add("VIAJAMOS LEJOS SIN MIEDO");
        frases.add("CAEN HOJAS EN OTOÑO");
        frases.add("DIBUJOS EN LA ARENA");
        frases.add("VIAJES CON AMIGOS NUEVOS");
        frases.add("TARDES LARGAS EN CASA");
        frases.add("RECUERDOS QUE NUNCA VUELVEN");
        frases.add("SONRISAS EN EL AIRE");
        frases.add("LUCES BRILLANTES EN LA NOCHE");
        frases.add("CANCIONES DE INVIERNO SUAVE");
        frases.add("CALOR EN EL INVIERNO");
        frases.add("SOMBRAS JUEGAN EN EL PISO");
        frases.add("SALTANDO ENTRE PIEDRAS GRANDES");
        frases.add("HORIZONTE VISTO DESDE LEJOS");
        frases.add("NAVEGANDO ENTRE LAS OLAS");
        frases.add("JUEGOS EN EL PATIO");
        frases.add("UN ATARDECER TRANQUILO Y CLARO");
        frases.add("CURLANGO REPRUEBA ALUMNOS SIN PIEDAD");
        frases.add("ESCUCHANDO VOCES LEJANAS SUAVES");
        frases.add("CIELO AZUL SIN NUBES");
        frases.add("GOTAS DE LLUVIA TIBIA");
        frases.add("AVENTURAS EN EL BOSQUE");
        frases.add("SONIDOS QUE ENTRAN PROFUNDO");
        frases.add("CAMINOS LARGOS POR DESCUBRIR");
        frases.add("AROMAS DE FLORES FRESCAS");
        frases.add("SALTANDO CHARCOS DESPUES DE LLUVER");
        frases.add("EL VIENTO ME LLAMA");
        frases.add("HISTORIAS QUE SE TEJEN");
        frases.add("CRISTALES LUMINOSOS Y CLAROS");
        frases.add("MISTERIOS DE NOCHE OSCURA");
        frases.add("FRUTAS DULCES EN EL VERANO");
        frases.add("PALABRAS SUAVES QUE ALIVIAN");
        frases.add("JUNTOS EN EL CAMINO");
        frases.add("DESCUBRIENDO NUEVOS SABORES");
        frases.add("EL MAR EN CALMA");
        frases.add("SECRETOS GUARDADOS POR SIEMPRE");
        frases.add("UNIVERSOS POR EXPLORAR CERCA");
        frases.add("LUCES QUE PARPADEAN A LO LEJOS");
        frases.add("QUE LA FUERZA TE ACOMPAÑE");
        frases.add("YO SOY TU PADRE");
        frases.add("HAZLO O NO LO HAGAS");
        frases.add("ESTE ES EL CAMINO");
        frases.add("HOUSTON TENEMOS UN PROBLEMA");
        frases.add("VOY A HACERTE UNA OFERTA");
        frases.add("LA VIDA ES COMO UNA CAJA");
        frases.add("HASTA LA VISTA BABY");
        frases.add("QUE EL JUEGO COMIENCE");
        frases.add("NO HAY LUGAR COMO EL HOGAR");
        frases.add("YO PODRIA HABER SIDO ALGUIEN");
        frases.add("ME ENCANTA EL OLOR DE LA MAÑANA");
        frases.add("SIEMPRE NOS QUEDARA PARIS");
        frases.add("ELEMENTAL MI QUERIDO WATSON");
        frases.add("ME ESTAS HABLANDO A MI");
        frases.add("ESTAMOS JUNTOS EN ESTO");
        frases.add("NADIE ES PERFECTO EN ESTE MUNDO");
        frases.add("NECESITAS UN MAYOR BARCO");
        frases.add("NO PUEDES MANEJAR LA VERDAD");
        frases.add("MI NOMBRE ES BOND, JAMES BOND");
    }

    /**
     * Elige una de las 61 frases y la regresa
     * @return La frase elegida aleatoriamente
     */
    public String darFraseAleatoria() {
        Random rng = new Random(System.currentTimeMillis());
        int indiceAleatorio = rng.nextInt(frases.size());
        return frases.get(indiceAleatorio);
    }
}