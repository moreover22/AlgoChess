package fiuba.algo3.algochess;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Posicion {
    private int y;
    private int x;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int calcularDistancia(Posicion hasta) {
        int deltaX = abs(this.getX() - hasta.getX());
        int deltaY = abs(this.getY() - hasta.getY());
        return max(deltaX, deltaY);
    }
}

