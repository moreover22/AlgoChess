package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.alcance.AlcanceNulo;
import fiuba.algo3.algochess.model.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.pieza.habilidad.armas.Proyectil;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.Movimiento;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Catapulta extends Pieza {
    public Catapulta() {
        super(50, 5);
        habilidad = new Ataque(new Proyectil());
        movimiento = new Movimiento(new AlcanceNulo());
        tipoPieza = "catapulta";
    }

    @Override
    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {

        habilidad.usarCon(objetivo, posicion);
        if (!objetivo.estaViva()) tablero.sacar(objetivo.getPosicion());

        Set<Pieza> objetivos = buscarObjetivos(tablero,objetivo);
        habilidad.aplicarEnGrupo(objetivos);
        for(Pieza pieza : objetivos){
            if(!pieza.estaViva()) tablero.sacar(objetivo.getPosicion());
        }

    }

    private Set<Pieza> buscarObjetivos(Tablero tablero, Pieza objetivo) throws FueraDelTableroException {

        Set<Pieza> conjuntoPiezas = ConcurrentHashMap.newKeySet();

        List<Direccion> direcciones = new ArrayList<Direccion>();
        direcciones.add(Direccion.derecha());
        direcciones.add(Direccion.izquierda());
        direcciones.add(Direccion.arriba());
        direcciones.add(Direccion.abajo());
        direcciones.add(Direccion.derechaArriba());
        direcciones.add(Direccion.derechaAbajo());
        direcciones.add(Direccion.izquierdaAbajo());
        direcciones.add(Direccion.izquierdaArriba());




        conjuntoPiezas.addAll(tablero.getVecinos(objetivo.getPosicion(), direcciones));
        Iterator<Pieza> iter =  conjuntoPiezas.iterator();

        while(iter.hasNext()){
            Pieza pieza = iter.next();
            if(!(pieza instanceof PiezaNula)) {
                conjuntoPiezas.addAll(tablero.getVecinos(pieza.getPosicion(), direcciones));
            }
        }
        conjuntoPiezas.remove(objetivo);
        return conjuntoPiezas;
    }
}















