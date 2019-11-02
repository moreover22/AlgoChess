package fiuba.algo3.algochess;


import fiuba.algo3.algochess.casillero.Casillero;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.casillero.VaciarCasilleroVacioException;
import fiuba.algo3.algochess.pieza.Pieza;

public class Tablero {
    private Casillero[][] casilleros;
    private int cantFilas;
    private int cantColumnas;

    public Tablero() {
        this(20, 20);
    }

    public Tablero(int cantFilas, int cantColumnas) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.casilleros = new CreadorTablero(cantFilas, cantColumnas).getCasilleros();
    }

    // TODO Casilleros podría ser un objeto
    public boolean estaVacio() {
        for (Casillero[] fila : casilleros) {
            for (Casillero casillero : fila) {
                if (!casillero.estaVacio()){
                    return false;
                }
            }
        }
        return true;
    }

    public void colocarPieza(Pieza pieza, int x, int y) throws ColocarEnCasilleroOcupadoException {
        casilleros[x][y].colocar(pieza);
    }

    public Pieza obtenerPieza(int x, int y) {
        return casilleros[x][y].getPieza();
    }

    public Pieza sacarPieza(int x, int y) throws VaciarCasilleroVacioException {
        return casilleros[x][y].vaciar();
    }

    public boolean esAliado(int x, int y) {
        return casilleros[x][y].esAliado();
    }

    public int getCantFilas() {
        return cantFilas;
    }

    public int getCantColumnas() {
        return cantColumnas;
    }
}
