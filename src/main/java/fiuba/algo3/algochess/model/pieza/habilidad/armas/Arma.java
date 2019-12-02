package fiuba.algo3.algochess.model.pieza.habilidad.armas;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.alcance.Alcance;
import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionAEnemigoException;
import fiuba.algo3.algochess.model.pieza.habilidad.Habilidad;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;

import java.util.Set;

public abstract class Arma implements Parseable {

    protected Alcance alcance;
    protected float danio;

    protected Arma(float danio, Alcance alcance) {
        this.danio = danio;
        this.alcance = alcance;
    }

    public void atacarA(Pieza objetivo, Posicion desde, Habilidad habilidad) throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, CuracionAEnemigoException {
        if (! alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.recibirHabilidad(habilidad,danio);
    }

    public Arma actualizar(Iterable<Pieza> vecinos) {
        int cantidadAliados = 0;
        int cantidadEnemigos = 0;
        for(Pieza pieza : vecinos) {
            cantidadAliados = contarAliado(pieza, cantidadAliados);
            cantidadEnemigos = contarEnemigo(pieza, cantidadEnemigos);
        }

        if (criterioDeActualizacion(cantidadAliados, cantidadEnemigos)) {
            return armaActualizada();
        }
        return this;
    }

    protected int contarAliado(Pieza pieza, int cantidadAliados) {
        return pieza.contarAliado(cantidadAliados);
    }

    protected int contarEnemigo(Pieza pieza, int cantidadEnemigos) {
        return pieza.contarEnemigo(cantidadEnemigos);
    }

    protected boolean criterioDeActualizacion(int cantidadAliados, int cantidadEnemigos) {
        return false;
    }

    protected Arma armaActualizada() {
        return this;
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("cantidad", danio);
        parser.put("alcance", alcance);
        return parser;
    }

    public void atacarAGrupo(Set<Pieza> objetivos) {
    }
}















