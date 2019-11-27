package fiuba.algo3.algochess.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ContenedorPiezas extends VBox {

    public ContenedorPiezas(String color, JuegoView juego) {

        BorderPane.setMargin(this, new Insets(40, 40, 40, 0));
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 0;");
        Label seleccionarPieza = new Label("Seleccione una pieza:");
        seleccionarPieza.setStyle("-fx-text-fill: #EEE; -fx-font-size: 15px; -fx-padding: 20px 15px 7px 15px;");
        getChildren().add(seleccionarPieza);
        getChildren().add(new PiezaItemView(color, "jinete", juego));
        getChildren().add(new PiezaItemView(color, "soldado", juego));
        getChildren().add(new PiezaItemView(color, "curandero", juego));
        getChildren().add(new PiezaItemView(color, "catapulta", juego));

    }
}
