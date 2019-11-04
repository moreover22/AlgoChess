package fiuba.algo3.algochess.pieza;

public class Catapulta extends Pieza {

    public Catapulta(int fila, int columna) {
        super(fila, columna);
        this.puntosDeVida = 50;
        this.coste = 5;
    }

    @Override
    public void usarHabilidadEn(Pieza objetivo) {

    }

}
