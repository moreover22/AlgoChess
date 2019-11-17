package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceNulo;
import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.Proyectil;
import fiuba.algo3.algochess.pieza.movimiento.Movimiento;

public class Catapulta extends Pieza {
    public Catapulta() {
        super(50, 5);
        habilidad = new Ataque(new Proyectil());
        movimiento = new Movimiento(new AlcanceNulo());
    }
}