package fiuba.algo3.algochess.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
public class CustomBorderPane extends AnchorPane {
    public CustomBorderPane() {
    }

    public void setCenter(Node nodo) {
        getChildren().add(nodo);
        centrar(nodo);
    }

    public void setLeft(Node nodo) {
        getChildren().add(nodo);
        centrarIzquierda(nodo);
    }

    public void setRight(Node nodo) {
        getChildren().add(nodo);
        centrarDerecha(nodo);
    }

    private void centrarHorizontal(Node nodo) {
        AnchorPane.setRightAnchor(nodo, 0.0);
        AnchorPane.setLeftAnchor(nodo, 0.0);
    }
    private void centrarVertical(Node nodo) {
        AnchorPane.setTopAnchor(nodo, 0.0);
        AnchorPane.setBottomAnchor(nodo, 0.0);
    }

    private void centrar(Node nodo) {
        centrarHorizontal(nodo);
        centrarVertical(nodo);
    }

    private void centrarIzquierda(Node nodo) {
        AnchorPane.setLeftAnchor(nodo, 0.0);
        centrarVertical(nodo);
    }

    private void centrarDerecha(Node nodo) {
        AnchorPane.setRightAnchor(nodo, 0.0);
        centrarVertical(nodo);
    }

}
