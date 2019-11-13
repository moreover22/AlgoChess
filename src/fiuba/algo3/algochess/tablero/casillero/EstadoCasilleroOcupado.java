package fiuba.algo3.algochess.tablero.casillero;

public class EstadoCasilleroOcupado implements EstadoCasillero {
    @Override
    public EstadoCasillero ocupar() throws ColocarEnCasilleroOcupadoException {
        throw new ColocarEnCasilleroOcupadoException();
    }

    @Override
    public EstadoCasillero vaciar() {
        return new EstadoCasilleroVacio();
    }
}