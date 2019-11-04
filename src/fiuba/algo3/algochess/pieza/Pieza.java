package fiuba.algo3.algochess.pieza;

public class Pieza {

    protected float puntosDeVida;
    protected int coste;
    protected int fila;
    protected int columna;

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


}
