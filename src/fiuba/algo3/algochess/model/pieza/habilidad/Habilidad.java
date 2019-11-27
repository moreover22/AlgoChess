package fiuba.algo3.algochess.model.pieza.habilidad;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;

public interface Habilidad extends Parseable {
    void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException;
    float aplicarHabilidad (float cantidad, Pieza pieza);
}
