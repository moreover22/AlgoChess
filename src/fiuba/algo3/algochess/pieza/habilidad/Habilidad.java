package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;

public interface Habilidad {
    void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException;
    void aplicarHabilidad (float cantidad, Pieza pieza);
}
