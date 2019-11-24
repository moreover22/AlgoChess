package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.PiezaAlianza;
import fiuba.algo3.algochess.pieza.Vida;
import fiuba.algo3.algochess.pieza.habilidad.armas.Arma;

public class Ataque implements Habilidad{
    private Arma arma;

    public Ataque(Arma arma) {
        this.arma = arma;
    }

    public void actualizarArma(Iterable<Pieza> piezas) {
        arma = arma.actualizar(piezas);
    }

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        arma.atacarA(objetivo, desde);
    }

//    @Override
//    public void aplicar(Vida vida, PiezaAlianza alianza) throws HabilidadConObjetivoInvalidoException {
//        arma.usarArma(vida, alianza);
//    }
}