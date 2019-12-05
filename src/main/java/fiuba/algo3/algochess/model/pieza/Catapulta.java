package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.alcance.AlcanceInmediato;
import fiuba.algo3.algochess.model.pieza.alcance.AlcanceNulo;
import fiuba.algo3.algochess.model.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionACatapultaException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.pieza.habilidad.armas.Proyectil;
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
    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, CuracionACatapultaException {

        habilidad.usarCon(objetivo, posicion);
        if (!objetivo.estaViva()) tablero.vaciar(objetivo.getPosicion());

        Set<Pieza> objetivos = buscarObjetivos(tablero, objetivo);
        habilidad.aplicarEnGrupo(objetivos);
        for(Pieza pieza : objetivos){
            if(!pieza.estaViva()) tablero.vaciar(pieza.getPosicion());
        }
    }

    private Set<Pieza> buscarObjetivos(Tablero tablero, Pieza objetivo) {
        Set<Pieza> conjuntoPiezas = ConcurrentHashMap.newKeySet();
        agregarVecinos(conjuntoPiezas, tablero, objetivo);
        conjuntoPiezas.remove(objetivo);
        return conjuntoPiezas;
    }

    private void agregarVecinos(Set<Pieza> conjunto, Tablero tablero, Pieza origen) {
        if (conjunto.contains(origen)) return;
        conjunto.add(origen);
        Iterable<Pieza> vecinosPiezaActual = tablero.piezasDentroDe(new AlcanceInmediato(), origen.getPosicion());
        for (Pieza vecino : vecinosPiezaActual) {
            if (!(vecino instanceof PiezaNula)) {
                agregarVecinos(conjunto, tablero, vecino);
            }
        }
    }
}