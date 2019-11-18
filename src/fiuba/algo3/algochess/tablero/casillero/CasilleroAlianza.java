package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public interface CasilleroAlianza {
    CasilleroAlianza cambiar();
    void posicionar() throws PosicionarEnCasilleroEnemigoException;
    void aplicarDanioTerritorio(Pieza pieza);
}
























