package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.ParserObjeto;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CasilleroView extends Pane {
    private static final String pathToImagenCasilleroBlanco = "/images/tiles/White-tile300.png";
    private static final String pathToImagenCasilleroNegro = "/images/tiles/Black-tile300.png";
    private static Image imagenCasilleroBlanco = new Image((CasilleroView.class).getResource(pathToImagenCasilleroBlanco).toExternalForm());
    private static Image imagenCasilleroNegro = new Image((CasilleroView.class).getResource(pathToImagenCasilleroNegro).toExternalForm());
    private static ColorAdjust casilleroEnemigo = new ColorAdjust();

    static {
        casilleroEnemigo.setBrightness(-0.5);
    }

    private ImageView iv;
    public CasilleroView(int i, int j, int dimension) {
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

    public void actualizar(ParserObjeto parserObjeto) {
        if (parserObjeto.get("alianza").equals("aliado")) {
            iv.setEffect(null);
            iv.setCursor(Cursor.HAND);
//            iv.setOnMouseClicked(() ->);
        }
        if (parserObjeto.get("alianza").equals("enemigo")) {
            iv.setEffect(casilleroEnemigo);
            iv.setCursor(Cursor.DEFAULT);
//            iv.setOnMouseEntered((evt) ->
//                iv.setCursor(Cursor.NONE)
//                    );
            //            iv.setB(1.5);
        }

    }
}
