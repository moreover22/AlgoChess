package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;

import static java.lang.Math.max;
import static java.lang.Math.abs;

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
        int distancia = calcularDistancia(desde, hasta);
        return llegoDistanciaMinima(distancia) && llegoDistanciaMaxima(distancia);
    }

    protected boolean llegoDistanciaMinima(int distancia) {
        return distancia >= distanciaMinima;
    }

    protected boolean llegoDistanciaMaxima(int distancia) {
        return distancia <= distanciaMaxima;
    }

    protected int calcularDistancia(Posicion desde, Posicion hasta) {
        int deltaX = abs(desde.getX() - hasta.getX());
        int deltaY = abs(desde.getY() - hasta.getY());
        return max(deltaX, deltaY);
    }
}





















