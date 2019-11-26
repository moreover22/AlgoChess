package fiuba.algo3.algochess.view;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImagenesPieza {
    private static final String pathToImagenCatapultaBlanco = "/images/piezas/catapulta-blanco.png";
    private static final String pathToImagenCatapultaNegro = "/images/piezas/catapulta-negro.png";
    private static final String pathToImagenCuranderoBlanco = "/images/piezas/curandero-blanco.png";
    private static final String pathToImagenCuranderoNegro = "/images/piezas/curandero-negro.png";
    private static final String pathToImagenJineteBlanco = "/images/piezas/jinete-blanco.png";
    private static final String pathToImagenJineteNegro = "/images/piezas/jinete-negro.png";
    private static final String pathToImagenSoldadoBlanco = "/images/piezas/soldado-blanco.png";
    private static final String pathToImagenSoldadoNegro = "/images/piezas/soldado-negro.png";

    private static Image imagenCatapultaBlanco = new Image((ContenedorPiezas.class).getResource(pathToImagenCatapultaBlanco).toExternalForm());
    private static Image imagenCatapultaNegro = new Image((ContenedorPiezas.class).getResource(pathToImagenCatapultaNegro).toExternalForm());
    private static Image imagenCuranderoBlanco = new Image((ContenedorPiezas.class).getResource(pathToImagenCuranderoBlanco).toExternalForm());
    private static Image imagenCuranderoNegro = new Image((ContenedorPiezas.class).getResource(pathToImagenCuranderoNegro).toExternalForm());
    private static Image imagenJineteBlanco = new Image((ContenedorPiezas.class).getResource(pathToImagenJineteBlanco).toExternalForm());
    private static Image imagenJineteNegro = new Image((ContenedorPiezas.class).getResource(pathToImagenJineteNegro).toExternalForm());
    private static Image imagenSoldadoBlanco = new Image((ContenedorPiezas.class).getResource(pathToImagenSoldadoBlanco).toExternalForm());
    private static Image imagenSoldadoNegro = new Image((ContenedorPiezas.class).getResource(pathToImagenSoldadoNegro).toExternalForm());
    
    private static Map<String, Map<String, Image>> imagenes;
    
    static {
        imagenes = new HashMap<>();
        Map<String, Image> imagenesBlanco = new HashMap<>();
        imagenesBlanco.put("soldado", imagenSoldadoBlanco);
        imagenesBlanco.put("catapulta", imagenCatapultaBlanco);
        imagenesBlanco.put("curandero", imagenCuranderoBlanco);
        imagenesBlanco.put("jinete", imagenJineteBlanco);
        
        imagenes.put("blanco", imagenesBlanco);

        Map<String, Image> imagenesNegro = new HashMap<>();
        imagenesNegro.put("soldado", imagenSoldadoNegro);
        imagenesNegro.put("catapulta", imagenCatapultaNegro);
        imagenesNegro.put("curandero", imagenCuranderoNegro);
        imagenesNegro.put("jinete", imagenJineteNegro);

        imagenes.put("negro", imagenesNegro);
    }

    public static Image getImage(String color, String tipoPieza) {
        return imagenes.get(color).get(tipoPieza);
    }
}
