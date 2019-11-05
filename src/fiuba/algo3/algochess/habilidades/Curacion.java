package fiuba.algo3.algochess.habilidades;

import fiuba.algo3.algochess.pieza.Pieza;

public class Curacion implements Habilidad {
    private float curacion;

    public Curacion(float curacion) {
        this.curacion = curacion;
    }

    @Override
    public void usarCon(Pieza pieza) {
        //pieza.recibirCuracion(curacion);
    }
}
