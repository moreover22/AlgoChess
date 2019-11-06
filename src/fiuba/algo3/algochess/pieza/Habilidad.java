package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;

public interface Habilidad {
    void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException;
}
