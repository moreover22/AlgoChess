package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.pieza.habilidad.CuracionAEnemigoException;

import java.util.List;

public class PiezaEnemiga implements PiezaAlianza {
    @Override
    public PiezaAlianza cambiar() {
        return new PiezaAliada();
    }

    /*@Override
    public float recibirDanio(float vida, float danio) {
        return vida - danio;
    }

    @Override
    public float recibirCuracion(float vida, float curacion) throws CuracionAEnemigoException {
        throw new CuracionAEnemigoException();
    }*/

    @Override
    public int contarAliado(int cantidadAliados) {
        return cantidadAliados;
    }

    @Override
    public int contarEnemigo(int cantidadEnemigos) {
        return cantidadEnemigos + 1;
    }

    @Override
    public float recibirAtaque(float cantidad,Pieza pieza){
        return pieza.getVida()-cantidad;
    }

    @Override
    public float recibirCuracion(float cantidad, Pieza pieza) throws CuracionAEnemigoException {
        throw new CuracionAEnemigoException();

    }

    @Override
    public void enlistarABatallon(List<Pieza> lista, Pieza pieza) {

    }

    @Override
    public String getAlianza() {
        return "enemigo";
    }
}
