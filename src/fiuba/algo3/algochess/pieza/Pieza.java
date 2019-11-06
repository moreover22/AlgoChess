package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.Movimiento;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;

public abstract class Pieza {
    private float vida;
    private int coste;
    private Posicion posicion;

    private Habilidad habilidad;
    private Movimiento movimiento;

    protected Pieza() {
        this.movimiento = new Movimiento();
    }

    protected void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public void usarHabilidadEn(Pieza objetivo) throws HabilidadFueraDeAlcanceException {
        habilidad.usarCon(objetivo, posicion);
    }

    protected void setVida(float vida) {
        this.vida = vida;
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
        this.vida += curacion; //ACA HAY QUE PENSAR COMO HACER PARA QUE AL CURAR UNA UNIDAD NO SUPERE EL MAXIMO DE VIDA DE LA UNIDAD
                                // PORQUE EL ATRIBUTO DE LA PIEZA GUARDA LA VIDA ACTUAL DE LA PIEZA PERO NO LA ORIGINAL
    }

    public void recibirDanio(float danio) {
        this.vida -= danio;
    }

    public void mover(Direccion direccion) throws MovimientoFueraDeAlcanceException {
        this.posicion = movimiento.mover(posicion, direccion);
    }
}
