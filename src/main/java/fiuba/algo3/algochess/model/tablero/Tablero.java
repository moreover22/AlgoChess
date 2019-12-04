package fiuba.algo3.algochess.model.tablero;

import fiuba.algo3.algochess.model.*;
import fiuba.algo3.algochess.model.pieza.Movible;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.alcance.Alcance;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.casillero.Casillero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Tablero implements Aliable, Parseable {
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
        this.rango = new Rango(0, 0, cantColumnas, cantFilas);
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
            casilleros.put(new Posicion(j, fila), casillero);
        }
        for (int j = cantColumnas / 2; j < cantColumnas; j++){
            casilleros.get(new Posicion(j, fila)).cambiarAlianza();
        }
    }

    private Casillero getCasillero(Posicion posicion) throws FueraDelTableroException {
        if (!posicion.estaDentroDe(rango)) throw new FueraDelTableroException();
        return casilleros.get(posicion);
    }

    public void posicionar(Posicion posicion, Pieza pieza) throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException {
        getCasillero(posicion).posicionar(pieza);
        pieza.setPosicion(posicion);
    }

    public void ocupar(Posicion posicion, Pieza pieza) throws FueraDelTableroException {
        getCasillero(posicion).ocupar(pieza, this);
    }

    public void sacar(Posicion posicion) throws FueraDelTableroException {
        getCasillero(posicion).sacar();
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

    public Set<Pieza> getVecinos(Posicion posicion, Iterable<Direccion> direcciones) throws FueraDelTableroException {
        Set<Pieza> vecinos = new HashSet<Pieza>();
        for (Direccion direccion : direcciones) {
            Posicion posicionEnDireccion = direccion.aplicarA(posicion);
            if (! posicionEnDireccion.estaDentroDe(rango))
                continue;
            vecinos.add(getCasillero(posicionEnDireccion).getPieza());
        }
        return vecinos;
    }

    public void mover(Pieza pieza, Direccion direccion) throws FueraDelTableroException, MovimientoFueraDeAlcanceException {
        Movible movible = pieza.seleccionarParaMover(this);

        System.out.println("\033[1;32m" + "==========================================");
        System.out.println("pos antes de mover: " + pieza.getPosicion());
        System.out.println(pieza.parsear());
        System.out.println(pieza);

        movible.mover(direccion, this);
        System.out.println("pos despues de mover: " + pieza.getPosicion() + "\033[0m");
    }

    @Override
    public void cambiarAlianza() {
        casilleros.forEach((posicion, casillero) -> {
            casillero.cambiarAlianza();
        });
    }

    public void aplicarDanioTerritorio() {
        casilleros.forEach((posicion, casillero) -> {
            casillero.aplicarDanioTerritorio();
        });
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto estado = new ParserObjeto();
        estado.put("cantidad_filas", cantFilas);
        estado.put("cantidad_columnas", cantColumnas);
        estado.put("casilleros", casilleros);
        return estado;
    }
}
