package fiuba.algo3.algochess.view.tablero;

import fiuba.algo3.algochess.controller.CasilleroMoverPiezaController;
import fiuba.algo3.algochess.controller.PiezaSeleccionarController;
import fiuba.algo3.algochess.controller.PosicionadorCasilleroController;
import fiuba.algo3.algochess.controller.UsarHabilidadController;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.alcance.Alcance;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.Casillero;
import fiuba.algo3.algochess.view.CasilleroView;
import fiuba.algo3.algochess.view.JuegoView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class TableroView extends StackPane {
    private int cantFilas;
    private int cantColumnas;
    private Map<Posicion, CasilleroView> casillerosView;
    private Map<?, ?> casilleros;
    private int dimension = 60;
    private Tablero tableroModelo;
    private JuegoView juego;

    public TableroView(Tablero tablero, JuegoView juego) {
        cantFilas = (int) tablero.parsear().get("cantidad_filas");
        cantColumnas = (int) tablero.parsear().get("cantidad_columnas");
        casilleros = (Map<?, ?>) tablero.parsear().get("casilleros");
        casillerosView = new HashMap<>();
        tableroModelo = tablero;
        this.juego = juego;

        getChildren().add(construirTablero());
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
        Posicion posicion = new Posicion(i, j);
        Casillero casilleroModelo = (Casillero) casilleros.get(posicion);

        CasilleroView casilleroView = new CasilleroView(casilleroModelo, i, j, dimension);

        casilleroView.setOnMouseClicked(new PosicionadorCasilleroController(tableroModelo, juego, casilleroModelo, posicion));

        casilleroView.resaltarPosicionable();

        casillerosView.put(posicion, casilleroView);

        return casilleroView;
    }

    public void actualizarCasilleros() {
        casillerosView.forEach((posicion, casilleroView) -> {
            casilleroView.getStyleClass().clear();
            casilleroView.resaltarCasillerosAliables();
        });
    }
    public void actualizarCasillerosPosicionables() {
        casillerosView.forEach((posicion, casilleroView) -> {
               casilleroView.resaltarPosicionable();
        });
    }

    public void hacerPiezasMovibles() {
        casillerosView.forEach((posicion, casilleroView) -> {
            casilleroView.hacerMoviblePieza(new PiezaSeleccionarController(this, casilleroView, juego));
        });
    }

    public void desresaltarCasilleros() {
        casillerosView.forEach((posicion, casilleroView) -> casilleroView.desresaltar());
    }

    public void resaltarParaMovimiento(Alcance alcance, Posicion desde) {
        for(Direccion direccion : Direccion.direcciones()) {
            Posicion hasta = direccion.aplicarA(desde);
            CasilleroView casilleroView = casillerosView.getOrDefault(hasta, null);
            if (casilleroView != null && alcance.llegoA(desde, hasta)) {
                casilleroView.hacerMovibleParaDestinoPieza(new CasilleroMoverPiezaController(tableroModelo, juego, direccion, this));
            }
        }
    }

    public void resaltarParaHabilidad(Alcance alcanceHabilidad, Posicion desde, String tipoHabilidad) {
        casillerosView.forEach((posicion, casilleroView) -> {
            if (alcanceHabilidad.llegoA(desde, posicion)) {
                casilleroView.hacerHabilidadTargeteable(new UsarHabilidadController(tableroModelo, juego, casilleroView), tipoHabilidad);
            }
        });
    }
}