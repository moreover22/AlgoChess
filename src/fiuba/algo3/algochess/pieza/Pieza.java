package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.alcance.AlcanceInmediato;
import fiuba.algo3.algochess.pieza.habilidad.*;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.Movimiento;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;

public abstract class Pieza implements Aliable, Movible {
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
        this.alianza = new PiezaAliada();
        this.movimiento = new Movimiento(new AlcanceInmediato());
    }

    protected void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    protected void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    protected void setCoste(int coste) {
        this.coste = coste;
    }

    public void usarHabilidadEn(Pieza objetivo) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        habilidad.usarCon(objetivo, posicion);
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void recibirCuracion(float curacion) throws CuracionAEnemigoException {
        if(vida + curacion > vidaInicial) {
            curacion = vidaInicial - vida;
        }
        vida = alianza.recibirCuracion(vida, curacion);
    }

    public void recibirDanio(float danio) throws AtaqueAAliadoException {
        vida = alianza.recibirDanio(vida, danio);
    }

    public boolean estaViva(){
        return vida > 0;
    }

    public int descontarCoste(int puntos){
        return puntos - coste;
    }

    public int agregarCoste(int puntos){
        return puntos + coste;
    }

    public void posicionar(Tablero tablero, Posicion posicion) throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        tablero.posicionar(posicion);
        this.posicion = posicion;
    }

    @Override
    public void mover(Tablero tablero, Direccion direccion) throws MovimientoFueraDeAlcanceException, FueraDelTableroException {
        tablero.sacar(posicion);
        posicion = movimiento.mover(posicion, direccion);
        tablero.ocupar(posicion, this);
    }

    @Override
    public void deshacerMovimiento() {
        posicion = movimiento.deshacerMovimiento();
    }

    @Override
    public void cambiarAlianza(){
        alianza = alianza.cambiar();
    }
}











