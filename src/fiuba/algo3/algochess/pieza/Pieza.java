package fiuba.algo3.algochess.pieza;

public class Pieza {

    private float puntosDeVida;
    private int coste;

    public void recibirDanio(float danio){
        puntosDeVida -= danio;
    }

    public int descontarCoste(int monto ){
        return (monto - coste) ;
    }

}
