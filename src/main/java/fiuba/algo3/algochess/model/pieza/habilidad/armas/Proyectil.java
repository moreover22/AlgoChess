package fiuba.algo3.algochess.model.pieza.habilidad.armas;

import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.alcance.AlcanceLejano;

import java.util.Set;

public class Proyectil extends Arma {
    public Proyectil() {
        super(20, new AlcanceLejano());
        tipoArma = "proyectil";
    }

    @Override
    public void atacarAGrupo(Set<Pieza> objetivos){
        for(Pieza pieza : objetivos){
            pieza.recibirHabilidad(pieza.getVida()-danio);
        }
    }

}


