package fiuba.algo3.algochess.model.tablero.casillero;

import fiuba.algo3.algochess.model.Aliable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.tablero.Tablero;

public class Casillero implements Aliable, Parseable {
    private EstadoCasillero estado;
    private CasilleroAlianza alianza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
    }

    public void posicionar(Pieza pieza) throws PosicionarEnCasilleroEnemigoException {
        alianza.posicionar();
        estado = estado.posicionar(pieza);
    }

    public void ocupar(Pieza pieza, Tablero tablero) throws FueraDelTableroException {
        estado = estado.ocupar(pieza, tablero);
    }

    public void vaciar() {
        estado = estado.vaciar();
    }

    public Pieza getPieza() {
        return estado.getPieza();
    }
    @Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }

    public void aplicarDanioTerritorio(Pieza pieza) {
        alianza.aplicarDanioTerritorio(pieza);
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parseado = new ParserObjeto();
        parseado.put("alianza", alianza.getAlianza());
        parseado.put("estado", estado.parsear());
        return parseado;
    }
}