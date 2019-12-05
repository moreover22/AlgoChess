package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionACatapultaException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.CasilleroView;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class UsarHabilidadController implements EventHandler<MouseEvent> {
    private Tablero tableroModelo;
    private JuegoView juego;
    private CasilleroView casilleroView;

    public UsarHabilidadController(Tablero tableroModelo, JuegoView juego, CasilleroView casilleroView) {
        this.tableroModelo = tableroModelo;
        this.casilleroView = casilleroView;
        this.juego = juego;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Pieza pieza = juego.getPiezaSeleccionada();
        Pieza objetivo = casilleroView.getPieza();
        try {
            pieza.usarHabilidadEn(tableroModelo, objetivo);
            juego.cambiarTurno();
        } catch (HabilidadFueraDeAlcanceException e) {
            juego.mostrarError("Habilidad no permitido", "La pieza objetivo no se encuentra en el alcance de la habilidad.");
        } catch (HabilidadConObjetivoInvalidoException e) {
            juego.mostrarError("Habilidad no permitido", "La pieza objetivo no puede recibir esa habilidad.");
        } catch (FueraDelTableroException e) {
            juego.mostrarError("Habilidad no permitido", "Habilidad queda fuera del tablero.");
        } catch (CuracionACatapultaException e) {
            juego.mostrarError("Habilidad no permitido", "Las catapultas no se pueden curar.");
        }
    }
}
