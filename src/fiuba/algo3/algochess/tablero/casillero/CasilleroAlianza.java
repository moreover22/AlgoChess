package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public interface CasilleroAlianza {
    CasilleroAlianza cambiar();
    void posicionar() throws ColocarEnCasilleroEnemigoException;
    void aplicarDanioTerritorio(Pieza pieza);
}
























