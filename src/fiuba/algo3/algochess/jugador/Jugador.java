package fiuba.algo3.algochess.jugador;

import fiuba.algo3.algochess.FueraDelTableroException;
import fiuba.algo3.algochess.casillero.CasilleroException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.Tablero;

public class Jugador {
    private int cantPuntos;
    private int cantPiezas;
    private char nombre;

    public Jugador() {
        this.cantPuntos = 20;
        this.cantPiezas = 0;
        this.nombre = '-';

    }

    public int obtenerCantPiezas() {
        return this.cantPiezas;
    }

    public void sacarPieza() {
        this.cantPiezas--;
    }

    public void agregarPieza(Tablero tablero, Pieza pieza, int x,int y) throws CantidadDePuntosInsuficientesException, CasilleroException, FueraDelTableroException {
        if (cantPuntos - pieza.getCoste() < 0) {
            throw new CantidadDePuntosInsuficientesException();
        }
        this.cantPuntos -= pieza.getCoste();
        this.cantPiezas++;
        tablero.colocarPieza(pieza, x, y);
    }
}