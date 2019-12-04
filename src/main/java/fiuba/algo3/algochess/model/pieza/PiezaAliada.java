package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;

import java.util.List;

public class PiezaAliada implements PiezaAlianza {
    @Override
    public PiezaAlianza cambiar() {
        return new PiezaEnemiga();
    }

    /*@Override
    public float recibirDanio(float vida, float danio) throws AtaqueAAliadoException {
        throw new AtaqueAAliadoException();
    }

    @Override
    public float recibirCuracion(float vida, float curacion) {
        return vida + curacion;
    }*/

    @Override
    public int contarAliado(int cantidadAliados) {
        return cantidadAliados + 1;
    }

    @Override
    public int contarEnemigo(int cantidadEnemigos) {
        return cantidadEnemigos;
    }

    @Override
    public float recibirAtaque(float cantidad, Pieza pieza) throws AtaqueAAliadoException {
        throw new AtaqueAAliadoException();
    }

    @Override
    public float recibirCuracion(float cantidad, Pieza pieza){
        return pieza.getVida() + cantidad;

    }

    @Override
    public void enlistarABatallon(List<Pieza> lista, Pieza pieza) {
        lista.add(pieza);
    }

    @Override
    public String getAlianza() {
        return "aliado";
    }
}










