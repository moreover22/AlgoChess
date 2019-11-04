package fiuba.algo3.algochess.Movimiento;

public class DireccionArriba implements Direccion {
    @Override
    public int [] aplicarA(int x,int y){
        return new int[]{x,y+1};
    }
}
