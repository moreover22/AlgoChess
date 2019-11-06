package fiuba.algo3.algochess.pieza;

public class Curandero extends Pieza {

    public Curandero () {
        this.setVidaInicial(75);
        this.setCoste(2);
        this.setVida(75);
        this.setHabilidad(new Curacion(15));
    }
}
