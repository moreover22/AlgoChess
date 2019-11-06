package fiuba.algo3.algochess.pieza.alcance;

public class AlcanceLejano extends Alcance {
    protected AlcanceLejano() {
        this.setDistanciaMinima(1);
     }

    @Override
    public boolean llegoDistanciaMaxima(int distancia) {
        return true;
    }
}
