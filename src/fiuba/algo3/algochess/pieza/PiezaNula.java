package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.tablero.Tablero;

public class PiezaNula extends Pieza{
    public PiezaNula() {
        super(0, 0);
    }

    @Override
    public int contarEnemigo(int cantidadEnemigos) {
        return cantidadEnemigos;
    }

    @Override
    public int contarAliado(int cantidadAliados) {
        return cantidadAliados;
    }

    @Override
    public void mover(Direccion direccion, Tablero tablero) {

    }

}
