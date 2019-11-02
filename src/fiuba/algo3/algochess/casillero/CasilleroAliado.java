package fiuba.algo3.algochess.casillero;

public class CasilleroAliado implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroEnemigo();
    }

    @Override
    public void colocar() {

    }
}
