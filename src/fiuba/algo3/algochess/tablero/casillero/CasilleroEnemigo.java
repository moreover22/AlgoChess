package fiuba.algo3.algochess.tablero.casillero;

public class CasilleroEnemigo implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroAliado();
    }

    @Override
    public EstadoCasillero posicionar(EstadoCasillero estado) throws ColocarEnCasilleroEnemigoException {
        throw new ColocarEnCasilleroEnemigoException();
    }
}
