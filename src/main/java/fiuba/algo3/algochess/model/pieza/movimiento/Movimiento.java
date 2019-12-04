package fiuba.algo3.algochess.model.pieza.movimiento;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.alcance.Alcance;

public class Movimiento implements Parseable {
    private Alcance alcance;
    private Posicion ultimaPosicion;

    public Movimiento(Alcance alcance) {
        this.alcance = alcance;
    }

    public Posicion mover(Posicion desde, Direccion direccion) throws MovimientoFueraDeAlcanceException {
        Posicion destino = direccion.aplicarA(desde);
        if (!alcance.llegoA(desde, destino)) {
            throw new MovimientoFueraDeAlcanceException();
        }
        ultimaPosicion = desde;
        return destino;
    }

    public Posicion deshacerMovimiento() {
        return ultimaPosicion;
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("alcance", alcance);
        parser.put("ultima_posicion", ultimaPosicion);
        return parser;
    }
}
