package fiuba.algo3.algochess.tablero.casillero;

public class CasilleroAliado implements CasilleroAlianza {
    @Override
    public CasilleroAlianza cambiar() {
        return new CasilleroEnemigo();
    }

    @Override
    public EstadoCasillero posicionar(EstadoCasillero estado) throws ColocarEnCasilleroOcupadoException {
        return estado.ocupar();
    }
}
