package fiuba.algo3.algochess;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.jugador.Jugador;

import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void testJugadorRecienCreadoTiene20Puntos() {
        // Arrange
        Jugador jugador = new Jugador(20);
        // Act
        int puntos = jugador.getPuntos();
        // Asserts
        assertEquals(puntos, 20);
    }

    @Test
    public void testJugadorAgregaUnaPiezaYSeDescuentaDePuntosElCosteDeLaPieza() throws CantidadDePuntosInsuficientesException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        // Act
        jugador.agregarPieza(pieza);
        // Asserts
        assertEquals(jugador.getPuntos(), 19);
    }

    @Test
    public void testJugadorAgregaDosVecesLaMismaPiezaYSeDescuentaDosVecesDeLosPuntosDelJugador() throws CantidadDePuntosInsuficientesException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza otraPieza = new SoldadoDeInfanteria();
        // Act
        jugador.agregarPieza(pieza);
        jugador.agregarPieza(otraPieza);
        // Asserts
        assertEquals(jugador.getPuntos(), 18);
    }

    @Test
    public void testJugadorAgregaUnaPiezaLuegoLaSacaYSeReintegraElCosto() throws CantidadDePuntosInsuficientesException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        // Act
        jugador.agregarPieza(pieza);
        jugador.sacarPieza(pieza);
        // Asserts
        assertEquals(jugador.getPuntos(), 20);
    }

    @Test
    public void testJugadorAgregaDosVecesElMismoTipoDePiezaYSacaUnaPeroSeDescuentaUnaVez() throws CantidadDePuntosInsuficientesException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza otraPieza = new SoldadoDeInfanteria();
        jugador.agregarPieza(pieza);
        jugador.agregarPieza(otraPieza);
        // Act
        jugador.sacarPieza(pieza);
        // Asserts
        assertEquals(jugador.getPuntos(), 19);

    }

    @Test
    public void testJugadorSinPuntosAgregaUnaPiezaDeberiaLanzarCantidadDePuntosInsuficientesException() {
        // Arrange
        Jugador jugador = new Jugador(0);
        Pieza pieza = new SoldadoDeInfanteria();
        // Act - Assert
        assertThrows(CantidadDePuntosInsuficientesException.class,
                () -> {
                    jugador.agregarPieza(pieza);
                });
    }

    @Test
    public void testJugadorConUnaPiezaVivaSigueEnPartida()throws CantidadDePuntosInsuficientesException{
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        jugador.agregarPieza(pieza);
        //Assert
        assertFalse(jugador.perdio());
    }

    @Test
    public void testJugadorConDosPiezasVivaSigueEnPartida()throws CantidadDePuntosInsuficientesException{
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza1 = new SoldadoDeInfanteria();
        Pieza pieza2 = new SoldadoDeInfanteria();
        //Act
        jugador.agregarPieza(pieza1);
        jugador.agregarPieza(pieza2);
        //Assert
        assertFalse(jugador.perdio());
    }

    @Test
    public void testJugadorConUnaPiezaVivaYUnaMuertaSigueEnPartida() throws CantidadDePuntosInsuficientesException, HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza1 = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        pieza1.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza1);
        Pieza pieza2 = new SoldadoDeInfanteria();
        //Act
        jugador.agregarPieza(pieza1);
        jugador.agregarPieza(pieza2);
        pieza1.recibirDanio(100);
        //Assert
        assertFalse(jugador.perdio());
    }

    @Test
    public void testJugadorSinPiezasPierdeLaPartida() throws CantidadDePuntosInsuficientesException, HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), pieza);
        pieza.cambiarAlianza();
        // Act
        jugador.agregarPieza(pieza);
        pieza.recibirDanio(100);
        //Assert
        assertTrue(jugador.perdio());


    }
}