package fiuba.algo3.algochess.Movimiento;

import java.lang.reflect.Array;

public class DireccionIzquierda implements Direccion {
    @Override
    public int [] aplicarA(int x,int y){
        return new int[]{x+1,y};
    }
}
