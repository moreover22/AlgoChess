package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaPesada;

public class SoldadoDeInfanteria extends Pieza {
    public SoldadoDeInfanteria(){
        super(100, 1);
        habilidad = new Ataque(new EspadaPesada());

    }

    @Override
    public int contarAliadosDeCaballeria(int cantidadAliadosCaballeria) {
        return alianza.contarAliado(cantidadAliadosCaballeria);
    }
}
