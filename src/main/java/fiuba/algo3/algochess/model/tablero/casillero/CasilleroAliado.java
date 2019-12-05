package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.pieza.Pieza;

public class CasilleroAliado implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroEnemigo();
    }

    @Override
    public void posicionar() {
    }

    @Override
    public void aplicarDanioTerritorio(Pieza pieza) {
    }

    @Override
    public String getAlianza() {
        return "aliado";
    }
}