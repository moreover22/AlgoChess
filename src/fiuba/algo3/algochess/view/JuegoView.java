package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.AlgoChess;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.tablero.TableroView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JuegoView {
    private Stage stage;
    private AlgoChess modelo;
    private TableroView tableroView;
    private BorderPane contenedor;
    private PiezaItemView piezaSeleccionada;
    public JuegoView(Stage stage, AlgoChess modelo) {
        this.stage = stage;
        this.modelo = modelo;
        this.contenedor = new BorderPane();
        Tablero tablero = new Tablero();
        this.tableroView = new TableroView(tablero, this);
        BorderPane.setAlignment(tableroView, Pos.CENTER);

        contenedor.setLeft(new ContenedorPiezas("blanco", this));
        contenedor.setCenter(tableroView);
    }

    public void iniciar(String nombrePrimerJugador, String nombreSegundoJugador) {
        modelo.agregarJugador(nombrePrimerJugador);
        modelo.agregarJugador(nombreSegundoJugador);
        modelo.empezar();
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.setScene(new Scene(contenedor));
    }

    public void cambiarJugador() {
        contenedor.setLeft(new ContenedorPiezas("negro", this));
    }

    public void setPiezaSeleccionada(PiezaItemView piezaItemView) {
        if (piezaSeleccionada != null)
            piezaSeleccionada.deseleccionar();
        piezaSeleccionada = piezaItemView;
        piezaSeleccionada.seleccionar();
    }
}