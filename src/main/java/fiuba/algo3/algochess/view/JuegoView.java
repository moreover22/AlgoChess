package fiuba.algo3.algochess.view;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

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


    public JuegoView(Stage stage, AlgoChess modelo) {
        this.stage = stage;
        this.modelo = modelo;
        this.contenedor = new CustomBorderPane();
        this.infoTurno = new InformacionTurno();

        Tablero tablero = new Tablero(16, 16);
        modelo.agregarAliable(tablero);
        /*
        // FIXME para testear mas rápido
        Pieza pieza = new SoldadoDeInfanteria();
        pieza.setColor("blanco");
        Pieza pieza2 = new SoldadoDeInfanteria();
        pieza2.cambiarAlianza();
        pieza2.setColor("negro");

        Pieza pieza3 = new Jinete();
        pieza3.cambiarAlianza();
        pieza3.setColor("negro");

        try {
            modelo.agregarJugador("pepe");
            modelo.agregarJugador("pupo");
            modelo.agregarAliable(pieza);
            modelo.agregarAliable(pieza2);
            modelo.agregarAliable(pieza3);
            tablero.posicionar(new Posicion(7, 3), pieza);
            tablero.cambiarAlianza();
            tablero.posicionar(new Posicion(15,0), pieza2);
            tablero.posicionar(new Posicion(8,5), pieza3);
            tablero.cambiarAlianza();
        } catch (FueraDelTableroException e) {
            e.printStackTrace();
        } catch (PosicionarEnCasilleroEnemigoException e) {
            e.printStackTrace();
        }
        // ---------------------------------
        */
        this.tableroView = new TableroView(tablero, this);

        contenedorIzquierda = new ContenedorPiezas("blanco", this, (evt) -> cambiarJugador());
        contenedorDerecha = new ContenedorPiezas("negro", this, (evt) -> empezarAJugar());

        contenedor.setCenter(tableroView);
        contenedor.setTop(infoTurno);
        contenedor.setLeft(contenedorIzquierda);
        /*
        // FIXME para testear mas rápido
        empezarAJugar();
        // ----------------------------------------
        */
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
        stage.setScene(new Scene(contenedor));
        stage.show();
    }

    public void cambiarJugador() {
//        cambiarTurno();
        modelo.cambiarTurno();
        tableroView.actualizarCasilleros();

        ParserObjeto infoModelo = modelo.parsear();
        Jugador jugadorActual = (Jugador) infoModelo.get("jugador_actual");
        infoTurno.actualizarTurnoPuntos(jugadorActual);
        ocultarJugadorContenedor(contenedorIzquierda, Direccion.izquierda());
    }

    public void empezarAJugar() {
        ocultarJugadorContenedor(contenedorDerecha, Direccion.derecha());
        modelo.cambiarTurno();
        tableroView.hacerPiezasMovibles();
        infoTurno.setMensaje("Empezar a jugar");
    }

    public void cambiarTurno() {
        modelo.cambiarTurno();
        tableroView.desresaltarCasilleros();
        tableroView.actualizarCasilleros();
        ParserObjeto infoModelo = modelo.parsear();
        Jugador jugadorActual = (Jugador) infoModelo.get("jugador_actual");
        infoTurno.actualizarTurno(jugadorActual);
        tableroView.hacerPiezasMovibles();
        piezaSeleccionada = null;
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

    public void actualizarTablero() {
        tableroView.actualizarCasilleros();
    }

    public void agregarPieza(Pieza pieza) throws CantidadDePuntosInsuficientesException {
        modelo.agregarPieza(pieza);
        Jugador jugadorActual = (Jugador) modelo.parsear().get("jugador_actual");
        infoTurno.actualizarTurnoPuntos(jugadorActual);
    }

    public void mostrarError(String titulo, String mensaje) {
        VentanaPopUp popUpError = new VentanaPopUp(contenedor, titulo, mensaje);
        contenedor.setCenter(popUpError);
    }
    public void playAudio(String filepath) {

        File archivo = new File(filepath);

        Media audio = new Media(archivo.toURI().toString());

        MediaPlayer reproductor = new MediaPlayer(audio);

        reproductor.play();

    }


}