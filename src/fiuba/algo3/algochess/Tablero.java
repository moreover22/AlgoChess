package fiuba.algo3.algochess;


import fiuba.algo3.algochess.casillero.Casillero;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;

import java.util.ArrayList;
import java.util.Collection;


public class Tablero {
    private Collection<Pieza> piezas;
    private Casillero[][] casilleros;
    private int cantFilas;
    private int cantColumnas;
    public Tablero() {
        cantColumnas = 20;
        cantFilas = 20;
        piezas = new ArrayList<>();
        casilleros = new Casillero[cantFilas][cantColumnas];
        inicializarCasilleros();
    }

    public boolean estaVacio() {
        return piezas.isEmpty();
    }

    public void colocarPieza(Pieza pieza, int x, int y) throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        piezas.add(pieza);
        casilleros[x][y].colocar(pieza);
    }

    public Pieza obtenerPieza(int x, int y) {
        return casilleros[x][y].getPieza();
    }

    private void inicializarCasilleros() {
        for(int i = 0; i < getCantFilas(); i++) {
            for(int j = 0; j < getCantColumnas(); j++) {
                casilleros[i][j] = new Casillero();
            }
        }

        for(int i = 0; i < getCantFilas(); i++) {
            for(int j = getCantColumnas() / 2; j < getCantColumnas(); j++) {
                casilleros[i][j].cambiarAlianza();
            }
        }
    }

    public int getCantFilas() {
        return cantFilas;
    }
    public int getCantColumnas() {
        return cantColumnas;
    }
}
