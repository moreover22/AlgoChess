package fiuba.algo3.algochess;


public class Tablero {
    private Unidad[][] unidades;
    public Tablero() {
        unidades = new Unidad[20][20];
    }

    public boolean estaVacio() {
        for (Unidad[] fila : unidades) {
            for (Unidad unidad : fila) {
                if (unidad != null) { return false; }
            }
        }
        return true;
    }

    public void colocar(Unidad unidad, int x, int y) {
        unidades[x][y] = unidad;
    }

    public Unidad obtener(int x, int y) {
        return unidades[x][y];
    }

}
