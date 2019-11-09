package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.Proyectil;

public class Catapulta extends Pieza {

    public Catapulta() {
        super(50);
        this.setCoste(5);
        this.setHabilidad(new Ataque(new Proyectil()));
    }

}