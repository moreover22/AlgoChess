package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceNulo;
import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.Proyectil;
import fiuba.algo3.algochess.pieza.movimiento.Movimiento;

public class Catapulta extends Pieza {

    public Catapulta() {
        super(50);
        this.setCoste(5);
        this.setHabilidad(new Ataque(new Proyectil()));
        this.setMovimiento(new Movimiento(new AlcanceNulo()));
    }

}