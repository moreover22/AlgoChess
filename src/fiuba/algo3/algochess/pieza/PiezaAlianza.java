package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.pieza.habilidad.CuracionAEnemigoException;

public interface PiezaAlianza {
    PiezaAlianza cambiar();
    float recibirDanio(float vida, float danio) throws AtaqueAAliadoException;
    float recibirCuracion(float vida, float curacion) throws CuracionAEnemigoException;
}
