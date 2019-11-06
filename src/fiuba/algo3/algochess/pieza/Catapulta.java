package fiuba.algo3.algochess.pieza;

public class Catapulta extends Pieza {

    public Catapulta() {
        this.setCoste(5);
        this.setVida(50);
        this.setHabilidad(new Ataque());
    }

}
