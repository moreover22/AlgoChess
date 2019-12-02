package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

public class TableroBordeSuperior extends TableroBordeHorizontal {

    private static String pathToImagenEsquinaSuperior = "/images/lados/esquinaSuperior100.png";
    private static String pathToImagenLadoSuperior = "/images/lados/ladoHorizontal300.png";

    private static Image imagenEsquinaSuperior = new Image((TableroBordeLateral.class).getResource(pathToImagenEsquinaSuperior).toExternalForm());
    private static Image imagenLadoSuperior = new Image((TableroBordeLateral.class).getResource(pathToImagenLadoSuperior).toExternalForm());

    public TableroBordeSuperior(int cantColumnas, int dimension) {
        super(cantColumnas, dimension);
        setImagenEsquina(imagenEsquinaSuperior);
        setImagenHorizontal(imagenLadoSuperior);
        setAlineamiento(Pos.BOTTOM_CENTER);
        construir();
    }
}
