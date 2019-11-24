package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaPesada;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
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

    private Movible reclutarSoldados(Tablero tablero) throws FueraDelTableroException {
        CreadorBatallon creadorBatallon = new CreadorBatallon(this, tablero);

        return creadorBatallon.getResultado();
    }

    @Override
    protected void enlistarABatallon(List<Pieza> lista){
        alianza.enlistarABatallon(lista,this);
    }
}



































