package fiuba.algo3.algochess.alianza;

public class EstadoAliado implements EstadoAlianza {
    @Override
    public EstadoAlianza cambiar() {
        return new EstadoEnemigo();
    }

    @Override
    public float descontarDanio(float vida, float danio){
        return (vida);
    }

    @Override
    public float recibirCuracion(float vida,float curacion){
        return (vida + curacion);
    }
}
