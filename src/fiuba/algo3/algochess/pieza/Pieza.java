package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.movimiento.Direccion;
import fiuba.algo3.algochess.movimiento.Movimiento;

public abstract class Pieza {
    private float puntosDeVida;
    private int coste;
    private Posicion posicion;

    protected Habilidad habilidad;
    protected Movimiento movimiento;

    public void usarHabilidadEn(Pieza objetivo) throws HabilidadFueraDeAlcanceException {
        habilidad.usarCon(objetivo, posicion);
    }

    protected void setPuntosDeVida(float puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    protected void setCoste(int coste) {
        this.coste = coste;
    }

    public int getCoste() {
        return coste;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void recibirCuracion(float curacion) {
        this.puntosDeVida += curacion;
    }

    public void recibirDanio(float danio){
        this.puntosDeVida -= danio;
    }

    public void mover(Direccion direccion){
        movimiento.mover(posicion, direccion);
    }
}
