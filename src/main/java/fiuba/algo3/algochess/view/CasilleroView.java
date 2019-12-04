package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.controller.CasilleroMoverPiezaController;
import fiuba.algo3.algochess.controller.PiezaSeleccionarController;
import fiuba.algo3.algochess.controller.UsarHabilidadController;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.casillero.Casillero;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class CasilleroView extends StackPane implements PropertyChangeListener {
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
        leyendasIndicador.put("mover-casillero-vacio-aliado", "Mover a territorio aliado");
        leyendasIndicador.put("mover-casillero-vacio-enemigo", "Mover a territorio enemigo \nRecibe 5% de daño adicional");
        leyendasIndicador.put("colocar-casillero-aliado-vacio", "Colocar pieza");
        leyendasIndicador.put("colocar-casillero-aliado-ocupado", "No se puede colocar");
    }

    private ImageView iv;

    public CasilleroView(Casillero modelo, int i, int j, int dimension) {
        getStyleClass().add("casillero");
        getStylesheets().add(pathToCssFile);
        this.modelo = modelo;

        modelo.agregarChangeListener(this);
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
        ParserObjeto parserObjeto = modelo.parsear();

        getStyleClass().add("casillero-" + parserObjeto.get("alianza"));
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");

        String estado = (String) parserEstado.get("estado");
        getStyleClass().add("casillero-" + estado);

        resaltarCasillerosAliables();

        String tagTooltip = "colocar-casillero-" + parserObjeto.get("alianza") + "-" + estado;
        String leyendaTooltip = leyendasIndicador.getOrDefault(tagTooltip, "No se puede colocar");
        Tooltip.install(this, new Tooltip(leyendaTooltip));
    }

    public void resaltarCasillerosAliables() {
        ParserObjeto parserObjeto = modelo.parsear();

        if (parserObjeto.get("alianza").equals("aliado")) {
            iv.setEffect(null);
        } else {
            iv.setEffect(casilleroEnemigo);
        }
    }

    public void hacerMoviblePieza(PiezaSeleccionarController controller) {
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        ParserObjeto parserPieza = getPieza().parsear();

        if (parserEstado.get("estado").equals("ocupado") && parserPieza.get("alianza").equals("aliado") ) {
            Tooltip.install(this, new Tooltip("Seleccionar " + InfoPieza.getNombre(parserPieza.get("tipo_pieza").toString())));
            getStyleClass().add("casillero-aliado-ocupado");
            setOnMouseClicked(controller);
        } else {
            setOnMouseClicked(null);
        }
    }

    public void hacerMovibleParaDestinoPieza(CasilleroMoverPiezaController controller) {
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");

        if (parserEstado.get("estado").equals("vacio")) {
            resaltar("mover");
            setOnMouseClicked(controller);
        }
    }

    public Pieza getPieza() {
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");

        return (Pieza) parserEstado.get("pieza");
    }

    public void resaltar(String cssPrefix) {
        ParserObjeto parserObjeto = modelo.parsear();

        getStyleClass().add(cssPrefix + "-casillero-" + parserObjeto.get("alianza"));

        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        String estado = (String) parserEstado.get("estado");
        getStyleClass().add(cssPrefix + "-casillero-" + estado);

        Pieza pieza = (Pieza) parserEstado.get("pieza");
        ParserObjeto infoPieza = pieza.parsear();

        if(infoPieza != null) {
            getStyleClass().add(cssPrefix + "-pieza-" + infoPieza.get("alianza"));
        }

        String tooltipTag = cssPrefix + "-casillero-" + estado + "-" + parserObjeto.get("alianza");
        String tooltipTexto = leyendasIndicador.getOrDefault(tooltipTag, "");

        Tooltip.install(this, new Tooltip(tooltipTexto));
    }

    public void desresaltar() {
        getStyleClass().clear();
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        if (parserEstado.get("estado").equals("vacio")) {
            setOnMouseClicked(null);
        }
    }

    public void hacerHabilidadTargeteable(UsarHabilidadController usarHabilidadController, String tipoHabilidad) {
        ParserObjeto parserObjeto = modelo.parsear();
        ParserObjeto parserEstado = (ParserObjeto) parserObjeto.get("estado");
        Pieza pieza = (Pieza) parserEstado.get("pieza");
        ParserObjeto infoPieza = pieza.parsear();

        if(infoPieza != null && habilidadValida(tipoHabilidad, infoPieza.get("alianza").toString())) {
            setOnMouseClicked(usarHabilidadController);
        }

        resaltar("habilidad-" + tipoHabilidad);
    }

    private boolean habilidadValida(String tipoHabilidad, String alianzaObjetivo) {
        boolean ataqueValido = tipoHabilidad.equals("ataque") && alianzaObjetivo.equals("enemigo");
        boolean curacionValida = tipoHabilidad.equals("curacion") && alianzaObjetivo.equals("aliado");
        return ataqueValido || curacionValida;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        getChildren().remove(piezaView);
        Pieza pieza = (Pieza) propertyChangeEvent.getNewValue();
        ParserObjeto parserPieza = pieza.parsear();
        System.out.println("Entro en CasilleroChange");
        if (parserPieza != null) {
            String tipoPieza = (String) parserPieza.get("tipo_pieza");
            String color = (String) parserPieza.get("color");
            piezaView = new PiezaView(tipoPieza, color);
            pieza.agregarChangeListener(piezaView);
            getChildren().add(piezaView);
        }
    }
}
