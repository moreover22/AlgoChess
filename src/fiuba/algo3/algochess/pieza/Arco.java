package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceMedio;

public class Arco extends Arma {

    public Arco(){
        this.danio = 15;
        this.alcance = new AlcanceMedio();
    }

}
