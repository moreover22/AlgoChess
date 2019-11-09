package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.CuracionAEnemigoException;

public class PiezaEnemiga implements PiezaAlianza {
    @Override
    public PiezaAlianza cambiar() {
        return new PiezaAliada();
    }

    @Override
    public float recibirDanio(float vida, float danio) {
        return vida - danio;
    }

    @Override
    public float recibirCuracion(float vida, float curacion) throws CuracionAEnemigoException {
        throw new CuracionAEnemigoException();
    }
}
