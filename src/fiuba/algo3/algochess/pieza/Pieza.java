package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Movimiento.Direccion;
import fiuba.algo3.algochess.Movimiento.Movimiento;

public class Pieza {

    protected float puntosDeVida;
    protected int coste;
    protected int fila;
    protected int columna;
    protected Movimiento movimiento;

    public Pieza(int fila,int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public void recibirDanio(float danio){
        puntosDeVida -= danio;
    }

    public int descontarCoste(int monto ){
        return (monto - coste) ;
    }

    public void mover(Direccion direccion){ movimiento.mover(this.columna,this.fila,direccion);}

}
