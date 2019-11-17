package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaLiviana;

public class Jinete extends Pieza {
    public Jinete() {
        super(100);
        this.setCoste(3);
        this.setHabilidad(new Ataque(new EspadaLiviana()));
    }
}