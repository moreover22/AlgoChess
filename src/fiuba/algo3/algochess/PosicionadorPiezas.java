package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.CasilleroException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.pieza.Pieza;

import java.util.HashMap;
import java.util.Map;

public class PosicionadorPiezas {
    private Tablero tablero;
    private Map<Posicion, Pieza> piezas;
    public PosicionadorPiezas(Tablero tablero) {
        this.tablero = tablero;
        piezas = new HashMap<>();
    }

    public void posicionarPieza(Pieza pieza, Posicion posicion) throws CasilleroException, FueraDelTableroException {
        if(!tablero.esAliado(posicion)) throw new ColocarEnCasilleroEnemigoException();
        tablero.ocuparCasillero(posicion);
        pieza.setPosicion(posicion);
        piezas.put(posicion, pieza);
    }
    public void sacarPieza(Posicion posicion) throws CasilleroException, FueraDelTableroException {
        tablero.vaciarCasillero(posicion);
        Pieza piezaARemover = piezas.remove(posicion);
        piezaARemover.setPosicion(null);
    }
}
