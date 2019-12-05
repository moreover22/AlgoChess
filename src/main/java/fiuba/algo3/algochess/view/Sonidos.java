package fiuba.algo3.algochess.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Sonidos {
    private static final String pathToSonidoAtaqueSoldado = "/sounds/soldado.mp3";
    private static final String pathToSonidoAtaqueJinete = "/sounds/jinete.mp3";
    private static final String pathToSonidoAtaqueCatapulta = "/sounds/catapulta.mp3";
    private static final String pathToSonidoAtaqueCurandero = "/sounds/curandero.mp3";

    private static final String pathToSonidoProyectil = "/sounds/proyectil.mp3";
    private static final String pathToSonidoFlecha = "/sounds/flecha.mp3";
    private static final String pathToSonidoEspada = "/sounds/espadaLiviana.mp3";
    private static final String pathToSonidoTerremoto = "/sounds/terremoto.mp3";

    private static final String pathToSonidoVictoria = "/sounds/victoria.mp3";

    private static final Media audioAtaqueSoldado = new Media((Sonidos.class).getResource(pathToSonidoAtaqueSoldado).toExternalForm());
    private static final Media audioAtaqueJinete = new Media((Sonidos.class).getResource(pathToSonidoAtaqueJinete).toExternalForm());
    private static final Media audioAtaqueCatapulta = new Media((Sonidos.class).getResource(pathToSonidoAtaqueCatapulta).toExternalForm());
    private static final Media audioAtaqueCurandero = new Media((Sonidos.class).getResource(pathToSonidoAtaqueCurandero).toExternalForm());
    private static final Media audioGolpeProyectil = new Media((Sonidos.class).getResource(pathToSonidoProyectil).toExternalForm());
    private static final Media audioGolpeFlecha = new Media((Sonidos.class).getResource(pathToSonidoFlecha).toExternalForm());
    private static final Media audioGolpeEspada = new Media((Sonidos.class).getResource(pathToSonidoEspada).toExternalForm());
    private static final Media audioTerremoto = new Media((Sonidos.class).getResource(pathToSonidoTerremoto).toExternalForm());
    private static final Media audioVictoria = new Media((Sonidos.class).getResource(pathToSonidoVictoria).toExternalForm());

    private static Map<String, Media> sonidos;

    static {
        sonidos = new HashMap<>();

        sonidos.put("soldado", audioAtaqueSoldado);
        sonidos.put("jinete", audioAtaqueJinete);
        sonidos.put("catapulta", audioAtaqueCatapulta);
        sonidos.put("curandero", audioAtaqueCurandero);

        sonidos.put("curacion", audioAtaqueCurandero);
        sonidos.put("ataque-proyectil", audioGolpeProyectil);
        sonidos.put("ataque-arco_y_flecha", audioGolpeFlecha);
        sonidos.put("ataque-espada_liviana", audioGolpeEspada);
        sonidos.put("ataque-espada_pesada", audioGolpeEspada);
        sonidos.put("danio_territorio", audioTerremoto);

        sonidos.put("victoria", audioVictoria);
    }

    public static MediaPlayer getReproductor(String sonidoTag) {
        Media media = sonidos.getOrDefault(sonidoTag, null);
//        if (media != null) {
            return new MediaPlayer(media);
//        }
//        return null;
    }
}
