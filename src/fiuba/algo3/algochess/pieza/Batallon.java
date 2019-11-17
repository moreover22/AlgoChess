package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

public class Batallon implements Movible {
    private Iterable<Pieza> piezas;

    public Batallon(Iterable<Pieza> piezas) {
        this.piezas = piezas;
    }

    public void add(SoldadoDeInfanteria soldado) {
        System.out.println("Soldado");
    }

    public void add(Pieza pieza) {
        System.out.println("Pieza");
    }

    @Override
    public void mover(Tablero tablero, Direccion direccion) throws MovimientoFueraDeAlcanceException, FueraDelTableroException {
        for (Pieza pieza : piezas) {
            pieza.mover(tablero, direccion);
        }
    }

    @Override
    public void deshacerMovimiento() {
        for (Pieza pieza : piezas) {
            pieza.deshacerMovimiento();
        }
    }
}
