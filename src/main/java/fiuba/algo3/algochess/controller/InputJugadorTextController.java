package fiuba.algo3.algochess.controller;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputJugadorTextController implements ChangeListener<String>, EventHandler<KeyEvent> {
    private TextInputControl unJugador;
    private TextInputControl otroJugador;
    private BotonEmpezarController botonController;

    public InputJugadorTextController(TextInputControl unJugador, TextInputControl otroJugador, BotonEmpezarController botonController) {
        this.unJugador = unJugador;
        this.otroJugador = otroJugador;
        this.botonController = botonController;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            botonController.handle(new ActionEvent());
        }
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (unJugador.equals(((StringProperty) observableValue).getBean())) {
                botonController.validarInput(unJugador, otroJugador);
        }
    }
}
