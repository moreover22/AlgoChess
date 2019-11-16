package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public class CasilleroEnemigo implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroAliado();
    }

    @Override
    public void posicionar() throws ColocarEnCasilleroEnemigoException {
        throw new ColocarEnCasilleroEnemigoException();
    }
    @Override
    public void aplicarDanioTerritorio(Pieza pieza){
        pieza.recibirDanioTerritorio();
    }
}
































































