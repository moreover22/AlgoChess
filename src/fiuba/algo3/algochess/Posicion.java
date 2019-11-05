package fiuba.algo3.algochess;

public class Posicion {
    private int y;
    private int x;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void cambiarX(int x) {
        this.x = x;
    }

    public void cambiarY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
