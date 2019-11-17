package fiuba.algo3.algochess.pieza.movimiento;

import fiuba.algo3.algochess.Posicion;

public class Direccion {
    private int deltaX;
    private int deltaY;

    private static Direccion izquierdaArriba;
    private static Direccion arriba;
    private static Direccion derechaArriba;
    private static Direccion derecha;
    private static Direccion derechaAbajo;
    private static Direccion abajo;
    private static Direccion izquierdaAbajo;
    private static Direccion izquierda;

    static {
        derecha = new Direccion(1, 0);
        izquierda = new Direccion(-1, 0);
        arriba = new Direccion(0, -1);
        abajo = new Direccion(0, 1);
        derechaArriba = new Direccion(derecha, arriba);
        derechaAbajo = new Direccion(derecha, abajo);
        izquierdaArriba = new Direccion(izquierda, arriba);
        izquierdaAbajo = new Direccion(izquierda, abajo);
    }

    private Direccion(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    private Direccion(Direccion unaDireccion, Direccion otraDireccion) {
        this.deltaX = unaDireccion.deltaX + otraDireccion.deltaX;
        this.deltaY = unaDireccion.deltaY + otraDireccion.deltaY;
    }

    public Posicion aplicarA(Posicion desde) {
        return desde.aplicarDireccion(deltaX, deltaY);
    }

    public Posicion opuestaA(Posicion desde) {
        return desde.aplicarDireccion(-deltaX, -deltaY);
    }

    public static Direccion derecha() {
        return derecha;
    }

    public static Direccion izquierda() {
        return izquierda;
    }

    public static Direccion arriba() {
        return arriba;
    }

    public static Direccion abajo() {
        return abajo;
    }

    public static Direccion derechaArriba() {
        return derechaArriba;
    }

    public static Direccion derechaAbajo() {
        return derechaAbajo;
    }

    public static Direccion izquierdaArriba() {
        return izquierdaArriba;
    }

    public static Direccion izquierdaAbajo() {
        return izquierdaAbajo;
    }
}
