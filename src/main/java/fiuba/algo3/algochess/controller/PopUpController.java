package fiuba.algo3.algochess.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PopUpController implements EventHandler<ActionEvent> {
    private Pane root;
    private Node contenido;
    public PopUpController(Pane root, Node contenido) {
        this.root = root;
        this.contenido = contenido;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        root.getChildren().remove(contenido);
    }
}
