package fiuba.algo3.algochess.pieza;

public abstract class Alcance {

    public abstract boolean llego (int filaOrigen,int columnaOrigen,int filaDestino,int columnaDestino);

    public int calcularDistancia(int filaOrigen, int columnaOrigen,int filaObjetivo,int columnaObjetivo){
        return (int) Math.sqrt(Math.pow(filaObjetivo-filaOrigen,2) + Math.pow(columnaObjetivo-columnaOrigen,2));
    }
}





















