package fiuba.algo3.algochess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableroTest {
    @Test
    public void testTableroRecienCreadoEstaVacio() {
        // Arrange
        Tablero tablero = new Tablero();
        // Act
        boolean estaVacio = tablero.estaVacio();
        // Assert
        assertTrue(estaVacio);
    }

    @Test
    public void testTableroAlColocarUnaUnidadDejaDeEstarVacio() {
        // Arrange
        Tablero tablero = new Tablero();
        Unidad unidad = new Unidad();
        int posX = 0;
        int posY = 0;
        // Act
        tablero.colocar(unidad, posX, posY);
        // Assert
        assertFalse(tablero.estaVacio());
    }

    @Test
    public void testTableroAlColocarUnaUnidadLuegoSePuedeRecuperar() {
        // Arrange
        Tablero tablero = new Tablero();
        Unidad unidad = new Unidad();
        int posX = 0;
        int posY = 0;
        tablero.colocar(unidad, posX, posY);
        // Act
        Unidad unidadTmp = tablero.obtener(posX, posY);
        // Assert
        assertEquals(unidad, unidadTmp);
    }


}
