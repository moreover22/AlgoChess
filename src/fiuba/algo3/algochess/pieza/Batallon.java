package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

import java.util.List;

public class Batallon implements Movible {
    private List<Pieza> movibles;

    public Batallon(List<Pieza> movibles){
        this.movibles = movibles;
    }

    @Override
    public void mover(Direccion direccion, Tablero tablero) throws MovimientoFueraDeAlcanceException, FueraDelTableroException {
        for (Movible movible : movibles) {
            movible.mover(direccion, tablero);
        }
    }

    @Override
    public void deshacerMovimiento() {
        for (Movible movible : movibles) {
            movible.deshacerMovimiento();
        }
    }

    public boolean esValido(){
        return movibles.size() == 3;
    }

    @Override
    public Posicion getPosicion() {
        return movibles.get(0).getPosicion();
    }
}















