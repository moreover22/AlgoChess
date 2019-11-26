package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.habilidad.Curacion;

public class Curandero extends Pieza {
    public Curandero () {
        super(75, 2);
        habilidad = new Curacion(15);
    }
}
