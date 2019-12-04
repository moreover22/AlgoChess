package fiuba.algo3.algochess.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ContenedorPiezas extends BorderPane {
    private static final String pathToCssFila = (ContenedorPiezas.class).getResource("/css/PiezaView.css").toExternalForm();

    public ContenedorPiezas(String color, JuegoView juego, EventHandler<ActionEvent> finalizar) {
        getStylesheets().add(pathToCssFila);

        getStyleClass().add("contenedor-de-piezas");
        getStyleClass().add("contenedor-de-piezas-" + color);

        Label seleccionarPieza = new Label("Seleccione una pieza:");
        seleccionarPieza.getStyleClass().add("selecciona-pieza-lbl");
        VBox contenedorPiezas = new VBox();

        contenedorPiezas.getChildren().add(seleccionarPieza);

        contenedorPiezas.getChildren().add(new PiezaItemView(color, "jinete", juego));
        contenedorPiezas.getChildren().add(new PiezaItemView(color, "soldado", juego));
        contenedorPiezas.getChildren().add(new PiezaItemView(color, "catapulta", juego));
        contenedorPiezas.getChildren().add(new PiezaItemView(color, "curandero", juego));
        setCenter(contenedorPiezas);

        Button aceptar = new Button("Cambiar turno");
        aceptar.getStyleClass().add("aceptar-btn");
        aceptar.setOnAction(finalizar);

        setBottom(aceptar);
        BorderPane.setAlignment(aceptar, Pos.CENTER);
        BorderPane.setMargin(aceptar, new Insets(15, 0, 15, 0));
    }
}
