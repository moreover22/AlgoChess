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
    private Vida vida;
    private int coste;

    protected Posicion posicion;
    protected Habilidad habilidad;
    protected Movimiento movimiento;
    protected PiezaAlianza alianza;

    protected Pieza(float vidaInicial, int coste) {
        this.vida = new Vida(vidaInicial);
        this.coste = coste;
        this.alianza = new PiezaAliada();
        this.movimiento = new Movimiento(new AlcanceInmediato());
    }

    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        habilidad.usarCon(objetivo, posicion);
    }

    public void posicionar(Tablero tablero, Posicion posicion) throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        tablero.posicionar(posicion, this);
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void recibirCuracion(float curacion) throws CuracionAEnemigoException {
        vida.recibirCuracion(curacion, alianza);
    }

    public void recibirDanio(float danio) throws AtaqueAAliadoException {
        vida.recibirDanio(danio, alianza);
    }

    public float getVida() {
        return vida.vidaActual();
    }

    public boolean estaViva(){
        return vida.tieneVida();
    }

    public int descontarCoste(int puntos){
        return puntos - coste;
    }

    public int agregarCoste(int puntos){
        return puntos + coste;
    }

    public int contarAliadosDeCaballeria(int cantidadAliadosCaballeria) {
        return cantidadAliadosCaballeria;
    }

    public int contarEnemigo(int cantidadEnemigos) {
        return alianza.contarEnemigo(cantidadEnemigos);
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