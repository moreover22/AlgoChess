package fiuba.algo3.algochess.model;

import fiuba.algo3.algochess.model.jugador.Jugador;
import fiuba.algo3.algochess.model.tablero.Tablero;

import java.util.*;

public class AlgoChess {
    private List<Jugador> jugadores;
    private Turno turno;
    private Aliable aliable;
    public AlgoChess() {
        jugadores = new ArrayList<>();
        turno = new Turno();
    }

    public void agregarJugador(String nombre) {
        jugadores.add(new Jugador(nombre));
    }

    public void agregarAliable(Aliable nuevoaliable){
        this.aliable = nuevoaliable;
        turno.agregarAliable(aliable);
    }
    public void empezar() {
        Collections.shuffle(jugadores);
        for (Jugador jugador : jugadores) {
//            System.out.println(jugador.getPuntos());
        }
    }
    public void cambiarTurno(){
        turno.cambiarTurno();
    }
}