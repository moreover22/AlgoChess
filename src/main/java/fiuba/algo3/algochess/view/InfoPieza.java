package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class InfoPieza {
    private static String pathToCss = (InfoPieza.class).getResource("/css/InfoPieza.css").toExternalForm();
    private static Map<String, Supplier<Pieza>> piezas;
    private static Map<String, String> nombresDisplayable;
    private static Map<String, String> descripcionDisplayable;

    static {
        piezas = new HashMap<>();
        nombresDisplayable = new HashMap<>();
        descripcionDisplayable = new HashMap<>();

        piezas.put("soldado", SoldadoDeInfanteria::new);
        piezas.put("catapulta", Catapulta::new);
        piezas.put("curandero", Curandero::new);
        piezas.put("jinete", Jinete::new);

        nombresDisplayable.put("soldado", "Soldado de Infantería");
        nombresDisplayable.put("catapulta", "Catapulta");
        nombresDisplayable.put("curandero", "Curandero");
        nombresDisplayable.put("jinete", "Jinete");

        descripcionDisplayable.put("soldado", "Si hay 3 soldados de infantería juntos los tres se mueven como batallón al mismo tiempo.");
        descripcionDisplayable.put("catapulta", "No se puede mover en toda la partida. Ataca a una distancia lejana. Causa daño en área, puede atacar a aliados.");
        descripcionDisplayable.put("curandero", "Puede curar a una unidad aliada, menos a la catapulta.");
        descripcionDisplayable.put("jinete", "Su ataque depende de las piezas vecinas. Si hay algún soldado de infantería aliado cerca o no hay enemigo cercano, puede atacar con un arco y flecha en una distancia media. Si no hay aliados cercanos y hay enemigos cercanos su arma es una espada y solo puede atacar en distancia cercana.");

    }

    public static Pieza getPieza(String tipoPieza) {
        Supplier<Pieza> supplierPieza = piezas.getOrDefault(tipoPieza, null);
        if (supplierPieza != null)
            return supplierPieza.get();
        return null;
    }

    public static TextFlow getInfo(String tipoPieza) {
        Pieza pieza = piezas.get(tipoPieza).get();
        ParserObjeto parserInformacion = pieza.parsear();
        TextFlow info = new TextFlow();
        info.getStylesheets().add(pathToCss);

        Text nombreText = new Text(nombresDisplayable.get(tipoPieza));
        nombreText.getStyleClass().add("nombre-pieza");
        info.getChildren().add(nombreText);

        Text barra = new Text("\n____________________________________________________\n");
        barra.getStyleClass().add("barra");
        info.getChildren().add(barra);

        float vida = (float) ((ParserObjeto) parserInformacion.get("vida")).get("vida_inicial");
        Text vidaText = new Text("\n \u2665 Vida: " + vida);

        vidaText.getStyleClass().add("texto");
        info.getChildren().add(vidaText);

        int coste = (int) parserInformacion.get("coste");
        Text costeText = new Text("\n $ Coste: " + coste);
        costeText.getStyleClass().add("texto");
        info.getChildren().add(costeText);

        Text habilidadesText = new Text("\n \u26A1 Habilidades: ");
        habilidadesText.getStyleClass().add("texto");
        habilidadesText.getStyleClass().add("titulo");

        info.getChildren().add(habilidadesText);

        Text comportamientoText = new Text("\n \u2022 Comportamiento: ");
        comportamientoText.getStyleClass().add("texto");
        comportamientoText.getStyleClass().add("titulo");

        info.getChildren().add(comportamientoText);

        Text descripcionText = new Text("\n" + descripcionDisplayable.get(tipoPieza));
        descripcionText.getStyleClass().add("texto");
        info.getChildren().add(descripcionText);

        return info;
    }

    public static String getNombre(String tipoPieza) {
        return nombresDisplayable.getOrDefault(tipoPieza, "");
    }

}
