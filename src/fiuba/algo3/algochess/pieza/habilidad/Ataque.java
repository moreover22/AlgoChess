package fiuba.algo3.algochess.pieza.habilidad;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.Pieza;
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
        arma.atacarA(objetivo, desde,this);
    }
    @Override
    public float aplicarHabilidad(float cantidad, Pieza pieza){
        return (pieza.getVida()-cantidad);
    }
}

























