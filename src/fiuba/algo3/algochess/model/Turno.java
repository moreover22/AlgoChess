package fiuba.algo3.algochess.model;

import fiuba.algo3.algochess.model.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Turno {
    private List<Aliable> aliables;
    public Turno(){
        aliables = new ArrayList<>();
    }

    public void agregarAliable(Aliable Aliable){
    aliables.add(Aliable);
    }

    public void cambiarTurno() {
        for(Aliable aliable: aliables){aliable.cambiarAlianza();};
    }

}
