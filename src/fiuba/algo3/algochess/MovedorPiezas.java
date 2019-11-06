package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.CasilleroException;
import fiuba.algo3.algochess.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.Pieza;

public class MovedorPiezas {
    private Tablero tablero;

    public MovedorPiezas(Tablero tablero) {
        this.tablero = tablero;
    }

    public void mover(Pieza pieza, Direccion direccion) throws CasilleroException, FueraDelTableroException {
        Posicion posicionActual = pieza.getPosicion();
        Posicion nuevaPosicion = pieza.mover(direccion);
        tablero.vaciarCasillero(posicionActual);
        tablero.ocuparCasillero(nuevaPosicion);
    }
}
