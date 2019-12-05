package fiuba.algo3.algochess.model.pieza.habilidad;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Catapulta;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.PiezaAlianza;
import fiuba.algo3.algochess.model.pieza.alcance.Alcance;
import fiuba.algo3.algochess.model.pieza.alcance.AlcanceCercano;

import java.util.Set;

public class Curacion implements Habilidad {
    private Alcance alcance;
    private float curacion;

    public Curacion(float curacion) {
        this.curacion = curacion;
        this.alcance = new AlcanceCercano();
    }
    public float recibirHabilidad(float cantidad, Habilidad habilidad, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException, CuracionAEnemigoException, CuracionACatapultaException {
        return habilidad.recibirHabilidad(cantidad,this,pieza,alianza);
    }

    public float recibirHabilidad(float cantidad, Ataque ataque, Pieza pieza, PiezaAlianza alianza) {
        return -1;//No deberia ocurrir nunca
    }

    public float recibirHabilidad(float cantidad, Curacion curacion, Pieza pieza, PiezaAlianza alianza) throws CuracionAEnemigoException, CuracionACatapultaException {

        if(pieza instanceof Catapulta) throw new CuracionACatapultaException();

        return alianza.recibirCuracion(cantidad,pieza);
    }

    @Override
    public void aplicarEnGrupo(Set<Pieza> objetivos) {

    }

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, CuracionACatapultaException {
        if (! alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.recibirHabilidad(this,curacion);

    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("tipo", "curacion");
        ParserObjeto parserInfo = new ParserObjeto();
        parserInfo.put("alcance", alcance);
        parserInfo.put("cantidad", curacion);
        parser.put("detalle", parserInfo);
        return parser;
    }
}