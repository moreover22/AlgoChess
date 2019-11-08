package fiuba.algo3.algochess.alianza;

public interface EstadoAlianza {
    EstadoAlianza cambiar();
    float descontarDanio(float vida , float danio);
    float recibirCuracion(float vida,float curacion);
}
