package fiuba.algo3.algochess.tablero.casillero;

public class Casillero {
    private EstadoCasillero estado;
    private CasilleroAlianza alianza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
    }

    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }

    public void vaciar() throws VaciarCasilleroVacioException {
        estado = estado.vaciar();
    }

    public void ocupar() throws ColocarEnCasilleroOcupadoException {
        estado = estado.ocupar();
    }

    public boolean esAliado() {
        return alianza.esAliado();
    }

    public boolean estaVacio() {
        return estado.estaVacio();
    }
}
