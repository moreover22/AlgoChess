package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.CasilleroMoverPiezaController;
import fiuba.algo3.algochess.controller.PiezaMoverController;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.casillero.Casillero;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class CasilleroView extends StackPane {
    private static final String pathToCssFile = (CasilleroView.class).getResource("/css/Casillero.css").toExternalForm();
    private static final String pathToImagenCasilleroBlanco = "/images/tiles/tile-white300.png";
    private static final String pathToImagenCasilleroNegro = "/images/tiles/tile-black300.png";

    private static Image imagenCasilleroBlanco = new Image((CasilleroView.class).getResource(pathToImagenCasilleroBlanco).toExternalForm());
    private static Image imagenCasilleroNegro = new Image((CasilleroView.class).getResource(pathToImagenCasilleroNegro).toExternalForm());
    private static ColorAdjust casilleroEnemigo = new ColorAdjust();
    private static Map<String, String> leyendasIndicador;

    private Casillero modelo;
    private PiezaView piezaView;

    static {
        casilleroEnemigo.setBrightness(-0.25);
        leyendasIndicador = new HashMap<>();
        leyendasIndicador.put("mover-casillero-vacio", "Mover a...");
        leyendasIndicador.put("colocar-casillero-aliado-vacio", "Colocar pieza");
        leyendasIndicador.put("colocar-casillero-aliado-ocupado", "No se puede colocar");
    }

    private ImageView iv;

    public CasilleroView(Casillero modelo, int i, int j, int dimension) {
        getStyleClass().add("casillero");
        getStylesheets().add(pathToCssFile);
        this.modelo = modelo;

        Image imagenCasillero;
        if ( (i + j) % 2 == 0 ) {
            imagenCasillero = imagenCasilleroBlanco;
        } else {
            imagenCasillero = imagenCasilleroNegro;
        }
        iv = new ImageView(imagenCasillero);
        iv.setFitHeight(dimension);
        iv.setPreserveRatio(true);
        getChildren().add(iv);
    }

    public void resaltarPosicionable() {
        getStyleClass().clear();
        ParserObjeto parserObjeto = modelo.parsear();

        getStyleClass().add("casillero-" + parserObjeto.get("alianza"));
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");

        String estado = (String) parserEstado.get("estado");
        getStyleClass().add("casillero-" + estado);

        if (parserObjeto.get("alianza").equals("aliado")) {
            iv.setEffect(null);
        } else {
            iv.setEffect(casilleroEnemigo);
        }

        String tagTooltip = "colocar-casillero-" + parserObjeto.get("alianza") + "-" + estado;
        String leyendaTooltip = leyendasIndicador.getOrDefault(tagTooltip, "No se puede colocar");
        Tooltip.install(this, new Tooltip(leyendaTooltip));
    }

    public void actualizarImagen() {
        getChildren().remove(piezaView);
        ParserObjeto parser = (ParserObjeto) modelo.parsear().get("estado");
        if (parser.get("estado").equals("vacio")) return;
        Pieza pieza = (Pieza) parser.get("pieza");
        ParserObjeto parserPieza = pieza.parsear();

        String tipoPieza = (String) parserPieza.get("tipo_pieza");

        String color = (String) parserPieza.get("color");
        piezaView = new PiezaView(tipoPieza, color);
        getChildren().add(piezaView);
    }

    public void hacerMoviblePieza(PiezaMoverController controller) {
        getStyleClass().clear();
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        ParserObjeto parserPieza = getPieza().parsear();
        if (parserEstado.get("estado").equals("ocupado") && parserPieza.get("alianza").equals("aliado") ) {
            Tooltip.install(this, new Tooltip("Seleccionar"));
            getStyleClass().add("casillero-aliado-ocupado");
            setOnMouseClicked(controller);
        } else {
            setOnMouseClicked(null);
        }
    }
    public void hacerMovibleParaDestinoPieza(CasilleroMoverPiezaController controller) {
        getStyleClass().clear();
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        if (parserEstado.get("estado").equals("vacio")) {
            Tooltip.install(this, new Tooltip("Mover a..."));
            resaltar("mover");
            setOnMouseClicked(controller);
        } else {
            setOnMouseClicked(null);
        }
    }

    public Pieza getPieza() {
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");

        return (Pieza) parserEstado.get("pieza");
    }

    public void resaltar(String cssPrefix) {
        desresaltar();
        ParserObjeto parserObjeto = modelo.parsear();
        getStyleClass().add(cssPrefix + "-casillero-" + parserObjeto.get("alianza"));
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        String estado = (String) parserEstado.get("estado");
        getStyleClass().add(cssPrefix + "-casillero-" + estado);
    }

    public void desresaltar() {
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        if (parserEstado.get("estado").equals("vacio")) {
            getStyleClass().clear();
            setOnMouseClicked(null);
        }
    }
}
