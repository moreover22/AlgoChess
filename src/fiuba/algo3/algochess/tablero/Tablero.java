package fiuba.algo3.algochess.tablero;

import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.Rango;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.alcance.Alcance;
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

    //TODO Hay que sacarlo
    private Casillero getCasillero(Posicion posicion) throws FueraDelTableroException {
        if (!posicion.estaDentroDe(rango)) throw new FueraDelTableroException();
        return casilleros.get(posicion);
    }

    public void posicionar(Posicion posicion, Pieza pieza) throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException {
        getCasillero(posicion).posicionar(pieza);
    }

    public void ocupar(Posicion posicion, Pieza pieza) throws FueraDelTableroException {
        getCasillero(posicion).ocupar(pieza);
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

    @Override
    public void cambiarAlianza() {
        casilleros.forEach((posicion, casillero) -> casillero.cambiarAlianza());
    }
    public void buscarVecinos()throws FueraDelTableroException {
        Posicion posicionActual;
        Casillero casilleroActual;
        Pieza pieza;
        for (Map.Entry<Posicion,Casillero>p:casilleros.entrySet()){
            for (int i =-1;i <= 1;i++){
                for (int j =-1;j <= 1;j++){

                    posicionActual = p.getKey().aplicarDireccion(i,j);
                    casilleroActual = getCasillero(posicionActual);
                    pieza = casilleroActual.getPieza();
                    p.getValue().getPieza().agregarVecino(pieza);
                }
            }

        }
    }

    public void aplicarDanioTeritorio() {
        casilleros.forEach((posicion, casillero) -> casillero.aplicarDanioTerritorio());
    }

    public List<Pieza> buscarVecinosVertical(Pieza pieza)throws FueraDelTableroException {
        List<Pieza>vecinos = new ArrayList<Pieza>();
        Posicion posicionCentral = pieza.getPosicion();

        for (int j = -1; j <= 1; j++) {
            Posicion proximaPosicion = posicionCentral.aplicarDireccion(0,j);
            Pieza proximaPieza = getCasillero(proximaPosicion).getPieza();
            vecinos.add(proximaPieza);
        }
        return vecinos;
    }

    public List<Pieza> buscarVecinosHorizontal(Pieza pieza)throws FueraDelTableroException {
        List<Pieza>vecinos = new ArrayList<Pieza>();
        Posicion posicionCentral = pieza.getPosicion();

        for (int j = -1; j <= 1; j++) {
            Posicion proximaPosicion = posicionCentral.aplicarDireccion(0,j);
            Pieza proximaPieza = getCasillero(proximaPosicion).getPieza();
            vecinos.add(proximaPieza);
        }
        return vecinos;
    }
}


























