package fiuba.algo3.algochess.view.tablero;

import fiuba.algo3.algochess.controller.PosicionadorCasilleroController;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.view.CasilleroView;
import fiuba.algo3.algochess.view.InformacionTurno;
import fiuba.algo3.algochess.view.JuegoView;
import fiuba.algo3.algochess.view.PiezaView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class TableroView extends StackPane {
    private static final String pathToCssFile = (TableroView.class).getResource("/css/Tablero.css").toExternalForm();
    private int cantFilas;
    private int cantColumnas;
    private Map<Posicion, CasilleroView> casillerosView;
    private Map<Posicion, ParserObjeto> casillerosInfo;
    private int dimension = 60;
    private Tablero tableroModelo;
    private JuegoView juego;

    public TableroView(Tablero tablero, JuegoView juego, InformacionTurno infoTurno, Button aceptar) {
        getStylesheets().add(pathToCssFile);
        cantFilas = (int) tablero.parsear().get("cantidad_filas");
        cantColumnas = (int) tablero.parsear().get("cantidad_columnas");
        casillerosView = new HashMap<>();
        casillerosInfo = new HashMap<>();
        tableroModelo = tablero;
        this.juego = juego;

        BorderPane.setAlignment(this, Pos.CENTER);

        getChildren().add(construirTablero());
        actualizarCasilleros();

        getChildren().add(infoTurno);

        StackPane.setMargin(infoTurno, new Insets(30));
        StackPane.setAlignment(infoTurno, Pos.TOP_CENTER);

        getChildren().add(aceptar);
    }


    private VBox construirTablero() {
        VBox tableroContenedor = new VBox();

        tableroContenedor.setPadding(new Insets(150, 0, 170, 0));
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
        Posicion posicion = new Posicion(i, j);

        casilleroView.setOnMouseClicked(new PosicionadorCasilleroController(tableroModelo, juego, posicion));

        casilleroView.setOnMouseEntered((evt) -> {
            ParserObjeto parser = tableroModelo.parsear();
            Map<?, ?> parserCasilleros = (Map<?, ?>) parser.get("casilleros");
            ParserObjeto infoCasillero = (ParserObjeto) parserCasilleros.get(posicion);
            ParserObjeto infoEstadoCasillero = (ParserObjeto) infoCasillero.get("estado");

            if (infoCasillero.get("alianza").equals("aliado") && infoEstadoCasillero.get("estado").equals("vacio") && juego.tienePiezaSeleccionada()) {
                Tooltip.install(casilleroView, new Tooltip("Colocar pieza"));
            } else {
                Tooltip.install(casilleroView, new Tooltip("No se puede colocar"));
                casilleroView.setCursor(Cursor.DEFAULT);
            }
        });

        casillerosView.put(posicion, casilleroView);
        return casilleroView;
    }

    public void actualizarCasilleros() {
        actualizarCasillerosInfo();
        casillerosView.forEach((posicion, casillerosView) -> {
            casillerosView.actualizar(casillerosInfo.get(posicion));
        });
    }

    private void actualizarCasillerosInfo() {
        Map<?, ?> infoCasilleros = (Map<?,?>) tableroModelo.parsear().get("casilleros");
        infoCasilleros.forEach((posicion, infoCasillero) -> {
            String posicionStr = posicion.toString();
            ParserObjeto infoCasilleroParseado = (ParserObjeto) infoCasillero;
            casillerosInfo.put(new Posicion(posicionStr), infoCasilleroParseado);
        });
    }

    public ParserObjeto casilleroInfo(Posicion posicion) {
        return casillerosInfo.get(posicion);
    }

    public void colocarPieza(Posicion posicion, String tipoPieza, String color) {
        PiezaView piezaView = new PiezaView(tipoPieza, color);
        casillerosView.get(posicion).colocarPieza(piezaView);
    }
}