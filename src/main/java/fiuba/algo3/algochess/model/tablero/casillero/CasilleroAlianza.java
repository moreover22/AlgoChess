package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.pieza.Pieza;

public interface CasilleroAlianza {
    CasilleroAlianza cambiar();
    void posicionar() throws PosicionarEnCasilleroEnemigoException;
    void aplicarDanioTerritorio(Pieza pieza);
    String getAlianza();
}
























