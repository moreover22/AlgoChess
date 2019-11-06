package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.alcance.Alcance;

public abstract class Arma {
    private Alcance alcance;
    private float danio;

    protected Arma(float danio, Alcance alcance) {
        this.danio = danio;
        this.alcance = alcance;
    }

    public void atacarA(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException {
        if (!alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.recibirDanio(danio);
    }

}



















