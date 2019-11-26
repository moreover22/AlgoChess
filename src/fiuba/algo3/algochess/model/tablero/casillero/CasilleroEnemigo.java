package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.pieza.Pieza;

public class CasilleroEnemigo implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroAliado();
    }

    @Override
    public void posicionar() throws PosicionarEnCasilleroEnemigoException {
        throw new PosicionarEnCasilleroEnemigoException();
    }

    @Override
    public void aplicarDanioTerritorio(Pieza pieza){
        pieza.recibirDanioTerritorio();
    }

    @Override
    public String getAlianza() {
        return "enemigo";
    }
}
































































