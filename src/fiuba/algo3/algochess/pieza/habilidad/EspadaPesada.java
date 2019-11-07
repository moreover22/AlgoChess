package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;

public class EspadaPesada extends Arma {
    public EspadaPesada() {
        super(10, new AlcanceCercano());
    }
}
