package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.view.JuegoView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

public class BotonEmpezarController implements EventHandler<ActionEvent> {
    private TextInputControl unJugador;
    private TextInputControl otroJugador;
    private Labeled errores;
    private JuegoView proximaVentana;

    public BotonEmpezarController(TextInputControl unJugador, TextInputControl otroJugador, Labeled errores, JuegoView proximaVentana) {
        this.unJugador = unJugador;
        this.otroJugador = otroJugador;
        this.errores = errores;
        this.proximaVentana = proximaVentana;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        errores.setText("");
        boolean valido = true;
        valido &= validarInput(unJugador);
        if (valido)
            valido &= validarInput(otroJugador);
        if (valido)
            valido = validarInput(unJugador, otroJugador);
        if (valido) {
            proximaVentana.iniciar(unJugador.getText(), otroJugador.getText());
        }
    }

    public boolean validarInput(TextInputControl entrada) {
        entrada.getStyleClass().remove("error-input");
        if (entrada.getText().trim().isEmpty()) {
            entrada.getStyleClass().add("error-input");
            errores.setText("Todos los datos son obligatorios.");
            return false;
        }
        errores.setText("");
        return true;
    }

    public boolean validarInput(TextInputControl entrada, TextInputControl otraEntrada) {
        if (unJugador.getText().equals(otroJugador.getText())) {
            errores.setText("Los nombres deben ser diferentes");
            return false;
        }
        errores.setText("");
        return true;
    }

}
