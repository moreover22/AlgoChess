package fiuba.algo3.algochess;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.model.pieza.Pieza;

import fiuba.algo3.algochess.model.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {
/*
    @Test
    public void testTableroRecienCreadoEstaVacio() {
        Tablero tablero = new Tablero();
        assertTrue(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaDejaDeEstarVacio() throws CasilleroException, FueraDelTableroException {
        Tablero tablero = new Tablero();
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        tablero.posicionar(piezaConcreta, 0, 0);
        assertFalse(tablero.estaVacio());
    }
*/
    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroEnemigoSeLanzaCasilleroEnemigoException() {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        // Act - Assert
        assertThrows(PosicionarEnCasilleroEnemigoException.class,
                () -> {
                    tablero.posicionar(new Posicion(10, 0), piezaConcreta);
                });
    }
/*
    @Test
    public void testTableroAlColocarYSacarUnaPiezaElTableroQuedaVacio() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        // Act
        tablero.posicionar(piezaConcreta, 0, 0);
        tablero.sacar(0, 0);
        // Assert
        assertTrue(tablero.estaVacio());
    }
*/
/*
    @Test
    public void testTableroSePuedeColocarPiezaEnCasilleroDespuesDeColocarYSacarOtraPiezaEnElMismoCasillero() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Pieza otraPiezaConcreta = new SoldadoDeInfanteria();
        otraPiezaConcreta.posicionar(new Posicion(0, 0));
        Tablero.getInstance().sacar(new Posicion(0, 0));
        // Act
        tablero.posicionar(piezaConcreta, new Posicion(0, 0));
        // Assert
//        assertFalse(tablero.estaVacio());
//        assertEquals(piezaConcreta.getPosicion(), posicion);
//        assertNotEquals(otraPiezaConcreta, posicion);
    }
*/
    @Test
    public void testTableroColocarPiezaFueraDelRangoEnFilasLanzaFueraDelTableroException() {
        Tablero tablero = new Tablero();

        assertThrows(FueraDelTableroException.class,
                () -> {
                    tablero.posicionar(new Posicion(0, 21), new SoldadoDeInfanteria());
                });
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnColumnasLanzaFueraDelTableroException() {
        Tablero tablero = new Tablero();

        assertThrows(FueraDelTableroException.class,
                () -> {
                    tablero.posicionar(new Posicion(21, 0), new SoldadoDeInfanteria());
                });
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnFilasYColumnasLanzaFueraDelTableroException() {
        Tablero tablero = new Tablero();

        assertThrows(FueraDelTableroException.class,
                () -> {
                    tablero.posicionar(new Posicion(21, 21), new SoldadoDeInfanteria());
                });
    }

    @Test
    public void testTableroColocarPiezaEnPoscicionNegativaLanzaFueraDelTableroException() {
        Tablero tablero = new Tablero();

        assertThrows(FueraDelTableroException.class,
                () -> {
                    tablero.posicionar(new Posicion(-1, -1), new SoldadoDeInfanteria());
                });
    }

    @Test
    public void testPiezaQueSeEncuentraEnCasilleroEnemigoRecibeElDanioCorrespondiente() throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException, MovimientoFueraDeAlcanceException {
        //Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new SoldadoDeInfanteria();
        tablero.posicionar(new Posicion (9,0),pieza);
        tablero.mover(pieza,Direccion.derecha());
        //Act
        tablero.aplicarDanioTerritorio();
        //Assert
        assertEquals(pieza.getVida(),95);
    }

    @Test
    public void testPiezaQueSeEncuentraEnCasilleroAliadoNoRecibeElDanioDeTerritorio() throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException, MovimientoFueraDeAlcanceException {
        //Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new SoldadoDeInfanteria();
        tablero.posicionar(new Posicion (9,0),pieza);

        //Act
        tablero.aplicarDanioTerritorio();
        //Assert
        assertEquals(pieza.getVida(),100);
    }

}





















