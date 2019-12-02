package fiuba.algo3.algochess.model.pieza.habilidad;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.PiezaAlianza;
import fiuba.algo3.algochess.model.pieza.habilidad.armas.Arma;

import java.util.Set;

public class Ataque implements Habilidad{
    private Arma arma;

    public Ataque(Arma arma) {
        this.arma = arma;
    }

    public void actualizarArma(Iterable<Pieza> piezas) {
        arma = arma.actualizar(piezas);
    }

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        arma.atacarA(objetivo, desde, this);
    }
    /*
   @Override
    public float aplicarHabilidad(float cantidad, Pieza pieza,Habilidad habilidad){
        return aplicarHabilidad(cantidad,pieza,this);
    }
*/
    public float recibirHabilidad(float cantidad,Habilidad habilidad,Pieza pieza,PiezaAlianza alianza) throws AtaqueAAliadoException {
        return habilidad.recibirHabilidad(cantidad,this,pieza,alianza);
    }

    public float recibirHabilidad(float cantidad, Ataque ataque, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException {
        return alianza.recibirAtaque(cantidad,pieza);

    }


    public float recibirHabilidad(float cantidad, Curacion curacion, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException {
        return -1;//No deberia ocurrir nunca
    }

    public void aplicarEnGrupo(Set<Pieza> objetivos) {
        arma.atacarAGrupo(objetivos);
    }


    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("tipo", "ataque");
        parser.put("detalle", arma.parsear());
        return parser;
    }
}











