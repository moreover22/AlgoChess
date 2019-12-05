package fiuba.algo3.algochess.controller;

import fiuba.algo3.algochess.view.Main;
import fiuba.algo3.algochess.view.Ventana;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class PopUpFinalizar extends PopUpController {
    private Stage stage;
    public PopUpFinalizar(Ventana ventana, Stage stage) {
        super(ventana);
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.handle(actionEvent);
        Main.comenzarJuego(stage);
    }
}
