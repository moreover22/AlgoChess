package fiuba.algo3.algochess.pieza;

public class Jinete extends Pieza {

    public Jinete(int fila, int columna) {
        super(fila, columna);
        this.puntosDeVida = 100;
        this.coste = 3;
    }

    @Override
    public void usarHabilidadEn(Pieza objetivo) {

    }

}
