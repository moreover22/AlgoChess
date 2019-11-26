package fiuba.algo3.algochess.model;

import fiuba.algo3.algochess.model.jugador.Jugador;

import java.util.*;

public class AlgoChess {
    private List<Jugador> jugadores;
    public AlgoChess() {
        jugadores = new ArrayList<>();
    }

    public void agregarJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
    }

    public void empezar() {
        Collections.shuffle(jugadores);
        for (Jugador jugador : jugadores) {
//            System.out.println(jugador.getPuntos());
        }
    }
}
