package fiuba.algo3.algochess.casillero;

import fiuba.algo3.algochess.Pieza;

public class Casillero {
    private Pieza pieza;
    private EstadoCasillero estado;
    private CasilleroAlianza alianza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
    }

    public void colocar(Pieza pieza) throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        estado = estado.ocupar();
        alianza.colocar();
        this.pieza = pieza;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }

    public Pieza vaciar() throws VaciarCasilleroVacioException {
        Pieza piezaAEliminar = pieza;
        estado = estado.vaciar();
        return piezaAEliminar;
    }
}
