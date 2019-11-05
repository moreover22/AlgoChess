package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;

public class Ataque implements Habilidad{
    private Arma arma;

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcance {
        arma.atacarA(objetivo, desde);
    }
}
