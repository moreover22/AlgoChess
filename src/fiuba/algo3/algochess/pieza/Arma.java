package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;

public abstract class Arma {
    private Alcance alcance;
    private float danio;

    protected Arma(float danio, Alcance alcance) {
        this.danio = danio;
        this.alcance = alcance;
    }

    public void atacarA(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcance {
        if (!alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcance();
        }
        objetivo.recibirDanio(danio);
    }

}



















