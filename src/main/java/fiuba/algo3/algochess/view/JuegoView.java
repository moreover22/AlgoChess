package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.PopUpController;
import fiuba.algo3.algochess.controller.PopUpFinalizar;
import fiuba.algo3.algochess.model.AlgoChess;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.jugador.Jugador;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.tablero.TableroView;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.util.Duration;


public class JuegoView {
    private Stage stage;
    private AlgoChess modelo;
    private TableroView tableroView;
    public CustomBorderPane contenedor;
    private PiezaItemView piezaItemViewSeleccionada;
    private ContenedorPiezas contenedorIzquierda;
    private ContenedorPiezas contenedorDerecha;
    private Pieza piezaSeleccionada;
    private InformacionTurno infoTurno;
    private Tablero tablero;
    private Scene escenaVieja;

    public JuegoView(Stage stage, AlgoChess modelo) {
        this.stage = stage;
        this.modelo = modelo;
        this.contenedor = new CustomBorderPane();
        this.infoTurno = new InformacionTurno();

        tablero = new Tablero(16, 16);
        modelo.agregarAliable(tablero);
        this.tableroView = new TableroView(tablero, this);

        contenedorIzquierda = new ContenedorPiezas("blanco", this, (evt) -> cambiarJugador());
        contenedorDerecha = new ContenedorPiezas("negro", this, (evt) -> empezarAJugar());

        contenedor.setCenter(tableroView);
        contenedor.setTop(infoTurno);
        contenedor.setLeft(contenedorIzquierda);
    }

    public void iniciar(String nombrePrimerJugador, String nombreSegundoJugador) {
        modelo.agregarJugador(nombrePrimerJugador);
        modelo.agregarJugador(nombreSegundoJugador);
        modelo.empezar();

        ParserObjeto infoModelo = modelo.parsear();
        Jugador jugadorActual = (Jugador) infoModelo.get("jugador_actual");
        infoTurno.actualizarTurnoPuntos(jugadorActual);

        stage.hide();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        Scene principal = new Scene(contenedor);
        escenaVieja = stage.getScene();
        stage.setScene(principal);

        stage.show();

        /*
        // FIXME temporal soluciona problema de resolución
        Slider sliderScale = new Slider(0.1, 3, 1);
        sliderScale.setBlockIncrement(0.1);

        sliderScale.valueProperty().addListener((evt) -> {
            System.out.println(sliderScale.getValue());
            Scale scale = new Scale(sliderScale.getValue(), sliderScale.getValue());
            scale.setPivotX(0);
            scale.setPivotY(0);
            principal.getRoot().getTransforms().setAll(scale);
        });
        contenedor.setTopLeft(sliderScale);
         */
    }

    public void cambiarJugador() {
        modelo.cambiarTurno();
        tableroView.actualizarCasillerosPosicionables();

        ParserObjeto infoModelo = modelo.parsear();
        Jugador jugadorActual = (Jugador) infoModelo.get("jugador_actual");
        infoTurno.actualizarTurnoPuntos(jugadorActual);

        ocultarJugadorContenedor(contenedorIzquierda, Direccion.izquierda());
    }

    public void empezarAJugar() {
        ocultarJugadorContenedor(contenedorDerecha, Direccion.derecha());
        modelo.cambiarTurno();
        tableroView.actualizarCasilleros();
        tableroView.hacerPiezasMovibles();
        infoTurno.setMensaje("Empezar a jugar");
    }

    public void cambiarTurno() {
        tablero.aplicarDanioTerritorio();
        modelo.cambiarTurno();
        tableroView.desresaltarCasilleros();
        tableroView.actualizarCasilleros();
        ParserObjeto infoModelo = modelo.parsear();
        Jugador jugadorActual = (Jugador) infoModelo.get("jugador_actual");
        infoTurno.actualizarTurno(jugadorActual);
        tableroView.hacerPiezasMovibles();
        piezaSeleccionada = null;
        Jugador ganador = modelo.ganador();

        if (ganador != null) {
            infoTurno.ocultar();
            finalizarJuego(ganador);
        }
    }

    private void finalizarJuego(Jugador ganador) {
        ParserObjeto parserJugador = ganador.parsear();
        String nombre = (String) parserJugador.get("nombre");
        VentanaFinaliza ventana = new VentanaFinaliza("Juego finalizado", "El ganador es: " + nombre);
        PopUpController controller = new PopUpFinalizar(ventana, stage);
        VentanaPopUp popUpGanador = new VentanaPopUp(contenedor, ventana, controller);
        contenedor.setCenter(popUpGanador);
    }

    private void ocultarJugadorContenedor(ContenedorPiezas contenedor, Direccion direccion) {
        TranslateTransition ocultarContenedor = new TranslateTransition(new Duration(300), contenedor);
        int factor = (direccion == Direccion.izquierda()) ? -1 : 1;
        ocultarContenedor.setToX(factor * contenedor.getWidth());
        ocultarContenedor.setOnFinished(evt -> {
            if (direccion == Direccion.izquierda()) {
                this.contenedor.getChildren().remove(contenedorIzquierda);
                this.contenedor.setRight(contenedorDerecha);
            } else {
                this.contenedor.getChildren().remove(contenedorDerecha);
            }
        });
        ocultarContenedor.play();
    }

    public void setPiezaItemViewSeleccionada(PiezaItemView piezaItemView) {
        if (tienePiezaItemViewSeleccionada())
            piezaItemViewSeleccionada.deseleccionar();
        piezaItemViewSeleccionada = piezaItemView;
        piezaItemViewSeleccionada.seleccionar();
    }

    public Pieza getPiezaSeleccionadaConstruida() {
        return piezaItemViewSeleccionada.getPieza();
    }

    public void setPiezaSeleccionada(Pieza pieza) {
        this.piezaSeleccionada = pieza;
    }

    public Pieza getPiezaSeleccionada() {
        return piezaSeleccionada;
    }

    public boolean tienePiezaItemViewSeleccionada() {
        return piezaItemViewSeleccionada != null;
    }

    public void actualizarTableroPosicionable() {
        tableroView.actualizarCasillerosPosicionables();
    }

    public void agregarPieza(Pieza pieza) throws CantidadDePuntosInsuficientesException {
        modelo.agregarPieza(pieza);
        Jugador jugadorActual = (Jugador) modelo.parsear().get("jugador_actual");
        infoTurno.actualizarTurnoPuntos(jugadorActual);
    }

    public void mostrarError(String titulo, String mensaje) {
        VentanaError ventana = new VentanaError(titulo, mensaje);
        PopUpController controller = new PopUpController(ventana);
        VentanaPopUp popUpError = new VentanaPopUp(contenedor, ventana, controller);
        contenedor.setCenter(popUpError);
    }

    public void actualizarHabilidad(Pieza pieza) {
        pieza.actualizarHabilidad(tablero);
    }
}