package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.jugador.Jugador;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class InformacionTurno extends StackPane {
    private TextFlow infoTurno;

    public InformacionTurno() {
        infoTurno = new TextFlow();

        infoTurno.setStyle("-fx-background-color:  rgba(10, 10, 10, 0.9); -fx-padding: 10; -fx-background-radius: 10px;");
        infoTurno.setTextAlignment(TextAlignment.CENTER);

        infoTurno.setPrefWidth(500);

        infoTurno.setMaxHeight(100);
        infoTurno.setMaxWidth(600);

        getChildren().add(infoTurno);
        setMaxHeight(80);
        setMaxWidth(600);
        setAlignment(Pos.TOP_CENTER);
    }

    public void setMensaje(String mensaje) {
        infoTurno.getChildren().clear();
        Text nombreJugador = new Text(mensaje);
        nombreJugador.setStyle("-fx-fill: #EEE; -fx-font-size: 40px; ");
        infoTurno.getChildren().add(nombreJugador);
    }

    public void actualizarTurno(Jugador jugador) {
        ParserObjeto infoJugador = jugador.parsear();
        String nombre = (String) infoJugador.get("nombre");
        int puntos = (int) infoJugador.get("puntos");

        setMensaje("Turno de " + nombre + " (Puntos: " + puntos + ")");
    }
}
