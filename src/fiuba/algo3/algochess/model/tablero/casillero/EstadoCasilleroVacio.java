package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.pieza.Pieza;

public class EstadoCasilleroVacio extends EstadoCasillero {
    @Override
    public EstadoCasillero posicionar(Pieza pieza) {
        return new EstadoCasilleroOcupado(pieza);
    }

    @Override
    public EstadoCasillero ocupar(Pieza pieza) {
        return new EstadoCasilleroOcupado(pieza);
    }

    @Override
    public String getEstado() {
        return "vacio";
    }
}





