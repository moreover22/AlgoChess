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

        List<Direccion> direcciones = new ArrayLi…


}















