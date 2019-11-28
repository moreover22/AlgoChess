package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;


public class EstadoCasilleroOcupado extends EstadoCasillero {

    public EstadoCasilleroOcupado(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public EstadoCasillero posicionar(Pieza pieza) {
        throw new PosicionarEnCasilleroOcupadoException();
    }

    @Override
    public EstadoCasillero ocupar(Pieza pieza) {
        pieza.deshacerMovimiento();
        return this;
    }

    @Override
    public String getEstado() {
        return "ocupado";
    }
}


