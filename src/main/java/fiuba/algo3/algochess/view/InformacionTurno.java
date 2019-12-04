package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.jugador.Jugador;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class InformacionTurno extends StackPane {
    private static String pathToCss = (InformacionTurno.class).getResource("/css/InfoTurno.css").toExternalForm();
    private TextFlow infoTurno;

    public InformacionTurno() {
        getStylesheets().add(pathToCss);
        infoTurno = new TextFlow();
        infoTurno.getStyleClass().add("contenedor");
        infoTurno.setTextAlignment(TextAlignment.CENTER);

        getChildren().add(infoTurno);
        infoTurno.setMaxHeight(10);
        infoTurno.setMaxWidth(500);
        setAlignment(Pos.TOP_CENTER);
        mostrar();
        setOnMouseEntered(evt -> ocultar());
        setOnMouseExited(evt -> {
            if (evt.getY() > 200)
                mostrar();
        });
    }

    private void ocultar() {
        TranslateTransition ocultarContenedor = new TranslateTransition(new Duration(300), this);
        ocultarContenedor.setToY(-this.getHeight());
        ocultarContenedor.play();
    }

    private void mostrar() {
        TranslateTransition ocultarContenedor = new TranslateTransition(new Duration(300), this);
        ocultarContenedor.setToY(15);
        ocultarContenedor.play();
    }

    public void setMensaje(String mensaje) {
        infoTurno.getChildren().clear();
        Text nombreJugador = new Text(mensaje);
        nombreJugador.setStyle("-fx-fill: #EEE; -fx-font-size: 40px; ");
        infoTurno.getChildren().add(nombreJugador);
        mostrar();
    }

    public void actualizarTurnoPuntos(Jugador jugador) {
        ParserObjeto infoJugador = jugador.parsear();
        String nombre = (String) infoJugador.get("nombre");
        int puntos = (int) infoJugador.get("puntos");

        setMensaje("Turno de " + nombre + " (Puntos: " + puntos + ")");
        mostrar();
    }

    public void actualizarTurno(Jugador jugador) {
        ParserObjeto infoJugador = jugador.parsear();
        String nombre = (String) infoJugador.get("nombre");

        setMensaje("Turno de " + nombre);
        mostrar();
    }
}
