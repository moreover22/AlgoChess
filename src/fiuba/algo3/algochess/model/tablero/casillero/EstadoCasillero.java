package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.PiezaNula;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

public abstract class EstadoCasillero implements Parseable {
    protected Pieza pieza;

    public EstadoCasillero() {
        this.pieza = new PiezaNula();
    }

    public Pieza getPieza() {
        return pieza;
    }

    public abstract EstadoCasillero posicionar(Pieza pieza);
    public abstract EstadoCasillero ocupar(Pieza pieza, Tablero tablero) throws FueraDelTableroException;
    // public abstract EstadoCasillero ocupar(Pieza pieza);
    public EstadoCasillero vaciar() {
        return new EstadoCasilleroVacio();
    }

}






