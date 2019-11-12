package fiuba.algo3.algochess.pieza.alcance;

public class AlcanceNulo extends Alcance {
    @Override
    final protected boolean llegoDistanciaMinima(int distancia) {
        return false;
    }

    @Override
    final protected boolean llegoDistanciaMaxima(int distancia) {
        return false;
    }
}
