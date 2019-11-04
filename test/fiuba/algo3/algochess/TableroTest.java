package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.CasilleroException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.casillero.VaciarCasilleroVacioException;
import fiuba.algo3.algochess.pieza.Pieza;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TableroTest {
    @Mock
    private Pieza pieza, otraPieza;

    @Test
    public void testTableroRecienCreadoEstaVacio() {
        Tablero tablero = new Tablero();
        assertTrue(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaDejaDeEstarVacio() throws CasilleroException, FueraDelTableroException {
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        posicionador.posicionarPieza(pieza, 0, 0);
        assertFalse(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaSePuedeObtenerNuevamente() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        // Act
        posicionador.posicionarPieza(pieza, x, y);
        // Assert
        assertEquals(pieza, tablero.obtenerPieza(x, y));
    }

    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroOcupadoSeLanzaCasilleroOcupadoException() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        posicionador.posicionarPieza(otraPieza, x, y);
        // Act - Assert
        assertThrows(ColocarEnCasilleroOcupadoException.class,
                () -> {
                    tablero.colocarPieza(pieza, x, y);
                });
    }

    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroEnemigoSeLanzaCasilleroEnemigoException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = tablero.getCantColumnas() / 2;
        // Act - Assert
        assertThrows(ColocarEnCasilleroEnemigoException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, x, y);
                });
    }

    @Test
    public void testTableroAlColocarYSacarUnaPiezaElTableroQuedaVacio() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        // Act
        posicionador.posicionarPieza(pieza, x, y);
        // TODO para mantener la interfaz, se podría agregar al PosicionadorPiezas
        tablero.sacarPieza(x, y);
        // Assert
        assertTrue(tablero.estaVacio());
    }
    @Test
    public void testTableroVaciarCasilleroVacioLanzaVaciarCasilleroVacioException() {
        // Arrange
        Tablero tablero = new Tablero();
        int x = 0;
        int y = 0;
        // Act - Assert
        assertThrows(VaciarCasilleroVacioException.class,
                () -> {
                        tablero.sacarPieza(x, y);
                });
    }
    @Test
    public void testTableroSePuedeColocarPiezaEnCasilleroDespuesDeColocarYSacarOtraPiezaEnElMismoCasillero() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        posicionador.posicionarPieza(otraPieza, x, y);
        tablero.sacarPieza(x, y);
        // Act
        posicionador.posicionarPieza(pieza, x, y);
        // Assert
        assertEquals(pieza, tablero.obtenerPieza(x, y));
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnFilasLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = tablero.getCantFilas();
        int y = 0;
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, x, y);
                });
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnColumnasLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = tablero.getCantColumnas();
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, x, y);
                });
    }

    @Test
    public void testTableroColocarPiezaFueraDelRangoEnFilasYColumnasLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = tablero.getCantFilas();
        int y = tablero.getCantColumnas();
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, x, y);
                });
    }

    @Test
    public void testTableroColocarPiezaEnPoscicionNegativaLanzaFueraDelTableroException() {
        // Arrange
        Tablero tablero = new Tablero();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = -1;
        int y = 0;
        // Act - Assert
        assertThrows(FueraDelTableroException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, x, y);
                });
    }
}
