package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

public interface Movible {
    void mover(Direccion direccion, Tablero tablero) throws MovimientoFueraDeAlcanceException, FueraDelTableroException;
    void deshacerMovimiento();
    Posicion getPosicion();
}
