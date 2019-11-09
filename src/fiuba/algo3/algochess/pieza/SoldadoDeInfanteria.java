package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.EspadaPesada;

public class SoldadoDeInfanteria extends Pieza {
    public SoldadoDeInfanteria(){
        super(100);
        this.setCoste(1);
        this.setHabilidad(new Ataque(new EspadaPesada()));
    }
}
