package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.AlgoChess;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static String pathToIcon = (Main.class).getResource("/images/icon100.png").toExternalForm();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        comenzarJuego(stage);
    }

    public static void comenzarJuego(Stage stage) {
        stage.getIcons().add(new Image(pathToIcon));
        stage.setTitle("AlgoChess");
        stage.setResizable(false);

        AlgoChess algoChess = new AlgoChess();

        JuegoView juego = new JuegoView(stage, algoChess);

         Scene bienvenida = new Scene(new ContenedorEntrada(juego));
         stage.setScene(bienvenida);

//        juego.iniciar("jugador 1", "jugador 2");
        stage.centerOnScreen();
        stage.show();
    }
}
