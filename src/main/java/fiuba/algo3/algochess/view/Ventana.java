package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.PopUpController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Ventana extends BorderPane {
    private static final String pathToCssFile = (VentanaError.class).getResource("/css/PopUp.css").toExternalForm();

    private Button botonAction;
    protected String indicador;
    private String titulo;
    private String mensaje;

    public Ventana(String titulo, String mensaje)  {
        getStylesheets().add(pathToCssFile);
        getStyleClass().add("contenedor");
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public void crear() {

        Text tituloTxt = new Text(indicador + " " + titulo);
        tituloTxt.getStyleClass().add("titulo-ventana");

        Text mensajeTxt = new Text(mensaje);
        TextFlow mensajeTf = new TextFlow(mensajeTxt);
        mensajeTxt.setWrappingWidth(300);
        mensajeTf.getStyleClass().add("contenido-mensaje");
        setTop(tituloTxt);
        setCenter(mensajeTf);
    }

    public void setButtonAction(Button boton, String style) {
        botonAction = boton;

        BorderPane.setAlignment(botonAction, Pos.CENTER_RIGHT);
        botonAction.getStyleClass().add(style);

        setBottom(botonAction);
        Platform.runLater(botonAction::requestFocus);
    }

    public void setClickController(PopUpController controller) {
        botonAction.setOnAction(controller);
    }
}