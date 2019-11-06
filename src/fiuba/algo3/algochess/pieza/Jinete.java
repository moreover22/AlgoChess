package fiuba.algo3.algochess.pieza;

public class Jinete extends Pieza {

    public Jinete() {
        this.setVidaInicial(100);
        this.setCoste(3);
        this.setVida(100);
        this.setHabilidad(new Ataque());
    }

}
