package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.AlgoChess;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image(getClass().getResource("/images/icon100.png").toExternalForm()));
        stage.setTitle("AlgoChess");
        stage.setResizable(false);

        AlgoChess algoChess = new AlgoChess();

        JuegoView juego = new JuegoView(stage, algoChess);
//
        Scene bienvenida = new Scene(new ContenedorEntrada(juego));
        stage.setScene(bienvenida);

//        juego.iniciar("asd", "papapa");
        stage.show();
    }
}
