package fiuba.algo3.algochess.view;

import javafx.scene.control.Button;

public class VentanaFinaliza extends Ventana {

    public VentanaFinaliza(String titulo, String mensaje) {
        super(titulo, mensaje);
        indicador = "\u2691";
        crear();

        Button finalizar = new Button("Salir");
        super.setButtonAction(finalizar, "aceptar-error-btn");
    }
}
