package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PosicionadorCasilleroController implements EventHandler<MouseEvent> {
    private Tablero modelo;
    private JuegoView juego;
    private Posicion posicion;

    public PosicionadorCasilleroController(Tablero tableroModelo, JuegoView juego, Posicion posicion) {
        modelo = tableroModelo;
        this.juego = juego;
        this.posicion = posicion;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        ParserObjeto parser = juego.casilleroInfo(posicion);
        String alianza = (String) parser.get("alianza");

        if (juego.tienePiezaSeleccionada() && alianza.equals("aliado")) {
            try {
                modelo.posicionar(posicion, juego.getPiezaSeleccionada().getPieza());
                juego.agregarPieza();
                juego.colocarPieza(posicion);
                juego.actualizarTablero();
            } catch (FueraDelTableroException e) {
                juego.mostrarError("Error al colocar", "No se puede colocar una pieza fuera del tablero.");
            } catch (PosicionarEnCasilleroEnemigoException e) {
                juego.mostrarError("Error al colocar", "No se puede posicionar en un casillero enemigo.");
            } catch (CantidadDePuntosInsuficientesException e) {
                sacarSeguroPorPuntosInsuficientes();
            }
        }
    }

    private void sacarSeguroPorPuntosInsuficientes() {
        juego.mostrarError("Error al colocar", "No tiene puntos suficientes para comprar la unidad.");
        try {
            modelo.sacar(posicion);
        } catch (FueraDelTableroException e) {
            e.printStackTrace();
        }
    }


}
