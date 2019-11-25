package fiuba.algo3.algochess.pieza;

public class PiezaNula extends Pieza{
    public PiezaNula() {
        super(0, 0);
    }

    @Override
    public int contarEnemigo(int cantidadEnemigos) {
        return cantidadEnemigos;
    }

    @Override
    public int contarAliado(int cantidadAliados) {
        return cantidadAliados;
    }
}
