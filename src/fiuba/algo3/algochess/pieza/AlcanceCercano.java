package fiuba.algo3.algochess.pieza;

public class AlcanceCercano extends Alcance {

    private static final int DISTANCIA_MINIMA = 1;
    private static final int DISTANCIA_MAXIMA = 2;

    @Override
    public boolean llego(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

        int distancia = calcularDistancia( filaOrigen, columnaOrigen, filaDestino, columnaDestino);

        return (distancia >= DISTANCIA_MINIMA && distancia <= DISTANCIA_MAXIMA);
    }
}
