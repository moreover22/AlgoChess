package fiuba.algo3.algochess.model.jugador;

import fiuba.algo3.algochess.model.pieza.Pieza;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int puntos;
    private String nombre;
    private List<Pieza> piezas;

    public Jugador(String nombre, int puntos) {
        this.puntos = puntos;
        this.piezas = new ArrayList<>();
        this.nombre = nombre;
    }

    public Jugador(String nombre) {
        this(nombre, 20);
    }

    public Jugador(int puntos) {
        this("", puntos);
    }

    public void sacarPieza(Pieza pieza) {
        puntos = pieza.agregarCoste(puntos);
        piezas.remove(pieza);
    }

    public int getPuntos() {
        return puntos;
    }

    public void agregarPieza(Pieza pieza) throws CantidadDePuntosInsuficientesException {
        if (pieza.descontarCoste(puntos) < 0) {
            throw new CantidadDePuntosInsuficientesException();
        }
        puntos = pieza.descontarCoste(puntos);
        piezas.add(pieza);
    }

    public boolean perdio() {
        for (Pieza pieza : piezas) {
            if (pieza.estaViva()) return false;
        }
        return true;
    }

}