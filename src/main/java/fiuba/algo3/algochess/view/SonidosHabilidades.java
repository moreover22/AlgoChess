package fiuba.algo3.algochess.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SonidosHabilidades {
    private static final String pathToSonidoAtaqueSoldado = "soldado.mp3";
    private static final String pathToSonidoAtaqueJinete = "jinete.mp3";
    private static final String pathToSonidoAtaqueCatapulta = "catapulta.mp3";
    private static final String pathToSonidoAtaqueCurandero = "curandero.mp3";
    private static final String pathToSonidoProyectil = "proyectil.mp3";
    private static final String pathToSonidoFlecha = "flecha.mp3";
    private static final String pathToSonidoEspada = "espadaLiviana.mp3";
    private static final String pathToSonidoTerremoto = "terremoto.mp3";

    private static final File archivoSoldado = new File(pathToSonidoAtaqueSoldado);
    private static final File archivoJinete = new File(pathToSonidoAtaqueJinete);
    private static final File archivoCatapulta = new File(pathToSonidoAtaqueCatapulta);
    private static final File archivoCurandero = new File(pathToSonidoAtaqueCurandero);
    private static final File archivoProyectil = new File(pathToSonidoProyectil);
    private static final File archivoFlecha = new File(pathToSonidoFlecha);
    private static final File archivoEspada = new File(pathToSonidoEspada);
    private static final File archivoTerremoto = new File(pathToSonidoTerremoto);



    private static final Media audioAtaqueSoldado = new Media(archivoSoldado.toURI().toString());
    private static final Media audioAtaqueJinete = new Media(archivoJinete.toURI().toString());
    private static final Media audioAtaqueCatapulta = new Media(archivoCatapulta.toURI().toString());
    private static final Media audioAtaqueCurandero = new Media(archivoCurandero.toURI().toString());
    private static final Media audioGolpeProyectil = new Media(archivoProyectil.toURI().toString());
    private static final Media audioGolpeFlecha = new Media(archivoFlecha.toURI().toString());
    private static final Media audioGolpeEspada = new Media(archivoEspada.toURI().toString());
    private static final Media audioTerremoto = new Media(archivoTerremoto.toURI().toString());



    private static Map<String,Media> sonidos;

    static {
        sonidos = new HashMap<>();

        sonidos.put("soldado", audioAtaqueSoldado);
        sonidos.put("jinete", audioAtaqueJinete);
        sonidos.put("catapulta", audioAtaqueCatapulta);
        sonidos.put("curandero", audioAtaqueCurandero);
        sonidos.put("catapultaproyectil", audioGolpeProyectil);
        sonidos.put("jinetearcoYFlecha", audioGolpeFlecha);
        sonidos.put("jineteespadaLiviana", audioGolpeEspada);
        sonidos.put("soldadoespadaPesada", audioGolpeEspada);
        sonidos.put("terremoto", audioTerremoto);
    }

    public static MediaPlayer getReproductor(String tipoPieza){
        MediaPlayer reproductor = new MediaPlayer(sonidos.get(tipoPieza));
        return reproductor;
    }
}
