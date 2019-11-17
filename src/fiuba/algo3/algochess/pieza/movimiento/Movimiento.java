package fiuba.algo3.algochess.pieza.movimiento;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.alcance.Alcance;

public class Movimiento {
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
}
