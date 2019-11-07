package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Curacion;

public class Curandero extends Pieza {

    public Curandero () {
        super(75);
        this.setCoste(2);
        this.setHabilidad(new Curacion(15));
    }
}
