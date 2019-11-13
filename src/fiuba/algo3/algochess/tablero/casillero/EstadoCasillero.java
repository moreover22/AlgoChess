package fiuba.algo3.algochess.tablero.casillero;

public interface EstadoCasillero {
    EstadoCasillero ocupar() throws ColocarEnCasilleroOcupadoException;
    EstadoCasillero vaciar() throws VaciarCasilleroVacioException;
}
