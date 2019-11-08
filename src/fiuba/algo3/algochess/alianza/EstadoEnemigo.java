package fiuba.algo3.algochess.alianza;

public class EstadoEnemigo implements EstadoAlianza {
    @Override
    public EstadoAlianza cambiar() {
        return new EstadoAliado();
    }

    @Override
    public float descontarDanio(float vida, float danio){
        return (vida -danio);
    }

    @Override
    public float recibirCuracion(float vida,float curacion){
        return vida;
    }
}
