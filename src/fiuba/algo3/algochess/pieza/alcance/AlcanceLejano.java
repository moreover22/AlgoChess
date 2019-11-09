package fiuba.algo3.algochess.pieza.alcance;

public class AlcanceLejano extends Alcance {
    public AlcanceLejano() {
        this.setDistanciaMinima(6);
     }

    @Override
    public boolean llegoDistanciaMaxima(int distancia) {
        return true;
    }
}
