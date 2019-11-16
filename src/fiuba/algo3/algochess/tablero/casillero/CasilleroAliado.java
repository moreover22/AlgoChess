package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public class CasilleroAliado implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroEnemigo();
    }

    @Override
    public void posicionar() {

    }

    @Override
    public void aplicarDanioTerritorio(Pieza pieza){
        //No hace danio porque esta en territorio propio
    }
}





















