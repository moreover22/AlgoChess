package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.alianza.Aliable;
import fiuba.algo3.algochess.alianza.EstadoAliado;
import fiuba.algo3.algochess.alianza.EstadoAlianza;

public class Casillero implements Aliable {
    private EstadoCasillero estado;
    private EstadoAlianza alianza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new EstadoAliado();
    }

    public boolean estaVacio() {
        return estado.estaVacio();
    }

    public void vaciar() throws VaciarCasilleroVacioException {
        estado = estado.vaciar();
    }

    public void ocupar() throws ColocarEnCasilleroOcupadoException {
        estado = estado.ocupar();
    }
    @Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }

    @Override
    public boolean esAliado() {
        return alianza.esAliado();
    }
}
