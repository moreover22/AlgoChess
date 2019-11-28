package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class InfoPieza {
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
        descripcionDisplayable.put("catapulta", "No se puede mover en toda la partida. Ataca a una distancia lejana. Causa daño en área (puede atacar a aliados).");
        descripcionDisplayable.put("curandero", "Puede curar a una unidad aliada, menos a la catapulta");
        descripcionDisplayable.put("jinete", "Su ataque depende de las piezas vecinas. Si hay algún soldado de infantería aliado cerca o no hay enemigo cercano, puede atacar con un arco y flecha en una distancia media. Si no hay aliados cercanos y hay enemigos cercanos su arma es una espada y solo puede atacar en distancia cercana.");

    }

    public static Pieza getPieza(String tipoPieza) {
        Supplier<Pieza> supplielPieza = piezas.getOrDefault(tipoPieza, null);
        if (supplielPieza != null)
            return supplielPieza.get();
        return null;
    }


//        Map<String, Integer> habilidades = new HashMap<>();
//        habilidades.put("\u26A1 Habilidad Alcance cercano", 30);
//        habilidades.put("\u2665 vida Alcance m2edio2", 50);
//        habilidades.put("\u2624 Alcance ce2r4cano", 30);
//        habilidades.put("\u26D1 curación medio3", 50);
//        habilidades.put("\u1F48A curación medio3", 50);
//
//        habilidades.put("\u2615 Alcance cerca3no", 30);
//        habilidades.put("Alcance med2io", 50);
//        habilidades.put("Alcance4 ce4rcano", 30);
//        habilidades.put("Alcance med4io", 50);
//        habilidades.put("Alcan3ce cercanoasdasd", 30);
//        habilidades.put("Alcanc2e medio", 50);
//        habilidades.put("Alcan3ce cercanoqweqwe", 30);
//        habilidades.put("Alcan3c2e meqweqwedio", 50);
//        habilidades.put("Alcan333ce cercaqweqweno", 30);
//        habilidades.put("Alcanceqweqweee medio", 50);
//        habilidades.put("\u2624 Alcan3c2e mediqweo", 50);
//        habilidades.put("Alcan333ce cercqqqano", 30);
//        habilidades.put("Alcance medioasdasqwqwe", 50);
//        habilidades.put("Alcan3c2e mediasdasdo", 50);
//        habilidades.put("Alcan333ce cercanqsdqwdo", 30);
//        habilidades.put("Alcance qwdqwdmedio", 50);
//        habilidades.put("Alcan3c2e meqwdddddddio", 50);
//        habilidades.put("Alcan333ce cercanodddddd", 30);
//        habilidades.put("Alca11111111111111111111 asd asdf  fk  asd a sd  d d dasldkñasdk asjdojd djasidjjdjd  ajsdij das dnce medio", 50);


    public static TextFlow getInfo(String tipoPieza) {
        Pieza pieza = piezas.get(tipoPieza).get();
        ParserObjeto parserInformacion = pieza.parsear();
        TextFlow info = new TextFlow();

        Text nombreText = new Text(nombresDisplayable.get(tipoPieza));
        nombreText.setStyle("-fx-font-size: 25px; -fx-fill: #AAAAAA;");
        info.getChildren().add(nombreText);

        Text barra = new Text("\n____________________________________________________\n");
        barra.setStyle("-fx-fill: #BBBBBB;");
        info.getChildren().add(barra);

        float vida = (float) ((ParserObjeto) parserInformacion.get("vida")).get("vida_inicial");
        Text vidaText = new Text("\n \u2665 Vida: " + vida);
        vidaText.setStyle("-fx-font-size: 15px; -fx-fill: #EEEEEE;");
        info.getChildren().add(vidaText);

        int coste = (int) parserInformacion.get("coste");
        Text costeText = new Text("\n $ Coste: " + coste);
        costeText.setStyle("-fx-font-size: 15px; -fx-fill: #EEEEEE;");
        info.getChildren().add(costeText);

        Text habilidadesText = new Text("\n \u26A1 Habilidades: ");
        habilidadesText.setStyle("-fx-font-size: 15px; -fx-fill: #EEEEEE; -fx-font-weight: bold;");
        info.getChildren().add(habilidadesText);

        Text comportamientoText = new Text("\n \u2022 Comportamiento: ");
        comportamientoText.setStyle("-fx-font-size: 15px; -fx-fill: #EEEEEE; -fx-font-weight: bold;");
        info.getChildren().add(comportamientoText);

        Text descripcionText = new Text("\n" + descripcionDisplayable.get(tipoPieza));
        descripcionText.setStyle("-fx-font-size: 15px; -fx-fill: #EEEEEE;");
        info.getChildren().add(descripcionText);

        return info;
    }

    public static String getNombre(String tipoPieza) {
        return nombresDisplayable.getOrDefault(tipoPieza, "");
    }

}
