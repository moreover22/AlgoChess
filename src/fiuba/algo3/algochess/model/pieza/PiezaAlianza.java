package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionAEnemigoException;

import java.util.List;

public interface PiezaAlianza {
    PiezaAlianza cambiar();
    float recibirDanio(float vida, float danio) throws AtaqueAAliadoException;
    float recibirCuracion(float vida, float curacion) throws CuracionAEnemigoException;
    int contarAliado(int cantidadAliados);
    int contarEnemigo(int cantidadEnemigos);
    void enlistarABatallon(List<Pieza> lista, Pieza pieza);

    String getAlianza();
}





