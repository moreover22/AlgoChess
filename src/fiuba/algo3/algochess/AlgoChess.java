package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.jugador.Jugador;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class AlgoChess {
    private Tablero tablero;
    private List<Pieza> piezas;
    private Jugador[] jugadores;
    private MovedorPiezas movedor;
    private PosicionadorPiezas posicionador;

    public AlgoChess() {
        tablero = new Tablero();
        piezas = new ArrayList<>();
        jugadores = new Jugador[2];
        movedor = new MovedorPiezas(tablero);
        posicionador = new PosicionadorPiezas(tablero);
    }

    public void colocar(Pieza pieza, Posicion posicion) throws CasilleroException, FueraDelTableroException {
        posicionador.posicionarPieza(pieza, posicion);
    }

    public void mover(Pieza pieza, Direccion direccion) throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        movedor.mover(pieza, direccion);
    }
}
