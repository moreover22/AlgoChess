package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaPesada;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class SoldadoDeInfanteria extends Pieza {

    public SoldadoDeInfanteria(){
        super(100, 1);
        habilidad = new Ataque(new EspadaPesada());

    }

    @Override
    public int contarAliadosDeCaballeria(int cantidadAliadosCaballeria) {
        return alianza.contarAliado(cantidadAliadosCaballeria);
    }

    @Override
    public void mover(Tablero tablero, Direccion direccion) throws MovimientoFueraDeAlcanceException, FueraDelTableroException {

        reclutarSoldados(tablero).mover(tablero,direccion);

    }
   /* public Movible formarBatallonVertical(Tablero tablero) {
        Iterable<Pieza> vecinos = tablero.buscarVecinosVertical(this);
        return new Batallon(this, vecinos);
    }

    public Movible formarBatallonHorizontal(Tablero tablero, Pieza pieza) {
        Iterable<Pieza> vecinos = tablero.buscarVecinosHorizontal(pieza);
        return new Batallon(pieza, vecinos);
    }
*/

    public Movible reclutarSoldados(Tablero tablero) throws FueraDelTableroException {
        Iterable<Pieza> vecinosHorizontales = tablero.buscarVecinosHorizontal(this);
        Iterable<Pieza> vecinosVerticales = tablero.buscarVecinosVertical(this);
        List<Pieza> reclutasVerticales = new ArrayList<>();
        List<Pieza> reclutasHorizontales = new ArrayList<>();

        for(Pieza vecino:vecinosHorizontales){
            vecino.enlistarABatallon(reclutasHorizontales);
        }

        for(Pieza vecino:vecinosVerticales){
            vecino.enlistarABatallon(reclutasVerticales);
        }

        Batallon batallon;
        for (Pieza candidato : reclutasHorizontales) {
            batallon = new Batallon(candidato,tablero.buscarVecinosHorizontal(candidato));
            if (batallon.esValido()) {
                return batallon;
            }
        }
        for (Pieza candidato : reclutasVerticales) {
            batallon = new Batallon(candidato,tablero.buscarVecinosVertical(candidato));
            if (batallon.esValido()) {
                return batallon;
            }
        }
        return this;
    }

    @Override
    protected void enlistarABatallon(List<Pieza> lista){
        alianza.enlistarABatallon(lista,this);
    }
}



































