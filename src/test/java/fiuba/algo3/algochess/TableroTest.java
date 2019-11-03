package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.casillero.VaciarCasilleroVacioException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

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
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        posicionador.posicionarPieza(pieza, 0, 0);
        assertFalse(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaPiezaSePuedeObtenerNuevamente() throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        // Act
        posicionador.posicionarPieza(pieza, x, y);
        // Assert
        assertEquals(pieza, tablero.obtenerPieza(x, y));
    }

    @Test
    public void testTableroAlColocarUnaPiezaEnUnCasilleroOcupadoSeLanzaCasilleroOcupadoException() throws ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        Pieza piezaOcupante = new Pieza();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        posicionador.posicionarPieza(piezaOcupante, x, y);
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
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = tablero.getCantColumnas() / 2;
        // Act - Assert
        assertThrows(ColocarEnCasilleroEnemigoException.class,
                () -> {
                    posicionador.posicionarPieza(pieza, x, y);
                });
    }
    // TODO armar una clase CasilleroException y que el resto herede de esta para evitar los "throws largos"
    @Test
    public void testTableroAlColocarYSacarUnaPiezaElTableroQuedaVacio() throws ColocarEnCasilleroEnemigoException, ColocarEnCasilleroOcupadoException, VaciarCasilleroVacioException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
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
    public void testTableroSePuedeColocarPiezaDespuesDeColocarYVaciarOtraPieza() throws VaciarCasilleroVacioException, ColocarEnCasilleroOcupadoException, ColocarEnCasilleroEnemigoException{
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        Pieza piezaTemp = new Pieza();
        PosicionadorPiezas posicionador = new PosicionadorPiezas(tablero);
        int x = 0;
        int y = 0;
        posicionador.posicionarPieza(piezaTemp, x, y);
        tablero.sacarPieza(x, y);
        // Act
        posicionador.posicionarPieza(pieza, x, y);
        // Assert
        assertEquals(pieza, tablero.obtenerPieza(x, y));
    }

}
