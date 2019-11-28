package fiuba.algo3.algochess.model.pieza.habilidad;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.PiezaAlianza;
import fiuba.algo3.algochess.model.pieza.alcance.Alcance;
import fiuba.algo3.algochess.model.pieza.alcance.AlcanceCercano;

public class Curacion implements Habilidad {
    private Alcance alcance;
    private float curacion;

    public Curacion(float curacion) {
        this.curacion = curacion;
        this.alcance = new AlcanceCercano();
    }
    @Override
    public float aplicarHabilidad(float cantidad, Pieza pieza,Habilidad habilidad){
        return aplicarHabilidad(cantidad,pieza,this);

    }

    public float recibirHabilidad(float cantidad, Habilidad habilidad, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException, CuracionAEnemigoException {
        return habilidad.recibirHabilidad(cantidad,this,pieza,alianza);
    }

    public float recibirHabilidad(float cantidad, Ataque ataque, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException {
        return -1;//No deberia ocurrir nunca
    }

    public float recibirHabilidad(float cantidad, Curacion curacion, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException, CuracionAEnemigoException {
        return alianza.recibirCuracion(cantidad,pieza);
    }

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        if (! alcance.llegoA(desde, objetivo.getPosicion())) {
            throw new HabilidadFueraDeAlcanceException();
        }
        objetivo.recibirHabilidad(this,curacion);

    }

    @Override
    public ParserObjeto getEstado() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("tipo", "curacion");
        ParserObjeto parserInfo = new ParserObjeto();
        parserInfo.put("alcance", alcance);
        parserInfo.put("cantidad", curacion);
        parser.put("detalle", parserInfo);
        return parser;
    }
}