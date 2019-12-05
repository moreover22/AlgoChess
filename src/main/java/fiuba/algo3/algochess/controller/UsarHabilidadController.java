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
import fiuba.algo3.algochess.view.Sonidos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
        ParserObjeto infoHabilidad = (ParserObjeto) infoPieza.get("habilidad");
        String tipoPieza = (String) infoPieza.get("tipo_pieza");
        String tipoHabilidad = (String) infoHabilidad.get("tipo");
        String sonidoTag = tipoHabilidad;

        if (tipoHabilidad.equals("ataque")) {
            ParserObjeto infoArma = (ParserObjeto) infoHabilidad.get("detalle");
            sonidoTag +=  "-" + infoArma.get("tipo_arma");
        }

        MediaPlayer reproductorHabilidad = Sonidos.getReproductor(sonidoTag);

        MediaPlayer reproductorOrigen = Sonidos.getReproductor(tipoPieza);

        reproductorOrigen.setOnEndOfMedia(() -> {
            safeUsarHabilidad(pieza, objetivo);
            reproductorHabilidad.play();
        });

        reproductorHabilidad.setOnEndOfMedia(() -> {
            juego.cambiarTurno();
            juego.desbloquear();
        });

        if (!tipoHabilidad.equals("ataque")) {
            reproductorOrigen.setStopTime(Duration.millis(0.0));
        }

        reproductorOrigen.play();
        juego.bloquear();
    }

    private void safeUsarHabilidad(Pieza pieza, Pieza objetivo) {
        try {
            pieza.usarHabilidadEn(tableroModelo, objetivo);
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
