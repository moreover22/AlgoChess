package fiuba.algo3.algochess.pieza;

public abstract class Pieza {

    protected float puntosDeVida;
    protected int coste;
    protected int fila;
    protected int columna;



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

    public int calcularDistancia(int filaObjetivo,int columnaObjetivo){
       return  (Math.abs(this.fila - filaObjetivo) + Math.abs(this.columna - columnaObjetivo));
    }

}
