package fiuba.algo3.algochess.pieza.movimiento;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.alcance.Alcance;
import fiuba.algo3.algochess.pieza.alcance.AlcanceInmediato;

public class Movimiento {
    private Alcance alcance;

    public Movimiento() {
        alcance = new AlcanceInmediato();
    }

    public Posicion mover(Posicion desde, Direccion direccion) throws MovimientoFueraDeAlcanceException {
        Posicion hasta = direccion.aplicarA(desde);
        if (!alcance.llegoA(desde, hasta)) {
            throw new MovimientoFueraDeAlcanceException();
        }
        return direccion.aplicarA(desde);
    }
}
