package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.view.Ventana;
import fiuba.algo3.algochess.view.VentanaPopUp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PopUpController implements EventHandler<ActionEvent> {
    private Pane root;
    private Node contenido;

    public PopUpController(Ventana ventana) {
        ventana.setClickController(this);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        root.getChildren().remove(contenido);
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public void setContenido(Pane contenido) {
        this.contenido = contenido;
    }
}
