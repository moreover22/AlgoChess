package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.casillero.VaciarCasilleroVacioException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TableroTest {
    @Test
    public void testTableroRecienCreadoEstaVacio() {
        Tablero tablero = new Tablero();
        assertTrue(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaDejaDeEstarVacio() throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        tablero.colocarPieza(pieza, 0, 0);
        assertFalse(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaSePuedeObtenerNuevamente() throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        int x = 0;
        int y = 0;
        // Act
        tablero.colocarPieza(pieza, x, y);
        // Assert
        assertEquals(pieza, tablero.obtenerPieza(x, y));
    }

    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroOcupadoSeLanzaCasilleroOcupadoException() throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        Pieza piezaOcupante = new Pieza();
        int x = 0;
        int y = 0;
        tablero.colocarPieza(piezaOcupante, x, y);
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
        Pieza pieza = new Pieza();
        int x = 0;
        int y = tablero.getCantColumnas() / 2;
        // Act - Assert
        assertThrows(ColocarEnCasilleroEnemigoException.class,
                () -> {
                    tablero.colocarPieza(pieza, x, y);
                });
    }
    @Test
    public void testTableroAlColocarYSacarUnaPiezaElTableroQuedaVacio() throws ColocarEnCasilleroEnemigoException, ColocarEnCasilleroOcupadoException, VaciarCasilleroVacioException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        int x = 0;
        int y = 0;
        // Act
        tablero.colocarPieza(pieza, x, y);
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
    public void testTableroSePuedeColocarPiezaDespuésDeColocarYVaciarOtraPieza() throws VaciarCasilleroVacioException, ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException{
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        Pieza piezaTemp = new Pieza();
        int x = 0;
        int y = 0;
        tablero.colocarPieza(piezaTemp, x, y);
        tablero.sacarPieza(x, y);
        // Act
        tablero.colocarPieza(pieza, x, y);
        // Assert
        assertEquals(pieza, tablero.obtenerPieza(x, y));
    }

}
