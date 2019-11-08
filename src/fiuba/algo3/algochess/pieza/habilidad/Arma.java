package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.alcance.Alcance;

public abstract class Arma {

    protected Alcance alcance;
    protected float danio;

    protected Arma(float danio, Alcance alcance) {
        this.danio = danio;
        this.alcance = alcance;
    }


    public void atacarA(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException {
        Posicion posicionEnemigo = objetivo.getPosicion();
        if (!alcance.llegoA(desde, posicionEnemigo)) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.descontarDanio(danio);
    }

}



















