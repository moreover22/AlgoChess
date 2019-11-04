package fiuba.algo3.algochess.pieza;

public class SoldadoDeInfanteria extends Pieza {

    public SoldadoDeInfanteria(int fila, int columna){
        super(fila, columna);
        this.puntosDeVida = 100;
        this.coste = 1;
    }

    @Override
    public void usarHabilidadEn(Pieza objetivo) {

    }

}
