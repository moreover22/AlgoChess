package fiuba.algo3.algochess.pieza.alcance;

public class AlcanceCercano extends Alcance {
    protected AlcanceCercano() {
        this.setDistanciaMinima(1);
        this.setDistanciaMaxima(2);
    }
}
