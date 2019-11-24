package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.pieza.habilidad.CuracionAEnemigoException;
import fiuba.algo3.algochess.pieza.habilidad.Habilidad;

import java.util.List;

public interface PiezaAlianza {
    PiezaAlianza cambiar();
    //float recibirDanio(float vida, float danio) throws AtaqueAAliadoException;
    //float recibirCuracion(float vida, float curacion) throws CuracionAEnemigoException;
    int contarAliado(int cantidadAliados);
    int contarEnemigo(int cantidadEnemigos);

    //void enlistarABatallon(List<Pieza> lista, Pieza pieza);
    float recibirHabilidad(float vida, float cantidad, Habilidad habilidad,Pieza pieza);
}
