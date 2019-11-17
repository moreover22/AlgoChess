package fiuba.algo3.algochess.pieza.habilidad.armas;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.alcance.Alcance;
import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;

public abstract class Arma {

    protected Alcance alcance;
    protected float danio;

    protected Arma(float danio, Alcance alcance) {
        this.danio = danio;
        this.alcance = alcance;
    }

    public void atacarA(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException {
        if (! alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.recibirDanio(danio);
    }
}















