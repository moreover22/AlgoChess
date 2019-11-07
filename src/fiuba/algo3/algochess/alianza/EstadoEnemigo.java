package fiuba.algo3.algochess.alianza;

public class EstadoEnemigo implements EstadoAlianza {
    @Override
    public EstadoAlianza cambiar() {
        return new EstadoAliado();
    }

    @Override
    public boolean esAliado() {
        return false;
    }
}
