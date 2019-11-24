package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

import java.util.List;

public class Batallon implements Movible {
    private List<Pieza> piezas;

    public Batallon(List<Pieza> piezas){
        this.piezas = piezas;
    }

    public Batallon(Pieza pieza , List<Pieza> piezas) {
        piezas.add(pieza);
        this.piezas = piezas;
    }

    @Override
    public void mover(Direccion direccion, Tablero tablero) throws MovimientoFueraDeAlcanceException, FueraDelTableroException {
        for (Pieza pieza : piezas) {
            pieza.mover(direccion, tablero);
        }
    }

    @Override
    public void deshacerMovimiento() {
        for (Pieza pieza : piezas) {
            pieza.deshacerMovimiento();
        }
    }

    public boolean esValido(){
        return (piezas.size() == 3);
    }

    @Override
    public Posicion getPosicion() {
        return piezas.get(0).getPosicion();
    }

}















