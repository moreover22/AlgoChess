package fiuba.algo3.algochess.movimiento;


import fiuba.algo3.algochess.Posicion;

public class Movimiento {
    public Posicion mover(Posicion desde, Direccion direccion){
        return direccion.aplicarA(desde);
    }
}
