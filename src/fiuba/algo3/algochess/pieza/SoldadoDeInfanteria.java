package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaPesada;
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
    public Movible seleccionarParaMover(Tablero tablero) throws FueraDelTableroException {
        return reclutarSoldados(tablero);
    }


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

            batallon = new Batallon(reclutasHorizontales);
            if (batallon.esValido()) {
                return batallon;
            }


            batallon = new Batallon(reclutasVerticales);
            if (batallon.esValido()) {
                return batallon;
            }

        return this;
    }

    @Override
    protected void enlistarABatallon(List<Pieza> lista){
        alianza.enlistarABatallon(lista,this);
    }
}



































