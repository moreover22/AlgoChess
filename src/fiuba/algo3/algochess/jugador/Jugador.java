package fiuba.algo3.algochess.jugador;

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


    public void sumarCantPiezas() {
        this.cantPiezas++;
    }

    public void sacarPieza() {
        this.cantPiezas--;
    }


    public void agregarPieza(Tablero tablero, Pieza pieza, int x,int y) throws ExcesoCantPuntosException, ColocarEnCasilleroOcupadoException {

        this.cantPuntos = pieza.descontarCoste(this.cantPuntos);
        sumarCantPiezas();
        tablero.colocarPieza(pieza,x,y);
    }
}

