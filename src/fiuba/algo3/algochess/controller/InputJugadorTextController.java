package fiuba.algo3.algochess.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputJugadorTextController implements EventHandler<KeyEvent> {
    private TextInputControl unJugador;
    private TextInputControl otroJugador;
    private BotonEmpezarController botonController;
    private boolean primer_verificacion;

    public InputJugadorTextController(TextInputControl unJugador, TextInputControl otroJugador, BotonEmpezarController botonController) {
        this.unJugador = unJugador;
        this.otroJugador = otroJugador;
        this.botonController = botonController;
        primer_verificacion = true;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            botonController.handle(new ActionEvent());
        }

        if (! primer_verificacion) {
            if (botonController.validarInput(unJugador))
                botonController.validarInput(unJugador, otroJugador);
        }
        primer_verificacion = false;
    }
}
