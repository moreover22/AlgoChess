package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

public class EstadoCasilleroOcupado extends EstadoCasillero {
    public EstadoCasilleroOcupado(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public EstadoCasillero posicionar(Pieza pieza) {
        throw new PosicionarEnCasilleroOcupadoException();
    }

    @Override
    public EstadoCasillero ocupar(Pieza pieza, Tablero tablero) throws FueraDelTableroException {
        pieza.deshacerMovimiento();
        tablero.ocupar(pieza.getPosicion(), pieza);
        return this;
    }
}