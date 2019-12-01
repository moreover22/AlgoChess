package fiuba.algo3.algochess.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class ContenedorPiezas extends VBox {
    private static final String pathToCssFila = (ContenedorPiezas.class).getResource("/css/PiezaView.css").toExternalForm();
    public ContenedorPiezas(String color, JuegoView juego) {
        getStylesheets().add(pathToCssFila);

        getStyleClass().add("contenedor-de-piezas");
        getStyleClass().add("contenedor-de-piezas-" + color);

        Label seleccionarPieza = new Label("Seleccione una pieza:");
        seleccionarPieza.getStyleClass().add("selecciona-pieza-lbl");

        getChildren().add(seleccionarPieza);
        getChildren().add(new PiezaItemView(color, "jinete", juego));
        getChildren().add(new PiezaItemView(color, "soldado", juego));
        getChildren().add(new PiezaItemView(color, "curandero", juego));
        getChildren().add(new PiezaItemView(color, "catapulta", juego));

    }
}
