package fiuba.algo3.algochess.casillero;

public class CasilleroEnemigo implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroAliado();
    }

    @Override
    public void colocar() throws ColocarEnCasilleroEnemigoException {
        throw new ColocarEnCasilleroEnemigoException();
    }
}
