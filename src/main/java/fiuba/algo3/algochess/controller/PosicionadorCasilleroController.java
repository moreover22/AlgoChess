package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.Casillero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PosicionadorCasilleroController implements EventHandler<MouseEvent> {
    private Tablero modelo;
    private JuegoView juego;
    private Casillero casillero;
    private Posicion posicion;

    public PosicionadorCasilleroController(Tablero tableroModelo, JuegoView juego, Casillero casilleroModelo, Posicion posicion) {
        modelo = tableroModelo;
        this.juego = juego;
        this.casillero = casilleroModelo;
        this.posicion = posicion;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        ParserObjeto parser = casillero.parsear();
        String alianza = (String) parser.get("alianza");
        ParserObjeto parserEstado = (ParserObjeto) parser.get("estado");
        String estado = (String) parserEstado.get("estado");

        if (juego.tienePiezaItemViewSeleccionada() && alianza.equals("aliado") && estado.equals("vacio")) {
            try {
                Pieza pieza = juego.getPiezaSeleccionadaConstruida();
                juego.agregarPieza(pieza);
                modelo.posicionar(posicion, pieza);
                juego.actualizarTableroPosicionable();
            } catch (FueraDelTableroException e) {
                juego.mostrarError("Error al colocar", "No se puede colocar una pieza fuera del tablero.");
            } catch (PosicionarEnCasilleroEnemigoException e) {
                juego.mostrarError("Error al colocar", "No se puede posicionar en un casillero enemigo.");
            } catch (PosicionarEnCasilleroOcupadoException e) {
                juego.mostrarError("Error al colocar", "No se puede posicionar en un casillero ocupado.");
            } catch (CantidadDePuntosInsuficientesException e) {
                sacarSeguroPorPuntosInsuficientes();
            }
        }
    }

    private void sacarSeguroPorPuntosInsuficientes() {
        juego.mostrarError("Error al colocar", "No tiene puntos suficientes para comprar la unidad.");
        try {
            modelo.vaciar(posicion);
        } catch (FueraDelTableroException e) {
            e.printStackTrace();
        }
    }
}
