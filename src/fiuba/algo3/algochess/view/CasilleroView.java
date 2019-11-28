package fiuba.algo3.algochess.view;

import fiuba.algo3.algochess.model.ParserObjeto;
import javafx.scene.Cursor;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CasilleroView extends StackPane {
    private static final String pathToCssFile = (CasilleroView.class).getResource("/css/Casillero.css").toExternalForm();
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
        getStyleClass().add("casillero");
        getStylesheets().add(pathToCssFile);
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
        }

        if (parserObjeto.get("alianza").equals("enemigo")) {
            iv.setEffect(casilleroEnemigo);
            iv.setCursor(Cursor.DEFAULT);
        }
    }

    public void colocarPieza(PiezaView piezaView) {
        getChildren().add(piezaView);
    }
}
