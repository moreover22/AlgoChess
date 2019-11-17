package fiuba.algo3.algochess.pieza;

import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;
import fiuba.algo3.algochess.pieza.habilidad.Ataque;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.habilidad.armas.ArcoYFlecha;
import fiuba.algo3.algochess.pieza.habilidad.armas.EspadaLiviana;
import fiuba.algo3.algochess.tablero.Tablero;

public class Jinete extends Pieza {
    public Jinete() {
        super(100);
        this.setCoste(3);
        this.setHabilidad(new Ataque(new EspadaLiviana()));
    }

    @Override
    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        Iterable<Pieza> vecinos = tablero.piezasDentroDe(new AlcanceCercano(), this.posicion);
        int cantidadAliadosDeCaballeria = 0;
        int cantidadEnemigos = 0;
        int total = 0;
        for(Pieza pieza : vecinos) {
            cantidadAliadosDeCaballeria = pieza.contarAliadosDeCaballeria(cantidadAliadosDeCaballeria);
            cantidadEnemigos = pieza.contarEnemigo(cantidadEnemigos);
            total++;
        }
        if (cantidadAliadosDeCaballeria > 0 || cantidadEnemigos == 0) {
            habilidad = new Ataque(new ArcoYFlecha());
        }
        int cantidadAliados = total - cantidadEnemigos;
        if (cantidadAliados == 0 && cantidadEnemigos > 0) {
            habilidad = new Ataque(new EspadaLiviana());
        }

        super.usarHabilidadEn(tablero, objetivo);
    }
}