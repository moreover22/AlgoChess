package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.alcance.Alcance;
import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;

public class Curacion implements Habilidad {
    private Alcance alcance;
    private float curacion;

    public Curacion(float curacion) {
        this.curacion = curacion;
        this.alcance = new AlcanceCercano();
    }
    @Override
    public float aplicarHabilidad(float curacion, Pieza pieza) {
        return (pieza.getVida()+curacion);
    }
    @Override
    public void usarCon(Pieza objetivo, Posicion desde,Habilidad habilidad) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        if (! alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcanceException();
        }
<
        objetivo.recibirHabilidad(habilidad,curacion);

    }



}











