package fiuba.algo3.algochess.tablero.casillero;

import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.PiezaNula;

public class Casillero implements Aliable {
    private EstadoCasillero estado;
    private CasilleroAlianza alianza;
    private Pieza pieza;

    public Casillero() {
        estado = new EstadoCasilleroVacio();
        alianza = new CasilleroAliado();
        pieza = new PiezaNula();
    }

    public void ocupar(Pieza pieza) throws ColocarEnCasilleroOcupadoException {
        this.pieza = pieza;
        estado = estado.ocupar();
    }


    public void posicionar(Pieza pieza) throws ColocarEnCasilleroEnemigoException, ColocarEnCasilleroOcupadoException {
        alianza.posicionar(estado);
        ocupar(pieza);
    }

    public Pieza sacar() throws VaciarCasilleroVacioException {
        Pieza piezaASacar = pieza;
        estado = estado.vaciar();
        pieza = new PiezaNula();
        return piezaASacar;
    }
    public Pieza getPieza(){return this.pieza;}

    @Override
    public void cambiarAlianza() {
        alianza = alianza.cambiar();
    }

}
