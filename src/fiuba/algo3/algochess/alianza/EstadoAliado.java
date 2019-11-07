package fiuba.algo3.algochess.alianza;

public class EstadoAliado implements EstadoAlianza {
    @Override
    public EstadoAlianza cambiar() {
        return new EstadoEnemigo();
    }

    @Override
    public boolean esAliado() {
        return true;
    }
}
