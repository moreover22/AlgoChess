package fiuba.algo3.algochess.model.pieza.alcance;

public class AlcanceLejano extends Alcance {
    public AlcanceLejano() {
        this.setDistanciaMinima(6);
     }

    @Override
    final protected boolean llegoDistanciaMaxima(int distancia) {
        return true;
    }
}
