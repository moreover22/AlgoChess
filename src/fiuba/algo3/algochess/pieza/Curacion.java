package fiuba.algo3.algochess.pieza;


import fiuba.algo3.algochess.Posicion;
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
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException {
        Posicion hasta = objetivo.getPosicion();
        if(! alcance.llegoA(desde, hasta)) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.recibirCuracion(curacion);
    }
}
