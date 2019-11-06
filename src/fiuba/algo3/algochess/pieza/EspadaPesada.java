package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;

public class EspadaPesada extends Arma { //ES LA ESPADA QUE USARIA EL SOLDADO//

    public EspadaPesada(){
        super(10, new AlcanceCercano());

    }
}
