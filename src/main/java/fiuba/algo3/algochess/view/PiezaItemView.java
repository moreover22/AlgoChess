package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.pieza.Pieza;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class PiezaItemView extends VBox {
    private static final String pathToCssFile = (PiezaItemView.class).getResource("/css/PiezaView.css").toExternalForm();
    private static Effect seleccionadoEffect = new InnerShadow(15, Color.AQUA);

    private ImageView imagen;
    private Label nombre;
    private String tipoPieza;
    private String color;

    public PiezaItemView(String color, String tipoPieza, JuegoView juego) {
        this.tipoPieza = tipoPieza;
        this.color = color;

        getStylesheets().add(pathToCssFile);
        setAlignment(Pos.CENTER);
        getStyleClass().add("contenedor-pieza");

        VBox.setMargin(this, new Insets(10));

        imagen = new ImageView(ImagenesPieza.getImage(color, tipoPieza));
        imagen.setFitWidth(100);
        imagen.setPreserveRatio(true);
        imagen.getStyleClass().add("pieza-imagen");
        getChildren().add(imagen);

        nombre = new Label(InfoPieza.getNombre(tipoPieza));
        nombre.getStyleClass().add("pieza-lbl");
        getChildren().add(nombre);


        Tooltip.install(this, crearTooltip(tipoPieza));
        setOnMouseClicked((evt) -> juego.setPiezaItemViewSeleccionada(this));
    }

    private Tooltip crearTooltip(String tipoPieza) {
        Tooltip infoTooltip = new Tooltip();
        infoTooltip.setGraphic(InfoPieza.getInfo(tipoPieza));
        infoTooltip.setWrapText(true);
        infoTooltip.setPrefHeight(200);
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

    public Pieza getPieza() {
        Pieza pieza = InfoPieza.getPieza(tipoPieza);
        if (pieza != null) pieza.setColor(color);
        return pieza;
    }

    public String getColor() {
        return color;
    }
}

