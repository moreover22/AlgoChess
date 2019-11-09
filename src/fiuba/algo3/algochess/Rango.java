package fiuba.algo3.algochess;

public class Rango {
    private int minimoX;
    private int minimoY;
    private int maximoX;
    private int maximoY;
    public Rango(int minimoX, int minimoY, int maximoX, int maximoY) {
        this.minimoX = minimoX;
        this.minimoY = minimoY;
        this.maximoX = maximoX;
        this.maximoY = maximoY;
    }

    public boolean estaDentro(int x, int y) {
        return dentroDeRango(x, minimoX, maximoX) && dentroDeRango(y, minimoY, maximoY);
    }


    private boolean dentroDeRango(int valor, int valorMinimo, int valorMaximo) {
        return (valor >= valorMinimo) && (valor < valorMaximo);
    }
}
