package fiuba.algo3.algochess.pieza.habilidad.armas;

import fiuba.algo3.algochess.pieza.alcance.AlcanceMedio;

public class ArcoYFlecha extends Arma {
    public ArcoYFlecha() {
        super(15, new AlcanceMedio());
    }

    @Override
    final protected boolean criterioDeActualizacion(int cantidadAliados, int cantidadEnemigos) {
        return cantidadAliados == 0 && cantidadEnemigos > 0;
    }

    @Override
    final protected Arma armaActualizada() {
        return new EspadaLiviana();
    }
}




























