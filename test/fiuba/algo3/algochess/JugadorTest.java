package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.jugador.ExcesoCantPuntosException;
import org.junit.Test;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.jugador.Jugador;
import fiuba.algo3.algochess.Tablero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class JugadorTest {
    @Test
    public void testJugadorAgregaUnaUnidad()throws ExcesoCantPuntosException,ColocarEnCasilleroOcupadoException{
        // Arrange
        Tablero tablero = new Tablero();
        Jugador jugador = new Jugador();
        Pieza pieza = new Pieza();

        int x = 1;
        int y = 1;
        // Act
        jugador.agregarPieza(tablero,pieza,x,y);
        // Asserts
        assertEquals(pieza,tablero.obtenerPieza(x,y));
    }
    @Test
    public void testJugadorGastaPuntosdDeMasAgregandoUnidadesLanzaExcepcion()throws ExcesoCantPuntosException, ColocarEnCasilleroOcupadoException {
        // Arrange
        Tablero tablero = new Tablero();
        Jugador jugador = new Jugador();
        Pieza catapulta1 = new Catapulta();
        Pieza catapulta2 = new Catapulta();
        Pieza catapulta3 = new Catapulta();
        Pieza catapulta4 = new Catapulta();
        Pieza curandero1 = new Curandero();

        //Act
        jugador.agregarPieza(tablero,catapulta1,2,2);
        jugador.agregarPieza(tablero,catapulta2,4,8);
        jugador.agregarPieza(tablero,catapulta3,5,9);
        jugador.agregarPieza(tablero,catapulta4,4,1);


        assertThrows (ExcesoCantPuntosException.class,
                () -> {
                    jugador.agregarPieza(tablero,curandero1,5,6);
                });
    }
}
