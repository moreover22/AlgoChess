package fiuba.algo3.algochess.pieza.movimiento;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.alcance.Alcance;

public class Movimiento {
    private Alcance alcance;

    public Movimiento(Alcance alcance) {
        this.alcance = alcance;
    }

    public Posicion mover(Posicion desde, Direccion direccion) throws MovimientoFueraDeAlcanceException {
        Posicion hasta = direccion.aplicarA(desde);
        if (!alcance.llegoA(desde, hasta)) {
            throw new MovimientoFueraDeAlcanceException();
        }
        return direccion.aplicarA(desde);
    }
}
