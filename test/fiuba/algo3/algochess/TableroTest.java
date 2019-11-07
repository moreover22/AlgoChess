package fiuba.algo3.algochess;

import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.tablero.casillero.VaciarCasilleroVacioException;
import fiuba.algo3.algochess.pieza.Pieza;

import fiuba.algo3.algochess.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TableroTest {
    @Mock
    private Pieza pieza;

    @Test
    public void testTableroRecienCreadoEstaVacio() {
        Tablero tablero = new Tablero();
        assertTrue(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaDejaDeEstarVacio() throws CasilleroException, FueraDelTableroException {
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(0, 0);
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        posicionador.posicionarPieza(piezaConcreta, posicion);
        assertFalse(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroOcupadoSeLanzaCasilleroOcupadoException() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(0, 0);
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Pieza otraPiezaConcreta = new SoldadoDeInfanteria();
        posicionador.posicionarPieza(otraPiezaConcreta, posicion);
        // Act - Assert
        assertThrows(ColocarEnCasilleroOcupadoException.class,
                () -> {
                    posicionador.posicionarPieza(piezaConcreta, posicion);
                });
    }

    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroEnemigoSeLanzaCasilleroEnemigoException() throws FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Posicion posicionEnemiga = new Posicion(0, tablero.getCantColumnas() / 2);

        //Tablero tablero = spy(new Tablero());
        // when(tablero.esAliado(posicionEnemiga)).thenReturn(false);

        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);

        Pieza piezaConcreta = new SoldadoDeInfanteria();
        // Act - Assert
        assertThrows(ColocarEnCasilleroEnemigoException.class,
                () -> {
                    posicionador.posicionarPieza(piezaConcreta, posicionEnemiga);
                });
    }

    @Test
    public void testTableroAlColocarYSacarUnaPiezaElTableroQuedaVacio() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(0, 0);
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        // Act
        posicionador.posicionarPieza(piezaConcreta, posicion);
        posicionador.sacarPieza(posicion);
        // Assert
        assertTrue(tablero.estaVacio());
    }
    @Test
    public void testTableroVaciarCasilleroVacioLanzaVaciarCasilleroVacioException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionadorPiezas = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(0, 0);
        // Act - Assert
        assertThrows(VaciarCasilleroVacioException.class,
                () -> {
                        posicionadorPiezas.sacarPieza(posicion);
                });
    }

    @Test
    public void testTableroSePuedeColocarPiezaEnCasilleroDespuesDeColocarYSacarOtraPiezaEnElMismoCasillero() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(0, 0);
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Pieza otraPiezaConcreta = new SoldadoDeInfanteria();
        posicionador.posicionarPieza(otraPiezaConcreta, posicion);
        posicionador.sacarPieza(posicion);
        // Act
        posicionador.posicionarPieza(piezaConcreta, posicion);
        // Assert
        assertFalse(tablero.estaVacio());
        assertEquals(piezaConcreta.getPosicion(), posicion);
        assertNotEquals(otraPiezaConcreta, posicion);
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnFilasLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(tablero.getCantFilas(), 0);
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, posicion);
                });
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnColumnasLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(0, tablero.getCantColumnas());
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, posicion);
                });
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnFilasYColumnasLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(tablero.getCantFilas(), tablero.getCantColumnas());
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, posicion);
                });
    }

    @Test
    public void testTableroColocarPiezaEnPoscicionNegativaLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        Posicion posicion = new Posicion(-1, -1);
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, posicion);
                });
    }
}
