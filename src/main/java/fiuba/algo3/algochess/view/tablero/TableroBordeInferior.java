package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TableroBordeInferior extends HBox {

    private static String pathToImagenEsquinaInferior = "/images/lados/esquinaInferior100.png";
    private static String pathToImagenLadoInferior = "/images/lados/ladoHorizontal300.png";

    private static Image imagenEsquinaInferior = new Image((TableroBordeLateral.class).getResource(pathToImagenEsquinaInferior).toExternalForm());
    private static Image imagenLadoInferior = new Image((TableroBordeLateral.class).getResource(pathToImagenLadoInferior).toExternalForm());

    private int dimension;

    private double getTamanioReal() {
        ImageView iv = new ImageView(imagenLadoInferior);
        iv.setPreserveRatio(true);
        iv.setFitWidth(dimension);
        return iv.getBoundsInParent().getHeight();
    }
    public TableroBordeInferior(int cantColumnas, int dimension) {
        this.dimension = dimension;
        setAlignment(Pos.TOP_CENTER);
        setSpacing(0);
        ImageView ladoEsquinaSuperior = new ImageView(imagenEsquinaInferior);
        ladoEsquinaSuperior.setFitHeight((int) getTamanioReal());
        ladoEsquinaSuperior.setFitWidth((int) getTamanioReal() - 2);
        getChildren().add(ladoEsquinaSuperior);
        for (int i = 0; i < cantColumnas; i++) {
            ImageView iv = new ImageView(imagenLadoInferior);
            iv.setPreserveRatio(true);
            iv.setFitWidth(dimension);
            getChildren().add(iv);
        }
        ladoEsquinaSuperior = new ImageView(imagenEsquinaInferior);
        ladoEsquinaSuperior.setFitHeight((int) getTamanioReal());
        ladoEsquinaSuperior.setFitWidth((int) getTamanioReal() - 2);
        getChildren().add(ladoEsquinaSuperior);

    }
}
