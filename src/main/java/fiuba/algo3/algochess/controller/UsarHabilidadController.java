package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.model.ParserObjeto;
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
        ParserObjeto infoPieza = pieza.parsear();
        System.out.println(infoPieza);
        try {
            pieza.usarHabilidadEn(tableroModelo, objetivo);
            juego.cambiarTurno();
        } catch (HabilidadFueraDeAlcanceException e) {
            e.printStackTrace();
        } catch (HabilidadConObjetivoInvalidoException e) {
            e.printStackTrace();
        } catch (FueraDelTableroException e) {
            e.printStackTrace();
        } catch (CuracionACatapultaException e) {
            e.printStackTrace();
        }
    }
}
