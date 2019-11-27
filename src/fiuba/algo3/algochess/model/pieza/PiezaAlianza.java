package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.model.pieza.habilidad.Habilidad;

import java.util.List;

public interface PiezaAlianza {
    PiezaAlianza cambiar();
    int contarAliado(int cantidadAliados);
    int contarEnemigo(int cantidadEnemigos);

    float recibirHabilidad(float cantidad, Habilidad habilidad, Pieza pieza);
    void enlistarABatallon(List<Pieza> lista, Pieza pieza);

    String getAlianza();
}
