package fiuba.algo3.algochess.tablero.casillero;

public class CasilleroEnemigo implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroAliado();
    }

    @Override
    public boolean esAliado() {
        return false;
    }
}
