package fiuba.algo3.algochess.pieza.alcance;

import fiuba.algo3.algochess.Posicion;

public abstract class Alcance {
    private int distanciaMinima;
    private int distanciaMaxima;

    protected void setDistanciaMinima(int distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    protected void setDistanciaMaxima(int distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }

    final public boolean llegoA(Posicion desde, Posicion hasta) {
        int distancia = desde.calcularDistancia(hasta);
        return llegoDistanciaMinima(distancia) && llegoDistanciaMaxima(distancia);
    }

    protected boolean llegoDistanciaMinima(int distancia) {
        return distancia >= distanciaMinima;
    }

    protected boolean llegoDistanciaMaxima(int distancia) {
        return distancia <= distanciaMaxima;
    }
}