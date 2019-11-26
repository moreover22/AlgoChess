package fiuba.algo3.algochess.view.tablero;

import fiuba.algo3.algochess.controller.CasilleroController;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.CasilleroView;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.util.HashMap;
import java.util.Map;

public class TableroView extends StackPane {
    private int cantFilas;
    private int cantColumnas;
    private Map<Posicion, CasilleroView> casillerosView;
    private Map<Posicion, ParserObjeto> casillerosInfo;
    private int dimension = 70;
    private Tablero tableroModelo;
    private JuegoView juego;


    public TableroView(Tablero tablero, JuegoView juego) {
        cantFilas = (int) tablero.getEstado().get("cantidad_filas");
        cantColumnas = (int) tablero.getEstado().get("cantidad_columnas");
        casillerosView = new HashMap<>();
        casillerosInfo = new HashMap<>();
        tableroModelo = tablero;
        this.juego = juego;
        ScrollPane sp = new ScrollPane();
        sp.setContent(construirTablero());

        sp.setFitToWidth(true);
        sp.setHvalue(0.5);
        sp.setVvalue(0.5);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setStyle("-fx-background-color: transparent;");

        getChildren().add(sp);
        BorderPane.setAlignment(this, Pos.CENTER);

        actualizarCasilleros();
        Button aceptar = new Button("Aceptar");
        StackPane.setAlignment(aceptar, Pos.BOTTOM_CENTER);
        StackPane.setMargin(aceptar, new Insets(100));
        aceptar.setStyle("-");
        aceptar.setOnAction((evt) -> {
            cambiarAlianza();
            juego.cambiarJugador();
        });

        getChildren().add(aceptar);
    }

    private VBox construirTablero() {
        VBox tableroContenedor = new VBox();
        tableroContenedor.setPadding(new Insets(100, 0, 100, 0));
        tableroContenedor.setAlignment(Pos.CENTER);
        tableroContenedor.getChildren().add(new TableroBordeSuperior(cantColumnas, dimension));
        HBox contenedorCentro = new HBox();
        contenedorCentro.setAlignment(Pos.CENTER);
        contenedorCentro.getChildren().add(new TableroBordeLateral(cantFilas, dimension, Pos.CENTER_RIGHT));
        contenedorCentro.getChildren().add(iniciarPanelCuadricula());
        contenedorCentro.getChildren().add(new TableroBordeLateral(cantFilas, dimension, Pos.CENTER_LEFT));
        tableroContenedor.getChildren().add(contenedorCentro);
        tableroContenedor.getChildren().add(new TableroBordeInferior(cantColumnas, dimension));
        return tableroContenedor;
    }

    private GridPane iniciarPanelCuadricula() {
        GridPane casilleros = new GridPane();
        for (int i = 0; i < cantColumnas; i++) {
            for (int j = 0; j < cantFilas; j++) {
                casilleros.add(iniciarCasilleroView(i, j), i, j);
            }
        }
        casilleros.setAlignment(Pos.CENTER);
        return casilleros;
    }

    private CasilleroView iniciarCasilleroView(int i, int j) {
        CasilleroView casilleroView = new CasilleroView(i, j, dimension);
        casilleroView.setOnMouseClicked(new CasilleroController(tableroModelo, juego));
        casillerosView.put(new Posicion(i, j), casilleroView);
        return casilleroView;
    }

    private void actualizarCasilleros() {
        actualizarCasillerosInfo();
        casillerosView.forEach((posicion, casillerosView) -> {
            casillerosView.actualizar(casillerosInfo.get(posicion));
        });
    }

    private void actualizarCasillerosInfo() {
        Map<?, ?> infoCasilleros = (Map<?,?>) tableroModelo.getEstado().get("casilleros");
        System.out.println(tableroModelo.getEstado().get("casilleros"));
        infoCasilleros.forEach((posicion, infoCasillero) -> {
            String posicionStr = posicion.toString();
            ParserObjeto infoCasilleroParseado = (ParserObjeto) infoCasillero;
            casillerosInfo.put(new Posicion(posicionStr), infoCasilleroParseado);
        });
    }

    private void cambiarAlianza() {
        tableroModelo.cambiarAlianza();
        actualizarCasilleros();
    }
}
