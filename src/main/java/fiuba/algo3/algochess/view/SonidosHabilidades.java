package fiuba.algo3.algochess.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SonidosHabilidades {
    private static final String pathToSonidoAtaqueSoldado = "sonido_ataque.mp3";
    private static final String pathToSonidoAtaqueJinete = "";
    private static final String pathToSonidoAtaqueCatapulta = "";
    private static final String pathToSonidoAtaqueCurandero = "";

    private static final File archivoSoldado = new File(pathToSonidoAtaqueSoldado);
    private static final File archivoJinete = new File(pathToSonidoAtaqueJinete);
    private static final File archivoCatapulta = new File(pathToSonidoAtaqueCatapulta);
    private static final File archivoCurandero = new File(pathToSonidoAtaqueCurandero);

    private static final Media audioAtaqueSoldado = new Media(archivoSoldado.toURI().toString());
    private static final Media audioAtaqueJinete = new Media(archivoJinete.toURI().toString());
    private static final Media audioAtaqueCatapulta = new Media(archivoCatapulta.toURI().toString());
    private static final Media audioAtaqueCurandero = new Media(archivoCurandero.toURI().toString());
    private static Map<String,Media> sonidos;

    static {
        sonidos = new HashMap<>();

        sonidos.put("soldado", audioAtaqueSoldado);
        sonidos.put("jinete", audioAtaqueJinete);
        sonidos.put("catapulta", audioAtaqueCatapulta);
        sonidos.put("curandero", audioAtaqueCurandero);
    }

    public static MediaPlayer getReproductor(String tipoPieza){
        MediaPlayer reproductor = new MediaPlayer(sonidos.get(tipoPieza));
        return reproductor;
    }
}
