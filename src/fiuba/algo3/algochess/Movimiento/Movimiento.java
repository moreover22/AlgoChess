package fiuba.algo3.algochess.Movimiento;


public class Movimiento {
    public int [] mover(int x, int y,Direccion direccion){
        return direccion.aplicarA(x,y);
    }
}
