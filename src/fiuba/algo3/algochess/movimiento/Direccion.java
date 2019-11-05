package fiuba.algo3.algochess.movimiento;

import fiuba.algo3.algochess.Posicion;

public abstract class Direccion {
    private int deltaX;
    private int deltaY;

    protected Direccion(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    Posicion aplicarA(Posicion desde) {
        int nuevoX = desde.getX() + deltaX;
        int nuevoY = desde.getY() + deltaY;
        return new Posicion(nuevoX, nuevoY);
    }
}
