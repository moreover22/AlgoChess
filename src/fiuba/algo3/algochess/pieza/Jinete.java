package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;
import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaLiviana;
import fiuba.algo3.algochess.tablero.Tablero;

public class Jinete extends Pieza {
    private Ataque ataque;

    public Jinete() {
        super(100, 3);
        ataque = new Ataque(new EspadaLiviana());
        habilidad = ataque;
    }

    @Override
    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        Iterable<Pieza> vecinos = tablero.piezasDentroDe(new AlcanceCercano(), this.posicion);
        ataque.actualizarArma(vecinos);
        super.usarHabilidadEn(tablero, objetivo);
    }
}