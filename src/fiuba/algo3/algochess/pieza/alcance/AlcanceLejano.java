package fiuba.algo3.algochess.pieza.alcance;

public class AlcanceLejano extends Alcance {
    public AlcanceLejano() {
        this.setDistanciaMinima(6);  //ESTO ESTABA EN 1 PERO LA DISTANCIA MINIMA PARA UN ATAQUE LEJANO ES 6 POR ESO LO CAMBIE//
     }

    @Override
    public boolean llegoDistanciaMaxima(int distancia) {
        return true;
    }
}



























