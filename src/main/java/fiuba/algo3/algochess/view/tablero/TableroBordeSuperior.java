package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TableroBordeSuperior extends HBox {

    private static String pathToImagenEsquinaSuperior = "/images/lados/esquinaSuperior100.png";
    private static String pathToImagenLadoSuperior = "/images/lados/ladoHorizontal300.png";

    private static Image imagenEsquinaSuperior = new Image((TableroBordeLateral.class).getResource(pathToImagenEsquinaSuperior).toExternalForm());
    private static Image imagenLadoSuperior = new Image((TableroBordeLateral.class).getResource(pathToImagenLadoSuperior).toExternalForm());

    private int dimension;

    public TableroBordeSuperior(int cantColumnas, int dimension) {
        this.dimension = dimension;
        setAlignment(Pos.BOTTOM_CENTER);
        setSpacing(0);
        ImageView ladoEsquinaSuperior = new ImageView(imagenEsquinaSuperior);
        ladoEsquinaSuperior.setFitHeight((int) getTamanioReal());
        ladoEsquinaSuperior.setFitWidth((int) getTamanioReal() - 2);
        getChildren().add(ladoEsquinaSuperior);
        for (int i = 0; i < cantColumnas; i++) {
            ImageView iv = new ImageView(imagenLadoSuperior);
            iv.setPreserveRatio(true);
            iv.setFitWidth(dimension);
            getChildren().add(iv);
        }
        ladoEsquinaSuperior = new ImageView(imagenEsquinaSuperior);
        ladoEsquinaSuperior.setFitHeight((int) getTamanioReal());
        ladoEsquinaSuperior.setFitWidth((int) getTamanioReal() - 2);
        getChildren().add(ladoEsquinaSuperior);
    }

    private double getTamanioReal() {
        ImageView iv = new ImageView(imagenLadoSuperior);
        iv.setPreserveRatio(true);
        iv.setFitWidth(dimension);
        return iv.getBoundsInParent().getHeight();
    }
}
