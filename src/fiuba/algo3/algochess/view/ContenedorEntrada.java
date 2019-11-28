package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.BotonEmpezarController;
import fiuba.algo3.algochess.controller.InputJugadorTextController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContenedorEntrada extends VBox {
    private static final String pathToCssFile = (ContenedorEntrada.class).getResource("/css/ContenedorEntrada.css").toExternalForm();
    public ContenedorEntrada(JuegoView vistaJuego) {
        getStylesheets().add(pathToCssFile);
        getStyleClass().add("contenedor-principal");

        getChildren().add(logo());

        VBox contenedorJugadores = new VBox();
        contenedorJugadores.setPadding(new Insets(0, 90, 0, 90));
        contenedorJugadores.setSpacing(15);
        TextField unJugador = new TextField();
        TextField otroJugador = new TextField();

        Label errores = new Label();
        errores.setWrapText(false);
        errores.getStyleClass().add("lbl-errores");

        BotonEmpezarController botonController = new BotonEmpezarController(unJugador, otroJugador, errores, vistaJuego);

        VBox primerJugador = new VBox();
        primerJugador.setSpacing(7);

        Label primerJugadorLbl = new Label("Nombre primer jugador");
        primerJugadorLbl.setLabelFor(unJugador);
        unJugador.setPromptText("Jugador 1");
        primerJugador.getChildren().add(primerJugadorLbl);
        primerJugador.getChildren().add(unJugador);
        unJugador.getStyleClass().add("txtfield-jugador");
        unJugador.setId("Nombre primer jugador");
        unJugador.setOnKeyTyped(new InputJugadorTextController(unJugador, otroJugador, botonController));


        VBox segundoJugador = new VBox();
        segundoJugador.setSpacing(7);

        Label segundoJugadorLbl = new Label("Nombre segundo jugador");
        segundoJugadorLbl.setLabelFor(otroJugador);
        otroJugador.setPromptText("Jugador 2");
        segundoJugador.getChildren().add(segundoJugadorLbl);
        segundoJugador.getChildren().add(otroJugador);
        otroJugador.getStyleClass().add("txtfield-jugador");
        otroJugador.setId("Nombre segundo jugador");
        otroJugador.setOnKeyReleased(new InputJugadorTextController(otroJugador, unJugador, botonController));

        contenedorJugadores.getChildren().add(primerJugador);
        contenedorJugadores.getChildren().add(segundoJugador);

        contenedorJugadores.getChildren().add(errores);

        getChildren().add(contenedorJugadores);
        getChildren().add(botonEmpezar(botonController));
    }

    private VBox logo() {
        VBox logoContainer = new VBox();
        logoContainer.setAlignment(Pos.TOP_CENTER);
        Image logo = new Image(getClass().getResource("/images/logo300.png").toExternalForm());
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(250);
        logoView.setFitHeight(250);
        logoContainer.getChildren().add(logoView);

        Label tituloAlgo = new Label("Algo");
        tituloAlgo.getStyleClass().add("titulo");
        tituloAlgo.getStyleClass().add("titulo-algo");

        Label tituloChess = new Label("Chess");
        tituloChess.getStyleClass().add("titulo");
        tituloChess.getStyleClass().add("titulo-chess");

        HBox tituloPane = new HBox(tituloAlgo, tituloChess);
        tituloPane.setAlignment(Pos.CENTER);
        logoContainer.getChildren().add(tituloPane);
        return logoContainer;
    }

    private Button botonEmpezar(BotonEmpezarController botonController) {
        Button empezar = new Button("Empezar");
        empezar.getStyleClass().add("btn-empezar");
        empezar.setOnAction(botonController);
        return empezar;
    }
}
