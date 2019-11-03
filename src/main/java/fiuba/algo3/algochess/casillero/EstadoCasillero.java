package fiuba.algo3.algochess.casillero;

public interface EstadoCasillero {
    EstadoCasillero ocupar() throws ColocarEnCasilleroOcupadoException;
    EstadoCasillero vaciar() throws VaciarCasilleroVacioException;
    boolean estaVacio();
}
