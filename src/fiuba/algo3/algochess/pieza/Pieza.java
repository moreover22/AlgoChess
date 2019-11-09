package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.pieza.alcance.Alcance;
import fiuba.algo3.algochess.pieza.habilidad.*;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.Movimiento;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.tablero.casillero.VaciarCasilleroVacioException;

public abstract class Pieza implements Aliable {
    private float vidaInicial;
    private float vida;
    private int coste;

    private Posicion posicion;
    private Habilidad habilidad;
    private Movimiento movimiento;
    private PiezaAlianza alianza;

    protected Pieza(float vidaInicial) {
        this.vidaInicial = vidaInicial;
        this.vida = vidaInicial;
        this.movimiento = new Movimiento();
        this.alianza = new PiezaAliada();
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

    // TODO ver si hay que sacarlo
    public int getCoste() {
        return coste;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public void recibirCuracion(float curacion) throws CuracionAEnemigoException {
        if(vida + curacion > vidaInicial) {
            curacion = vidaInicial - vida;
        }
        vida = alianza.recibirCuracion(vida, curacion);
    }
    public void recibirCuracion(float curacion, Posicion desde, Alcance alcance) throws CuracionAEnemigoException, HabilidadFueraDeAlcanceException {
        verificarAlcance(desde, alcance);
        recibirCuracion(curacion);
    }

    public void recibirDanio(float danio) throws AtaqueAAliadoException {
        vida = alianza.recibirDanio(vida, danio);
    }

    public void recibirDanio(float danio, Posicion desde, Alcance alcance) throws AtaqueAAliadoException, HabilidadFueraDeAlcanceException {
        verificarAlcance(desde, alcance);
        recibirDanio(danio);
    }

    private void verificarAlcance(Posicion desde, Alcance alcance) throws HabilidadFueraDeAlcanceException{
        if (!alcance.llegoA(desde, posicion)) {
            throw new HabilidadFueraDeAlcanceException();
        }
    }

    public boolean estaViva(){
        return vida > 0;
    }

    public Posicion mover(Direccion direccion) throws MovimientoFueraDeAlcanceException, FueraDelTableroException, VaciarCasilleroVacioException, ColocarEnCasilleroOcupadoException {
        this.posicion = movimiento.mover(posicion, direccion);
        return posicion;
    }

    @Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }
}
