package fiuba.algo3.algochess.view;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class BarraDeVida extends StackPane {
    private static final String pathToCssFile = (BarraDeVida.class).getResource("/css/BarraDeVida.css").toExternalForm();
    private float ancho = 30;
    private float alto = 4;
    private float vidaInicial;
    private float vidaActual;
    private Rectangle vida;

    public BarraDeVida(float vidaInicial, float vidaActual) {
        getStylesheets().add(pathToCssFile);
        this.vidaInicial = vidaInicial;
        this.vidaActual = vidaActual;
        vida = new Rectangle();
        vida.setHeight(alto);
        vida.setWidth(anchoPorcentual());
        getChildren().add(vida);
        StackPane.setAlignment(vida, Pos.BOTTOM_CENTER);
        addCssClasses();
    }

    private float anchoPorcentual() {
        return vidaActual * ancho / vidaInicial;
    }

    public void actualizar(float vidaNueva) {
        this.vidaActual = vidaNueva;
        addCssClasses();
        vida.setWidth(anchoPorcentual());
    }

    private void addCssClasses() {
        float porcentaje = vidaActual * 100 / vidaInicial;
        vida.getStyleClass().clear();
        String cssClass = "";
        if (porcentaje >= 50) {
            cssClass = "barra-vida-saludable";
        } else if (porcentaje >= 25) {
            cssClass = "barra-vida-precaucion";
        } else {
            cssClass = "barra-vida-peligroso";
        }
        System.out.println(cssClass);

        System.out.println(porcentaje);
        vida.getStyleClass().add(cssClass);
    }
}
