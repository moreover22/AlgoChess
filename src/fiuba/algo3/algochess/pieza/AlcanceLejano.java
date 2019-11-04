package fiuba.algo3.algochess.pieza;

public class AlcanceLejano extends Alcance {

    private static final int DISTANCIA_MINIMA = 6;

    @Override
    public boolean llego(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

        int distancia = calcularDistancia( filaOrigen,columnaOrigen, filaDestino,columnaDestino);

        return(distancia >=DISTANCIA_MINIMA);
    }
}
