package fiuba.algo3.algochess.casillero;

import fiuba.algo3.algochess.Pieza;

public class Casillero {
    private Pieza pieza;
    private  EstadoCasillero estado;
    private CasilleroAlianza alianza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
    }

    public void colocar(Pieza pieza) throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        if (!estado.estaVacio()) {
            throw new ColocarEnCasilleroOcupadoException();
        }
        if (!alianza.esAliado()) {
            throw new ColocarEnCasilleroEnemigoException();
        }
        this.pieza = pieza;
        estado = new EstadoCasilleroOcupado();
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }
}
