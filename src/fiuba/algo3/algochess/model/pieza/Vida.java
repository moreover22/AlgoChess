package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionAEnemigoException;

public class Vida implements Parseable {
    private float vidaInicial;
    private float vidaActual;

    public Vida(float vidaInicial) {
        this.vidaInicial = vidaInicial;
        this.vidaActual = vidaInicial;
    }

    public float vidaActual() {
        return vidaActual;
    }

    public void aumentar(float cantidad, PiezaAlianza alianza) throws CuracionAEnemigoException {
        vidaActual = alianza.recibirCuracion(vidaActual, cantidad);
        if(vidaActual > vidaInicial) {
            vidaActual = vidaInicial;
        }
    }

    public void reducir(float cantidad, PiezaAlianza alianza) throws AtaqueAAliadoException {
        vidaActual = alianza.recibirDanio(vidaActual, cantidad);
    }

    public boolean tieneVida() {
        return vidaActual > 0;
    }

    public void recibirDanioPorcentual(int porcentaje) {
        vidaActual -= vidaInicial * porcentaje / 100;
    }
    @Override
    public ParserObjeto getEstado() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("vida_inicial", vidaInicial);
        parser.put("vida_actual", vidaActual);

        return parser;
    }
}
