package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.PopUpController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class VentanaError extends BorderPane {
    private static final String pathToCssFile = (VentanaError.class).getResource("/css/PopUp.css").toExternalForm();
    private static String indicador = "\u26A0";

    public VentanaError(PopUpController controller, String titulo, String mensaje)  {
        getStylesheets().add(pathToCssFile);
        getStyleClass().add("contenedor-error");

        Text tituloTxt = new Text(indicador + " " + titulo);
        tituloTxt.getStyleClass().add("titulo-ventana");

        Text mensajeTxt = new Text(mensaje);
        TextFlow mensajeTf = new TextFlow(mensajeTxt);
        mensajeTxt.setWrappingWidth(300);
        mensajeTf.getStyleClass().add("contenido-mensaje");
        setTop(tituloTxt);
        setCenter(mensajeTf);

        Button aceptarError = new Button("Continuar");
        BorderPane.setAlignment(aceptarError, Pos.CENTER_RIGHT);
        aceptarError.setOnAction(controller);
        aceptarError.getStyleClass().add("aceptar-error-btn");

        setBottom(aceptarError);
        Platform.runLater(aceptarError::requestFocus);
    }
}
