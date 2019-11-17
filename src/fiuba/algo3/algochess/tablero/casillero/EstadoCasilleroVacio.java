package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public class EstadoCasilleroVacio implements EstadoCasillero {
    @Override
    public EstadoCasillero ocupar(Pieza pieza) {
        return new EstadoCasilleroOcupado();
    }

    @Override
    public EstadoCasillero vaciar() {
        return this;
    }

    @Override
    public EstadoCasillero posicionar() {
        return new EstadoCasilleroOcupado();
    }
}
