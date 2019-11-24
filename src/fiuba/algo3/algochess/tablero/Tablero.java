package fiuba.algo3.algochess.tablero;

import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.Rango;
import fiuba.algo3.algochess.pieza.Movible;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.PiezaNula;
import fiuba.algo3.algochess.pieza.alcance.Alcance;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.casillero.Casillero;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tablero implements Aliable {
    private Map<Posicion, Casillero> casilleros;
    private int cantFilas;
    private int cantColumnas;
    private Rango rango;

    public Tablero() {
        this(20, 20);
    }

    public Tablero(int cantFilas, int cantColumnas) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.rango = new Rango(0, 0, cantFilas, cantColumnas);
        this.casilleros = new HashMap<>();
        inicializarCasilleros();
    }

    private void inicializarCasilleros() {
        for (int i = 0; i < cantFilas; i++) {
            iniciarFila(i);
        }
    }

    private void iniciarFila(int fila) {
        for (int j = 0; j < cantColumnas; j++){
            Casillero casillero = new Casillero();
            casilleros.put(new Posicion(fila, j), casillero);
        }
        for (int j = cantColumnas / 2; j < cantColumnas; j++){
            casilleros.get(new Posicion(fila, j)).cambiarAlianza();
        }
    }

    private Casillero getCasillero(Posicion posicion) throws FueraDelTableroException {
        if (!posicion.estaDentroDe(rango)) throw new FueraDelTableroException();
        return casilleros.get(posicion);
    }

    public void posicionar(Posicion posicion, Pieza pieza) throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException {
        getCasillero(posicion).posicionar(pieza);
    }

    public void ocupar(Posicion posicion, Pieza pieza) throws FueraDelTableroException {
        getCasillero(posicion).ocupar(pieza, this);
    }

    public void vaciar(Posicion posicion) throws FueraDelTableroException {
        getCasillero(posicion).vaciar();
    }

    public Iterable<Pieza> piezasDentroDe(Alcance alcance, Posicion desde) {
        List<Pieza> piezas = new ArrayList<>();

        casilleros.forEach((posicion, casillero) -> {
            if (alcance.llegoA(desde, posicion)) {
                piezas.add(casillero.getPieza());
            }
        });
        return piezas;
    }

    @Override
    public void cambiarAlianza() {
        casilleros.forEach((posicion, casillero) -> casillero.cambiarAlianza());
    }

    public void aplicarDanioTeritorio() {
        casilleros.forEach((posicion, casillero) -> {
            casillero.aplicarDanioTerritorio(casillero.getPieza());
        });
    }

    public void mover(Pieza pieza, Direccion direccion) throws FueraDelTableroException, MovimientoFueraDeAlcanceException {
        Movible movible = pieza.seleccionarParaMover(this);
        movible.mover(direccion, this);
    }

    public Iterable<Pieza> getVecinos(Posicion posicion, Iterable<Direccion> direcciones) throws FueraDelTableroException {
        List<Pieza> vecinos = new ArrayList<>();
        for (Direccion direccion : direcciones) {
            Posicion posicionEnDireccion = direccion.aplicarA(posicion);
            if (! posicionEnDireccion.estaDentroDe(rango))
                continue;
            vecinos.add(getCasillero(posicionEnDireccion).getPieza());
        }

        return vecinos;
    }
}