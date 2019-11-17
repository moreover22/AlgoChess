package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.pieza.Pieza;

public interface EstadoCasillero {
    EstadoCasillero ocupar(Pieza pieza);
    EstadoCasillero vaciar();
    EstadoCasillero posicionar();
}
