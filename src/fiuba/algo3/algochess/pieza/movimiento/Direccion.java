package fiuba.algo3.algochess.pieza.movimiento;

import fiuba.algo3.algochess.Posicion;

public class Direccion {
    private int deltaX;
    private int deltaY;

    public Direccion(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Posicion aplicarA(Posicion desde) {
        return desde.aplicarDireccion(deltaX, deltaY);
    }

    public Posicion opuestaA(Posicion desde) {
        return desde.aplicarDireccion(-deltaX, -deltaY);
    }
}
