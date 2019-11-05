package fiuba.algo3.algochess;

import fiuba.algo3.algochess.casillero.CasilleroException;
import fiuba.algo3.algochess.casillero.ColocarEnCasilleroOcupadoException;
import fiuba.algo3.algochess.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.pieza.Catapulta;
import fiuba.algo3.algochess.pieza.Curandero;
import fiuba.algo3.algochess.pieza.SoldadoDeInfanteria;
import org.junit.Test;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.jugador.Jugador;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class JugadorTest {
    @Test
    public void testJugadorAgregaUnaUnidad() throws CantidadDePuntosInsuficientesException, CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Jugador jugador = new Jugador();
        Pieza pieza = new SoldadoDeInfanteria();
        int x = 1;
        int y = 1;

        // Act
        jugador.agregarPieza(tablero, pieza, x, y);

        // Asserts
        assertEquals(pieza,tablero.obtenerPieza(x,y));
    }

/*
    @Test
    public void testJugadorGastaPuntosdDeMasAgregandoUnidadesLanzaExcepcion() throws CantidadDePuntosInsuficientesException, CasilleroException, FueraDelTableroException {
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


        assertThrows (CantidadDePuntosInsuficientesException.class,
                () -> {
                    jugador.agregarPieza(tablero,curandero1,5,6);
                });
    }
    */

}
