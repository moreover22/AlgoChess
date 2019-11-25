package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.CuracionAEnemigoException;
import fiuba.algo3.algochess.pieza.habilidad.Habilidad;

public class PiezaEnemiga implements PiezaAlianza {
    @Override
    public PiezaAlianza cambiar() {
        return new PiezaAliada();
    }

    /*@Override
    public float recibirDanio(float vida, float danio) {
        return vida - danio;
    }

    @Override
    public float recibirCuracion(float vida, float curacion) throws CuracionAEnemigoException {
        throw new CuracionAEnemigoException();
    }*/

    @Override
    public int contarAliado(int cantidadAliados) {
        return cantidadAliados;
    }

    @Override
    public int contarEnemigo(int cantidadEnemigos) {
        return cantidadEnemigos + 1;
    }
    @Override
    public float recibirHabilidad(float cantidad, Habilidad habilidad, Pieza pieza){
        return habilidad.aplicarHabilidad(cantidad, pieza);
    }
}
