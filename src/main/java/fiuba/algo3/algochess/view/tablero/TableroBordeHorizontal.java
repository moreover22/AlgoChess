package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public abstract class TableroBordeHorizontal extends HBox {
    private final int cantidad;
    private Image imagenHorizontal;
    private Image imagenEsquina;
    private int dimension;
    private Pos alineamiento;

    protected TableroBordeHorizontal(int cantidad, int dimension) {
        this.dimension = dimension;
        this.cantidad = cantidad;
    }

    protected void setAlineamiento(Pos alineamiento) {
        this.alineamiento = alineamiento;
    }

    protected void setImagenHorizontal(Image imagenHorizontal) {
        this.imagenHorizontal = imagenHorizontal;
    }

    protected void setImagenEsquina(Image imagenEsquina) {
        this.imagenEsquina = imagenEsquina;
    }

    protected void construir() {
        setAlignment(alineamiento);
        setSpacing(0);

        ImageView ladoEsquina = new ImageView(imagenEsquina);
        ladoEsquina.setFitHeight((int) getTamanioReal() + 1);
        ladoEsquina.setFitWidth((int) getTamanioReal() - 1);
        getChildren().add(ladoEsquina);

        for (int i = 0; i < cantidad; i++) {
            ImageView iv = new ImageView(imagenHorizontal);
            iv.setPreserveRatio(true);
            iv.setFitWidth(dimension);
            getChildren().add(iv);
        }

        ladoEsquina = new ImageView(imagenEsquina);
        ladoEsquina.setFitHeight((int) getTamanioReal() + 1);
        ladoEsquina.setFitWidth((int) getTamanioReal() - 1);
        getChildren().add(ladoEsquina);
    }

    private double getTamanioReal() {
        ImageView iv = new ImageView(imagenHorizontal);
        iv.setPreserveRatio(true);
        iv.setFitWidth(dimension);
        return iv.getBoundsInParent().getHeight();
    }
}
