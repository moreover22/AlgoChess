package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;

public class PiezaAliada implements PiezaAlianza {
    @Override
    public PiezaAlianza cambiar() {
        return new PiezaEnemiga();
    }

    @Override
    public float recibirDanio(float vida, float danio) throws AtaqueAAliadoException {
        throw new AtaqueAAliadoException();
    }

    @Override
    public float recibirCuracion(float vida, float curacion) {
        return vida + curacion;
    }

}
