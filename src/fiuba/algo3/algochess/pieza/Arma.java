package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.alcance.Alcance;

public abstract class Arma {

    protected Alcance alcance;
    protected float danio;

    protected Arma() {
    }

    protected Arma(float danio, Alcance alcance) {
        this.danio = danio;
        this.alcance = alcance;
    }


    public void atacarA(Pieza objetivo, Posicion desde) throws ArmaFueraDeAlcanceException {
        if (!alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new ArmaFueraDeAlcanceException();
        }
        objetivo.recibirDanio(danio);
    }

}



















