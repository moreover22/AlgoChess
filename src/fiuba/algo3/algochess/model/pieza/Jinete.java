package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.alcance.AlcanceCercano;
import fiuba.algo3.algochess.model.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.pieza.habilidad.armas.EspadaLiviana;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

public class Jinete extends Pieza {
    private Ataque ataque;

    public Jinete() {
        super(100, 3);
        ataque = new Ataque(new EspadaLiviana());
        habilidad = ataque;
        tipoPieza = "jinete";
    }

    @Override
    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, FueraDelTableroException {
        Iterable<Pieza> vecinos = tablero.piezasDentroDe(new AlcanceCercano(), this.posicion);
        ataque.actualizarArma(vecinos);
        super.usarHabilidadEn(tablero, objetivo);
    }

    public static String getName() {
        return "Jinete";
    }

}




















