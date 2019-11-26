package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class CreadorBatallon {
    private Tablero tablero;
    private Pieza pieza;
    private Movible movible;

    public CreadorBatallon(Pieza pieza, Tablero tablero) throws FueraDelTableroException {
        this.tablero = tablero;
        this.pieza = pieza;
        this.movible = pieza;
        List<CasoBatallon> posiblesBatallones = new ArrayList<>();
        posiblesBatallones.add(CasoBatallon.horizontal());
        posiblesBatallones.add(CasoBatallon.vertical());
        probarCasos(posiblesBatallones);
    }

    public void setMovible(Movible movible) {
        this.movible = movible;
    }

    public Movible getResultado() {
        return movible;
    }

    private void probarCasos(List<CasoBatallon> posiblesBatallones) throws FueraDelTableroException {
        for (CasoBatallon casoBatallon : posiblesBatallones) {
            casoBatallon.probarCaso(tablero, pieza, this);
        }
    }
}
