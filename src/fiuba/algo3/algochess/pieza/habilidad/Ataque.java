package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;

public class Ataque implements Habilidad{
    private Arma arma;

    public Ataque(Arma arma) {
        this.arma = arma;
    }

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        if(objetivo.esAliado()) {
            throw new AtaqueAAliadoException();
        }
        arma.atacarA(objetivo, desde);
    }
}