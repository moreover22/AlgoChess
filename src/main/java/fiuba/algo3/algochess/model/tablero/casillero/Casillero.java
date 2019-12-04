package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.Aliable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.tablero.Tablero;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Casillero implements Aliable, Parseable {
    private EstadoCasillero estado;
    private CasilleroAlianza alianza;
    private PropertyChangeSupport notificador;

    public Casillero() {
        notificador = new PropertyChangeSupport(this);
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
    }

    public void posicionar(Pieza pieza) throws PosicionarEnCasilleroEnemigoException {
        Pieza piezaVieja = estado.getPieza();
        alianza.posicionar();
        estado = estado.posicionar(pieza);
        notificador.firePropertyChange("pieza", piezaVieja, estado.getPieza());
    }

    public void ocupar(Pieza pieza, Tablero tablero) throws FueraDelTableroException {
        Pieza piezaVieja = estado.getPieza();
        estado = estado.ocupar(pieza, tablero);
        notificador.firePropertyChange("pieza", piezaVieja, estado.getPieza());
    }

    public void vaciar() {
        Pieza piezaVieja = estado.getPieza();
        estado = estado.vaciar();
        notificador.firePropertyChange("pieza", piezaVieja, estado.getPieza());
    }

    public Pieza getPieza() {
        return estado.getPieza();
    }

    @Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }

    public void aplicarDanioTerritorio() {
        Pieza pieza = estado.getPieza();
        alianza.aplicarDanioTerritorio(pieza);
        if (! pieza.estaViva() ) {
            this.vaciar();
        }
    }

    public void agregarChangeListener(PropertyChangeListener notificado) {
        notificador.addPropertyChangeListener(notificado);
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parseado = new ParserObjeto();
        parseado.put("alianza", alianza.getAlianza());
        parseado.put("estado", estado.parsear());
        return parseado;
    }
}