package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.Casillero;
import fiuba.algo3.algochess.casillero.CasilleroException;

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

    public boolean estaVacio() {
        for (Casillero[] fila : casilleros)
            for (Casillero casillero : fila)
                if (!casillero.estaVacio()) return false;
        return true;
    }

    public void ocuparCasillero(Posicion posicion) throws CasilleroException, FueraDelTableroException {
        getCasillero(posicion).ocupar();
    }

    public void vaciarCasillero(Posicion posicion) throws CasilleroException, FueraDelTableroException {
        getCasillero(posicion).vaciar();
    }

    public boolean esAliado(Posicion posicion) throws FueraDelTableroException {
        return getCasillero(posicion).esAliado();
    }

    private boolean estaDentro(Posicion posicion) {
        int fila = posicion.getX();
        int columna = posicion.getY();
        boolean dentroDeFila = fila >= 0 && fila < this.getCantFilas();
        boolean dentroDeColumna = columna >= 0 && columna < this.getCantColumnas();
        return dentroDeFila && dentroDeColumna;
    }

    private Casillero getCasillero(Posicion posicion) throws FueraDelTableroException {
        if (!this.estaDentro(posicion)) throw new FueraDelTableroException();
        return casilleros[posicion.getX()][posicion.getY()];
    }

    public int getCantFilas() {
        return cantFilas;
    }

    public int getCantColumnas() {
        return cantColumnas;
    }
}
