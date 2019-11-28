package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionAEnemigoException;
import fiuba.algo3.algochess.model.pieza.habilidad.Habilidad;

import java.util.List;

public interface PiezaAlianza {
    PiezaAlianza cambiar();
    int contarAliado(int cantidadAliados);
    int contarEnemigo(int cantidadEnemigos);

    void enlistarABatallon(List<Pieza> lista, Pieza pieza);
    float recibirAtaque(float cantidad,Pieza pieza) throws AtaqueAAliadoException;
    float recibirCuracion(float cantidad,Pieza pieza) throws CuracionAEnemigoException;

    String getAlianza();
}
















