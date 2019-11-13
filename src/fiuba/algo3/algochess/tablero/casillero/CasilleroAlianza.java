package fiuba.algo3.algochess.tablero.casillero;

public interface CasilleroAlianza {
    CasilleroAlianza cambiar();
    EstadoCasillero posicionar(EstadoCasillero estado) throws ColocarEnCasilleroEnemigoException, ColocarEnCasilleroOcupadoException;
}
