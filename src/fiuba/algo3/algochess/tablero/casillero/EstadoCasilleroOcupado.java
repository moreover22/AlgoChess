package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public class EstadoCasilleroOcupado implements EstadoCasillero {
    @Override
    public EstadoCasillero ocupar(Pieza pieza) {
        pieza.deshacerMovimiento();
        return this;
    }

    @Override
    public EstadoCasillero vaciar() {
        return new EstadoCasilleroVacio();
    }

    @Override
    public EstadoCasillero posicionar() {
        throw new PosicionarEnCasilleroOcupadoException();
    }
}