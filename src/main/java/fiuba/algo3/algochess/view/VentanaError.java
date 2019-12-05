package fiuba.algo3.algochess.view;
import javafx.scene.control.Button;

public class VentanaError extends Ventana {
    public VentanaError(String titulo, String mensaje)  {
        super(titulo, mensaje);
        indicador = "\u26A0";
        crear();

        Button aceptarError = new Button("Continuar");
        super.setButtonAction(aceptarError, "aceptar-error-btn");
    }
}
