package fiuba.algo3.algochess.casillero;

public interface CasilleroAlianza {
    CasilleroAlianza cambiar();
    void colocar() throws ColocarEnCasilleroEnemigoException ;
}
