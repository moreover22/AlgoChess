package fiuba.algo3.algochess.model;

import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.jugador.Jugador;
import fiuba.algo3.algochess.model.pieza.Pieza;

import java.util.*;

public class AlgoChess implements Parseable {
    private List<Jugador> jugadores;
    private Jugador jugadorActual;
    private Turno turno;


    public AlgoChess() {
        jugadores = new ArrayList<>();
        turno = new Turno();
    }

    public void agregarJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
    }

    public void agregarAliable(Aliable nuevoAliable){
        turno.agregarAliable(nuevoAliable);
    }

    public void empezar() {
        Collections.shuffle(jugadores);
        jugadorActual = jugadores.get(0);
    }

    public void cambiarTurno(){
        Jugador jugadorAnterior = jugadores.remove(0);
        jugadores.add(jugadorAnterior);
        jugadorActual = jugadores.get(0);
        turno.cambiarTurno();
    }

    public void agregarPieza(Pieza pieza) throws CantidadDePuntosInsuficientesException {
        turno.agregarAliable(pieza);
        jugadorActual.agregarPieza(pieza);
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("jugador_actual", jugadorActual);
        return parser;
    }
}