package fiuba.algo3.algochess.tablero;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.Rango;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.tablero.casillero.*;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
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
            if (j >= cantColumnas / 2) {
                casillero.cambiarAlianza();
            }
            casilleros.put(new Posicion(fila, j),casillero);
        }
    }

    private Casillero getCasillero(Posicion posicion) throws FueraDelTableroException {
        if (!posicion.estaDentroDe(rango)) throw new FueraDelTableroException();
        return casilleros.get(posicion);
    }

    public void posicionar(Pieza pieza, Posicion posicion) throws FueraDelTableroException, ColocarEnCasilleroEnemigoException, ColocarEnCasilleroOcupadoException {
        getCasillero(posicion).posicionar(pieza);
        pieza.setPosicion(posicion);
    }

    public void ocupar(Pieza pieza, Posicion posicion) throws FueraDelTableroException, ColocarEnCasilleroOcupadoException {
        getCasillero(posicion).ocupar(pieza);
        pieza.setPosicion(posicion);
    }

    public Pieza sacar(Posicion posicion) throws FueraDelTableroException, VaciarCasilleroVacioException {
        return getCasillero(posicion).sacar();
    }
    public void buscarVecinos()throws FueraDelTableroException {
        Posicion posicionActual;
        Casillero casilleroActual;
        Pieza pieza;
        for (Map.Entry<Posicion,Casillero>p:casilleros.entrySet()){
            for (int i =-1;i <= 1;i++){
                for (int j =-1;j <= 1;i++){

                    posicionActual = p.getKey().aplicarDireccion(i,j);
                    casilleroActual = getCasillero(posicionActual);
                    pieza = casilleroActual.getPieza();
                    p.getValue().getPieza().agregarVecino(pieza);
                }
            }

        }
    }
}
