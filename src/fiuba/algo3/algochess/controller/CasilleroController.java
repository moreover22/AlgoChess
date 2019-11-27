package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.awt.event.MouseEvent;

public class CasilleroController implements EventHandler<Event> {
    private Tablero modelo;
    private JuegoView juego;

    public CasilleroController(Tablero tableroModelo, JuegoView juego) {
        modelo = tableroModelo;
        this.juego = juego;
    }

    @Override
    public void handle(Event event) {
//        if (juego.tienePiezaSeleccionada())

    }
}
