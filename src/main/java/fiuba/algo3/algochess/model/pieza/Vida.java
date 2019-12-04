package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;

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

    /*public void recibirCuracion(float curacion, PiezaAlianza alianza) throws CuracionAEnemigoException {
        vidaActual = alianza.recibirCuracion(vidaActual, curacion);
        if(vidaActual > vidaInicial) {
            vidaActual = vidaInicial;
        }
    }

    public void recibirDanio(float danio, PiezaAlianza alianza) throws AtaqueAAliadoException {
        vidaActual = alianza.recibirDanio(vidaActual, danio);
    }*/

    public boolean tieneVida() {
        return vidaActual > 0;
    }

    public void recibirDanioPorcentual(int porcentaje) {
        vidaActual -= vidaInicial * porcentaje / 100;
    }

    public void recibirHabilidad(float cantidad){
        //vidaActual = alianza.recibirHabilidad(vidaActual, cantidad,habilidad);
        vidaActual = cantidad;
        if(vidaActual > vidaInicial) {
            vidaActual = vidaInicial;
        }
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("vida_inicial", vidaInicial);
        parser.put("vida_actual", vidaActual);

        return parser;
    }
}


















