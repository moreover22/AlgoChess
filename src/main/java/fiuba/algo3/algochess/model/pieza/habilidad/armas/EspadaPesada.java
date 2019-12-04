package fiuba.algo3.algochess.model.pieza.habilidad.armas;

import fiuba.algo3.algochess.model.pieza.alcance.AlcanceCercano;

public class EspadaPesada extends Arma {
    public EspadaPesada() {
        super(10, new AlcanceCercano());
    }
}
