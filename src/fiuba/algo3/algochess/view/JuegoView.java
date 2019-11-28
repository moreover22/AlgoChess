package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.AlgoChess;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.tablero.TableroView;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoView {
    private Stage stage;
    private AlgoChess modelo;
    private TableroView tableroView;
    private BorderPane contenedor;
    private PiezaItemView piezaSeleccionada;
    private ContenedorPiezas contenedorIzquierda;

    public JuegoView(Stage stage, AlgoChess modelo) {
        this.stage = stage;
        this.modelo = modelo;
        this.contenedor = new BorderPane();
        Tablero tablero = new Tablero();
        this.tableroView = new TableroView(tablero, this);

        contenedorIzquierda = new ContenedorPiezas("blanco", this);
//        contenedorIzquierda.setTranslateX(-contenedorIzquierda.getWidth());
        contenedor.setLeft(contenedorIzquierda);
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
        TranslateTransition ocultarIzquierda = new TranslateTransition(new Duration(300), contenedorIzquierda);
        ocultarIzquierda.setToX(-contenedorIzquierda.getWidth());
        ocultarIzquierda.play();
//        contenedor.setLeft(null);

        contenedor.setRight(new ContenedorPiezas("negro", this));
    }

    public void setPiezaSeleccionada(PiezaItemView piezaItemView) {
        if (tienePiezaSeleccionada())
            piezaSeleccionada.deseleccionar();
        piezaSeleccionada = piezaItemView;
        piezaSeleccionada.seleccionar();
    }

    public boolean tienePiezaSeleccionada() {
        return piezaSeleccionada != null;
    }

    public PiezaItemView getPiezaSeleccionada() {
        return piezaSeleccionada;
    }

    public void actualizarTablero() {
        tableroView.actualizarCasilleros();
    }

    public void colocarPieza(Posicion posicion, PiezaItemView piezaSeleccionada) {
        tableroView.colocarPieza(posicion, piezaSeleccionada.getTipoPieza(), piezaSeleccionada.getColor());
    }
}