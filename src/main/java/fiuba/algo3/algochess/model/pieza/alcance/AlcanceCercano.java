package fiuba.algo3.algochess.model.pieza.alcance;

public class AlcanceCercano extends Alcance {
    public AlcanceCercano() {
        this.setDistanciaMinima(1);
        this.setDistanciaMaxima(2);
    }
}
