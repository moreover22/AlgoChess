package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Vida implements Parseable {
    private float vidaInicial;
    private float vidaActual;
    private PropertyChangeSupport notificador;


    public Vida(float vidaInicial) {
        notificador = new PropertyChangeSupport(this);
        this.vidaInicial = vidaInicial;
        this.vidaActual = vidaInicial;
    }

    public float vidaActual() {
        return vidaActual;
    }

    public boolean tieneVida() {
        return vidaActual > 0;
    }

    public void recibirDanioPorcentual(int porcentaje) {
        float vidaAntesDelCambio = vidaActual;
        vidaActual -= vidaInicial * porcentaje / 100;
        notificador.firePropertyChange("vida", vidaAntesDelCambio, vidaActual);
    }

    public void recibirHabilidad(float cantidad) {
        float vidaAntesDelCambio = vidaActual;

        vidaActual = cantidad;
        if(vidaActual > vidaInicial) {
            vidaActual = vidaInicial;
        }
        notificador.firePropertyChange("vida", vidaAntesDelCambio, vidaActual);
    }


    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("vida_inicial", vidaInicial);
        parser.put("vida_actual", vidaActual);

        return parser;
    }

    public void agregarChangeListener(PropertyChangeListener notificado) {
        notificador.addPropertyChangeListener(notificado);
    }
}