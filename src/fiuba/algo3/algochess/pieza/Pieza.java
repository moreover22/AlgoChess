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

import java.util.ArrayList;
import java.util.List;

public abstract class Pieza implements Aliable, Movible {
    private Vida vida;
    private int coste;

    protected Posicion posicion;
    protected Habilidad habilidad;
    protected Movimiento movimiento;
    protected PiezaAlianza alianza;
    private List<Pieza> piezas;

    static final int PORCENTAJE_DANIO_TERRITORIO = 5;

    protected Pieza(float vidaInicial, int coste) {
        this.vida = new Vida(vidaInicial);
        this.coste = coste;
        this.alianza = new PiezaAliada();
        this.movimiento = new Movimiento(new AlcanceInmediato());
        piezas = new ArrayList<>();
    }

    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, FueraDelTableroException {
        habilidad.usarCon(objetivo, posicion);
        if (!objetivo.estaViva()) tablero.sacar(objetivo.getPosicion());
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
    public void recibirHabilidad(Habilidad habilidad, float cantidad ){
        float vidaActual = alianza.recibirHabilidad(this.getVida(),cantidad,habilidad,this);
        vida.recibirHabilidad(cantidad);
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

    public void agregarVecino(Pieza pieza) {
        piezas.add(pieza);
    }

    public void  recibirDanioTerritorio() {
        vida.recibirDanioPorcentual(PORCENTAJE_DANIO_TERRITORIO);
    }

    public int contarAliadosDeCaballeria(int cantidadAliadosCaballeria) {
        return cantidadAliadosCaballeria;
    }

    public int contarEnemigo(int cantidadEnemigos) {
        return alianza.contarEnemigo(cantidadEnemigos);
    }

    public int contarAliado(int cantidadAliados) {
        return alianza.contarAliado(cantidadAliados);
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