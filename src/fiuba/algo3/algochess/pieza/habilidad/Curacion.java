package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.alianza.Aliable;
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
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        verificarCurabilidad(objetivo, desde);
        objetivo.recibirCuracion(curacion);
    }

    private void verificarCurabilidad(Pieza objetivo, Posicion desde) throws CuracionAEnemigoException, HabilidadFueraDeAlcanceException {
        verificarAlianza(objetivo);
        verificarAlcance(desde, objetivo.getPosicion());
    }

    private void verificarAlianza(Aliable objetivo) throws CuracionAEnemigoException {
        if (!objetivo.esAliado()) {
            throw new CuracionAEnemigoException();
        }
    }

    private void verificarAlcance(Posicion desde, Posicion hasta) throws HabilidadFueraDeAlcanceException {
        if(!alcance.llegoA(desde, hasta)) {
            throw new HabilidadFueraDeAlcanceException();
        }
    }

}















