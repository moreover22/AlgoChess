package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Movible;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.PiezaNula;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

public abstract class EstadoCasillero {
    protected Pieza pieza;
    public EstadoCasillero() {
        this.pieza = new PiezaNula();
    }

    public Pieza getPieza() {
        return pieza;
    }
    public abstract EstadoCasillero posicionar(Pieza pieza);
    public abstract EstadoCasillero ocupar(Pieza pieza, Tablero tablero) throws FueraDelTableroException;

    public EstadoCasillero vaciar() {
        return new EstadoCasilleroVacio();
    }
}
