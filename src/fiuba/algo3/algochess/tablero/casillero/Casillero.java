package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.pieza.Pieza;

public class Casillero implements Aliable {
    private EstadoCasillero estado;
    private CasilleroAlianza alianza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
    }

    public void posicionar(Pieza pieza) throws PosicionarEnCasilleroEnemigoException {
        alianza.posicionar();
        estado = estado.posicionar(pieza);
    }

    public void ocupar(Pieza pieza) {
        estado = estado.ocupar(pieza);
    }

    public void sacar() {
        estado = estado.vaciar();
    }

    public Pieza getPieza() {
        return estado.getPieza();
    }

    @Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }
}