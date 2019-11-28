package fiuba.algo3.algochess.model;

import fiuba.algo3.algochess.model.jugador.Jugador;

import java.util.*;

public class AlgoChess {
    private List<Jugador> jugadores;
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
    }

    public void cambiarTurno(){
        turno.cambiarTurno();
    }
}









