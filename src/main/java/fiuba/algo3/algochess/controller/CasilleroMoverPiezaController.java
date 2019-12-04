package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.JuegoView;
import fiuba.algo3.algochess.view.tablero.TableroView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CasilleroMoverPiezaController implements EventHandler<MouseEvent> {
    private Tablero modeloTablero;
    private JuegoView juego;
    private Direccion direccion;
    private TableroView tableroView;
    public CasilleroMoverPiezaController(Tablero tableroModelo, JuegoView juego, Direccion direccion, TableroView tableroView) {
        this.modeloTablero = tableroModelo;
        this.juego = juego;
        this.direccion = direccion;
        this.tableroView = tableroView;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            modeloTablero.mover(juego.getPiezaSeleccionada(), direccion);
            juego.cambiarTurno();
        } catch (FueraDelTableroException e) {
            juego.mostrarError("Movimiento no permitico", "Después de realizar el movimiento, la pieza queda fuera del tablero.");
        } catch (MovimientoFueraDeAlcanceException e) {
            juego.mostrarError("Movimiento no permitico", "La pieza no puede moverse hasta esa distancia.");
        }
    }
}
