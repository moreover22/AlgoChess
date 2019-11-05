package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.Casillero;
import fiuba.algo3.algochess.casillero.CasilleroException;
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
        for (Casillero[] fila : casilleros)
            for (Casillero casillero : fila)
                if (!casillero.estaVacio()) return false;
        return true;
    }

    public void colocarPieza(Pieza pieza, int fila, int columna) throws CasilleroException, FueraDelTableroException {
        getCasillero(fila, columna).colocar(pieza);
    }

    public Pieza obtenerPieza(int fila, int columna) throws FueraDelTableroException {
        return getCasillero(fila, columna).getPieza();
    }

    public Pieza sacarPieza(int fila, int columna) throws CasilleroException, FueraDelTableroException {
        return getCasillero(fila, columna).vaciar();
    }

    public boolean esAliado(int fila, int columna) throws FueraDelTableroException {
        return getCasillero(fila, columna).esAliado();
    }

    private boolean estaDentro(int fila, int columna) {
        boolean dentroDeFila = fila >= 0 && fila < this.getCantFilas();
        boolean dentroDeColumna = columna >= 0 && columna < this.getCantColumnas();
        return dentroDeFila && dentroDeColumna;
    }

    private Casillero getCasillero(int columna, int fila) throws FueraDelTableroException {
        if (!this.estaDentro(columna, fila)) throw new FueraDelTableroException();
        return casilleros[columna][fila];
    }

    public int getCantFilas() {
        return cantFilas;
    }

    public int getCantColumnas() {
        return cantColumnas;
    }
}
