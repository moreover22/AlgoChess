package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

public class TableroBordeInferior extends TableroBordeHorizontal {

    private static String pathToImagenEsquinaInferior = "/images/lados/esquinaInferior100.png";
    private static String pathToImagenLadoInferior = "/images/lados/ladoHorizontal300.png";

    private static Image imagenEsquinaInferior = new Image((TableroBordeLateral.class).getResource(pathToImagenEsquinaInferior).toExternalForm());
    private static Image imagenLadoInferior = new Image((TableroBordeLateral.class).getResource(pathToImagenLadoInferior).toExternalForm());

    public TableroBordeInferior(int cantColumnas, int dimension) {
        super(cantColumnas, dimension);
        setImagenEsquina(imagenEsquinaInferior);
        setImagenHorizontal(imagenLadoInferior);
        setAlineamiento(Pos.TOP_CENTER);
        construir();

    }
}
