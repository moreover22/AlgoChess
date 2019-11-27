package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TableroBordeLateral extends VBox {
    private static String pathToImagenLateral = "/images/lados/ladoLateral300.png";
    private static Image imagenLateral = new Image((TableroBordeLateral.class).getResource(pathToImagenLateral).toExternalForm());
    public TableroBordeLateral(int cantFilas, int dimension, Pos alineamiento) {
        setAlignment(alineamiento);
        for (int i = 0; i < cantFilas; i++) {
            ImageView iv = new ImageView(imagenLateral);
            iv.setPreserveRatio(true);
            iv.setFitHeight(dimension);
            getChildren().add(iv);
        }
    }
}
