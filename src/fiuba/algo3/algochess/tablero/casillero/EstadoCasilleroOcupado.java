package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public class EstadoCasilleroOcupado extends EstadoCasillero {
    public EstadoCasilleroOcupado(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public EstadoCasillero posicionar(Pieza pieza) {
        throw new PosicionarEnCasilleroOcupadoException();
    }

    @Override
    public EstadoCasillero ocupar(Pieza pieza) {
        pieza.deshacerMovimiento();
        return this;
    }

    @Override
    public EstadoCasillero vaciar() {
        return new EstadoCasilleroVacio();
    }
}