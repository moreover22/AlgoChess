package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.PopUpController;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VentanaPopUp extends StackPane {
    public VentanaPopUp(Pane root, Ventana ventana, PopUpController controller) {
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        getChildren().add(ventana);
        controller.setRoot(root);
        controller.setContenido(this);
        this.setOnMouseClicked(evt -> controller.handle(new ActionEvent()));
    }
}