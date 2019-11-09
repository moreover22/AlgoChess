package fiuba.algo3.algochess.tablero.casillero;

public class EstadoCasilleroVacio implements EstadoCasillero {
    @Override
    public EstadoCasillero ocupar() {
        return new EstadoCasilleroOcupado();
    }

    @Override
    public EstadoCasillero vaciar() throws VaciarCasilleroVacioException {
        throw new VaciarCasilleroVacioException();
    }
}
