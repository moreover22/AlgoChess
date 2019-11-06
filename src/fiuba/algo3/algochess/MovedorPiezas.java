package fiuba.algo3.algochess;

import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

public class MovedorPiezas {
    private Tablero tablero;

    public MovedorPiezas(Tablero tablero) {
        this.tablero = tablero;
    }

    public void mover(Pieza pieza, Direccion direccion) throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        tablero.vaciarCasillero(pieza.getPosicion());
        pieza.mover(direccion);
        tablero.ocuparCasillero(pieza.getPosicion());
    }
}
