package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.Casillero;

public class CreadorTablero {
    private Casillero[][] casilleros;
    private int cantFilas;
    private int cantColumnas;

    public CreadorTablero(int cantFilas, int cantColumnas) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        casilleros = new Casillero[cantFilas][cantColumnas];
        inicializarCasilleros();
    }

    public Casillero[][] getCasilleros() {
        return casilleros;
    }

    private void inicializarCasilleros() {
        for(int i = 0; i < cantFilas; i++) {
            for(int j = 0; j < cantColumnas; j++) {
                casilleros[i][j] = new Casillero();
            }
        }

        for(int i = 0; i < cantFilas; i++) {
            for(int j = cantColumnas / 2; j < cantColumnas; j++) {
                casilleros[i][j].cambiarAlianza();
            }
        }
    }
}
