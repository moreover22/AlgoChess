package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.alianza.EstadoAliado;
import fiuba.algo3.algochess.alianza.EstadoAlianza;
import fiuba.algo3.algochess.pieza.habilidad.Habilidad;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.Movimiento;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;

public abstract class Pieza /*implements Aliable*/ {
    private float vidaInicial;
    private float vida;
    private int coste;

    private Posicion posicion;
    private Habilidad habilidad;
    private Movimiento movimiento;
    private EstadoAlianza alianza;

    protected Pieza(float vidaInicial) {
        this.vidaInicial = vidaInicial;
        this.vida = vidaInicial;
        this.movimiento = new Movimiento();
        this.alianza = new EstadoAliado();
    }

    protected void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public void usarHabilidadEn(Pieza objetivo) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        habilidad.usarCon(objetivo, posicion);
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
        vida = alianza.recibirCuracion(this.vida,curacion);

        if(vida  > vidaInicial){
            vida = vidaInicial;
        }
    }

    public void descontarDanio(float danio) {
        vida = alianza.descontarDanio(this.vida,danio);
    }

    public boolean estaViva(){
        return vida > 0;
    }

    public void mover(Direccion direccion) throws MovimientoFueraDeAlcanceException {
        this.posicion = movimiento.mover(posicion, direccion);
    }

   /*@Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }
    */
}
