package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;

public interface Movible {
    void mover(Direccion direccion) throws MovimientoFueraDeAlcanceException;
    void deshacerMovimiento();
    Posicion getPosicion();
}
