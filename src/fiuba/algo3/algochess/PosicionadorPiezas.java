package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.pieza.Pieza;

public class PosicionadorPiezas {
    private Tablero tablero;
    public PosicionadorPiezas(Tablero tablero) {
        this.tablero = tablero;
    }

    public void posicionarPieza(Pieza pieza, int x, int y) throws ColocarEnCasilleroEnemigoException, ColocarEnCasilleroOcupadoException {
        if(!tablero.esAliado(x, y)) throw new ColocarEnCasilleroEnemigoException();
        tablero.colocarPieza(pieza, x, y);
    }
}
