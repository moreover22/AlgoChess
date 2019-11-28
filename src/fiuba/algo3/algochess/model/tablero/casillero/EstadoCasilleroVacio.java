package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.Tablero;

public class EstadoCasilleroVacio extends EstadoCasillero {
    @Override
    public EstadoCasillero posicionar(Pieza pieza) {
        return new EstadoCasilleroOcupado(pieza);
    }

    @Override
    public EstadoCasillero ocupar(Pieza pieza, Tablero tablero) {
        return new EstadoCasilleroOcupado(pieza);
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("estado", "vacio");
        parser.put("pieza", pieza.parsear());
        return parser;
    }
}





