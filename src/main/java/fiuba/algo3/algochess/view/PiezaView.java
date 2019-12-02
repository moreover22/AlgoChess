package fiuba.algo3.algochess.view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PiezaView extends ImageView {
    public PiezaView(String tipoPieza, String color) {
        super(ImagenesPieza.getImage(color, tipoPieza));
        setFitWidth(50);
        setPreserveRatio(true);
        StackPane.setAlignment(this, Pos.CENTER);
    }
}
