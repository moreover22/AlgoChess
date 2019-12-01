package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CasilleroController implements EventHandler<MouseEvent> {
    private Tablero modelo;
    private JuegoView juego;
    private Posicion posicion;

    public CasilleroController(Tablero tableroModelo, JuegoView juego, Posicion posicion) {
        modelo = tableroModelo;
        this.juego = juego;
        this.posicion = posicion;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        if (juego.tienePiezaSeleccionada()) {
            try {
                modelo.posicionar(posicion, juego.getPiezaSeleccionada().getPieza());
                juego.agregarPieza();
                juego.colocarPieza(posicion, juego.getPiezaSeleccionada());
                juego.actualizarTablero();
            } catch (FueraDelTableroException e) {
                e.printStackTrace();
            } catch (PosicionarEnCasilleroEnemigoException e) {
                e.printStackTrace();
            } catch (CantidadDePuntosInsuficientesException e) {
                e.printStackTrace();
            }
        }

    }
}
