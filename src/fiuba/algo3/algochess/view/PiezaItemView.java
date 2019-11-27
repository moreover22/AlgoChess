package fiuba.algo3.algochess.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class PiezaItemView extends VBox {
    private static Effect seleccionadoEffect = new InnerShadow(15, Color.AQUA);
    private ImageView imagen;
    private Label nombre;

    public PiezaItemView(String color, String tipoPieza, JuegoView juego) {
        setAlignment(Pos.CENTER);
        VBox.setMargin(this, new Insets(10));
        setPadding(new Insets(10));
        setStyle("-fx-background-radius: 3px; -fx-background-color: rgba(0, 0, 0, 0.6); ");

        imagen = new ImageView(ImagenesPieza.getImage(color, tipoPieza));
        imagen.setFitWidth(100);
        imagen.setPreserveRatio(true);
        imagen.setStyle("-fx-padding: 0; -fx-border-color: black; -fx-border-width: 5px;");
        getChildren().add(imagen);

        nombre = new Label(InfoPieza.getNombre(tipoPieza));
        nombre.setStyle("-fx-background-radius: 3px; -fx-padding: 4 12; -fx-text-fill: #EEEEEE; -fx-background-color: #111111;");
        getChildren().add(nombre);


        setCursor(Cursor.HAND);
        Tooltip.install(this, crearTooltip(tipoPieza));

        setOnMouseEntered((evt) -> {
            if (this.getEffect() == null)
                this.setEffect(new InnerShadow(2, Color.ALICEBLUE));
        });
        setOnMouseExited((evt) -> {
            if (this.getEffect() != seleccionadoEffect)
                this.setEffect(null);
        });
        setOnMouseClicked((evt) -> {
            juego.setPiezaSeleccionada(this);
        });
    }

    private Tooltip crearTooltip(String tipoPieza) {
        Tooltip infoTooltip = new Tooltip();
        infoTooltip.setGraphic(InfoPieza.getInfo(tipoPieza));
        infoTooltip.setWrapText(true);
        infoTooltip.setPrefHeight(300);
        infoTooltip.setPrefWidth(300);
        infoTooltip.setShowDelay(Duration.millis(150));
        infoTooltip.setShowDuration(Duration.INDEFINITE);
        return infoTooltip;
    }

    public void seleccionar() {
        imagen.setEffect(seleccionadoEffect);

    }
    public void deseleccionar() {
        imagen.setEffect(null);
    }
}
