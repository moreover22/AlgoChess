package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.jugador.Jugador;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroOcupadoException;

import java.util.ArrayList;
import java.util.List;

public class AlgoChess {
    private Tablero tablero;
    private List<Pieza> piezas;
    private Jugador[] jugadores;
    public AlgoChess() {
        tablero = new Tablero();
        piezas = new ArrayList<>();
        jugadores = new Jugador[2];
    }

    public void posicionar(Pieza pieza, Posicion posicion) throws FueraDelTableroException, ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        tablero.posicionar(pieza, posicion);
    }

    public Posicion mover(Pieza pieza, Direccion direccion) throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        Posicion destino = pieza.mover(direccion);
        tablero.sacar(direccion.opuestaA(destino));
        tablero.ocupar(pieza, destino);
        return destino;
    }
}