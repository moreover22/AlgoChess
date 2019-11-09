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

    public boolean estaDentroDe(Rango rango) {
        return rango.estaDentro(x, y);
    }

    private int deltaX(int x){
        return abs(this.x - x);
    }

    private int deltaY(int y){
        return abs(this.y - y);
    }

    public int calcularDistancia(Posicion hasta) {
        return max(hasta.deltaX(x), hasta.deltaY(y));
    }

    public Posicion aplicarDireccion(int deltaX, int deltaY) {
        return new Posicion(x + deltaX, y + deltaY);
    }

    /*
     * Se sobre-escriben equals y hashCode para poder usarlos en un Map
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int resultado = 1;
        resultado = prime * resultado + x;
        resultado = prime * resultado + y;
        return resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Posicion otraPosicion = (Posicion) o;
        return otraPosicion.x == x && otraPosicion.y == y;
    }
}

