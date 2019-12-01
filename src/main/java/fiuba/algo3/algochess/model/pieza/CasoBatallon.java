package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class CasoBatallon {
    private List<Direccion> direcciones;
    private static CasoBatallon horizontal;
    private static CasoBatallon vertical;

    static {
        horizontal = new CasoBatallon();
        horizontal.addDireccion(Direccion.derecha());
        horizontal.addDireccion(Direccion.izquierda());
        vertical = new CasoBatallon();
        vertical.addDireccion(Direccion.arriba());
        vertical.addDireccion(Direccion.abajo());
    }

    private CasoBatallon() {
        direcciones = new ArrayList<>();
    }

    private void addDireccion(Direccion direccion) {
        direcciones.add(direccion);
    }

    public static CasoBatallon horizontal() {
        return horizontal;
    }
    public static CasoBatallon vertical() {
        return vertical;
    }

    public void probarCaso(Tablero tablero, Pieza pieza, CreadorBatallon creador) throws FueraDelTableroException {
        List<Pieza> vecinosEnlistados = getVecinosEnlistados(tablero, pieza.getPosicion());

        if (vecinosEnlistados.size() == 2) {
            vecinosEnlistados.add(pieza);
            Movible batallon = new Batallon(vecinosEnlistados);
            creador.setMovible(batallon);
        } else if (vecinosEnlistados.size() == 1) {
            Pieza vecino = vecinosEnlistados.get(0);
            List<Pieza> vecinosReclutados = getVecinosEnlistados(tablero, vecino.getPosicion());
            vecinosReclutados.add(vecino);
            Batallon batallon = new Batallon(vecinosReclutados);
            if (batallon.esValido())
                creador.setMovible(batallon);
        }
    }

    private List<Pieza> getVecinosEnlistados(Tablero tablero, Posicion posicion) throws FueraDelTableroException {
        Iterable<Pieza> vecinos = tablero.getVecinos(posicion, direcciones);
        List<Pieza> vecinosEnlistados = new ArrayList<>();
        for (Pieza vecino : vecinos)
            vecino.enlistarABatallon(vecinosEnlistados);

        return vecinosEnlistados;
    }
}
