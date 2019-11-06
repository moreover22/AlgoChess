package fiuba.algo3.algochess.pieza;

public class SoldadoDeInfanteria extends Pieza {
    public SoldadoDeInfanteria(){
        this.setCoste(1);
        this.setVida(100);
        this.setHabilidad(new Ataque());
    }
}
