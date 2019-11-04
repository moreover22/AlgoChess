package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Movimiento.Direccion;
import fiuba.algo3.algochess.Movimiento.Movimiento;

public abstract class Pieza {
    protected float puntosDeVida;
    protected int coste;
    protected int fila;
    protected int columna;
    protected Habilidad habilidad;
    protected Movimiento movimiento;

    public Pieza(int fila,int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public void recibirDanio(float danio){
        this.puntosDeVida -= danio;
    }

    public int descontarCoste(int monto ){
        return (monto - this.coste) ;
    }
    public abstract void usarHabilidadEn(Pieza objetivo);

    public void mover(Direccion direccion){ movimiento.mover(this.columna,this.fila,direccion);}

}
