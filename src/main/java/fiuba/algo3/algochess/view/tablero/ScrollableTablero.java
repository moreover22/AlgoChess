package fiuba.algo3.algochess.view.tablero;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;


public class ScrollableTablero extends StackPane {
    private float deltaScrollY = 0.0f;
    private ScrollPane scrollPane;

    public ScrollableTablero(Pane contenido, float realWidth) {

        scrollPane = new ScrollPane();
        scrollPane.setContent(contenido);

        scrollPane.setFitToWidth(true);
        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);
        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setStyle("-fx-background-color: transparent;");

        getChildren().add(scrollPane);


        Rectangle sombraSuperior = new Rectangle(0, 0, realWidth, 100);
        Rectangle sombraInferior = new Rectangle(0, 0, realWidth, 100);

        getChildren().add(sombraSuperior);
        getChildren().add(sombraInferior);

        sombraSuperior.setStyle("-fx-fill: " +
                "linear-gradient(from 0 30 to 0 80, rgba(0, 0, 0, 0.7), transparent); "
        );
        sombraInferior.setStyle("-fx-fill: " +
                "linear-gradient(from 0 200 to 0 0, rgba(0, 0, 0, 0.6), transparent); "
        );

        sombraInferior.setOnMouseEntered((evt) -> {
            deltaScrollY = 0.01f;
            autoScroll();
            sombraInferior.setStyle("-fx-fill: " +
                    "linear-gradient(from 0 100 to 0 0, rgba(0, 0, 0, 1), transparent); "
            );
        });

        sombraSuperior.setOnMouseEntered((evt) -> {
            deltaScrollY = -0.01f;
            autoScroll();

        });

        sombraInferior.setOnMouseExited((evt) -> {
            deltaScrollY = 0;
            autoScroll();
            sombraInferior.setStyle("-fx-fill: " +
                    "linear-gradient(from 0 200 to 0 0, rgba(0, 0, 0, 0.6), transparent); "
            );
        });

        sombraSuperior.setOnMouseExited((evt) -> {
            deltaScrollY = 0;
            autoScroll();
        });

        StackPane.setAlignment(sombraSuperior, Pos.TOP_CENTER);
        StackPane.setAlignment(sombraInferior, Pos.BOTTOM_CENTER);

    }


    private void autoScroll () {
        Thread thread = new Thread(() -> scroll(100));
        thread.start();
    }

    private void scroll(int maxCount) {
        int count = 0;
        while (scrollPane.getVvalue() <= 1) {
            if (deltaScrollY == 0) break;
            scrollPane.setVvalue(scrollPane.getVvalue() + deltaScrollY);
            if (count >= maxCount) break;
            count++;

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
