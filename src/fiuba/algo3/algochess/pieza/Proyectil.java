package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceLejano;

public class Proyectil extends Arma {

    public Proyectil(){
        this.danio = 20;
        this.alcance = new AlcanceLejano();
    }
}
