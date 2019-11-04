package fiuba.algo3.algochess.pieza;

public class Curandero extends Pieza {

    public Curandero (int fila, int columna) {
        super(fila, columna);
        this.puntosDeVida = 75;
        this.coste = 2;
    }

    @Override
    public void usarHabilidadEn(Pieza objetivo) {

    }

}
