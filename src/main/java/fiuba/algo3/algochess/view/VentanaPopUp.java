package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.PopUpController;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VentanaPopUp extends StackPane {
    public VentanaPopUp(Pane root, String titulo, String mensaje) {
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        PopUpController controller = new PopUpController(root, this);
        VentanaError ventana = new VentanaError(controller, titulo, mensaje);
        getChildren().add(ventana);
        this.setOnMouseClicked(evt -> controller.handle(new ActionEvent()));
    }
}