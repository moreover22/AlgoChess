package fiuba.algo3.algochess.entrega1;

import fiuba.algo3.algochess.AlgoChess;
import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.jugador.Jugador;
import fiuba.algo3.algochess.pieza.*;
import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;
import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.movimiento.*;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroOcupadoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Entrega1Test {

    //Pruebas Entidades

    @Test
    public void test00PiezaPuedeMoverseAUnCasilleroDeDistanciaEnCualquierDireccion() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        AlgoChess chess = new AlgoChess();
        Pieza pieza = new SoldadoDeInfanteria();

        chess.posicionar(pieza, new Posicion(0, 0));

        Posicion destino = chess.mover(pieza, new DireccionAbajo());
        assertEquals(new Posicion(0, 1), destino);

        destino = chess.mover(pieza, new DireccionDerecha());
        assertEquals(new Posicion(1, 1), destino);

        destino = chess.mover(pieza, new DireccionIzquierda());
        assertEquals(new Posicion(0, 1), destino);

        destino = chess.mover(pieza, new DireccionArriba());
        assertEquals(new Posicion(0, 0), destino);
    }


    @Test
    public void test01PiezaNoPuedeMoverseAUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException {
        AlgoChess chess = new AlgoChess();
        Pieza pieza1 = new SoldadoDeInfanteria();
        chess.posicionar(pieza1, new Posicion(0, 0));
        Pieza pieza2 = new SoldadoDeInfanteria();
        chess.posicionar(pieza2, new Posicion(0, 1));

        assertThrows(ColocarEnCasilleroOcupadoException.class,
                () -> {
                    chess.mover(pieza2, new DireccionArriba());
                });
    }



    @Test
    public void test02SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        SoldadoDeInfanteria soldado = new SoldadoDeInfanteria();
        soldado.setPosicion(new Posicion(0, 0));

        Jinete enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.setPosicion(new Posicion(1, 0));

        enemigo.recibirDanio(90);
        soldado.usarHabilidadEn(enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.setPosicion(new Posicion(1, 0));
        enemigo.recibirDanio(89);
        soldado.usarHabilidadEn(enemigo);

        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test03JineteAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        SoldadoDeInfanteria enemigo = new SoldadoDeInfanteria();
        enemigo.setPosicion(new Posicion(0, 0));
        enemigo.cambiarAlianza();

        Jinete jinete = new Jinete();
        jinete.setPosicion(new Posicion(1, 0));
        enemigo.recibirDanio(95);
        jinete.usarHabilidadEn(enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();
        enemigo.setPosicion(new Posicion(0, 0));
        enemigo.recibirDanio(94);
        jinete.usarHabilidadEn(enemigo);
        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test04CatapultaAliadoAtacaAUnaPiezaEnemigaYSeVerificaQueSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        Catapulta catapulta = new Catapulta();
        catapulta.setPosicion(new Posicion(0, 0));
        Jinete enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.setPosicion(new Posicion(10, 0));

        enemigo.recibirDanio(80);
        catapulta.usarHabilidadEn(enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.setPosicion(new Posicion(10, 0));
        enemigo.recibirDanio(79);

        catapulta.usarHabilidadEn(enemigo);
        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test05CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        Curandero curandero = new Curandero();
        curandero.setPosicion(new Posicion(0, 0));

        SoldadoDeInfanteria enemigo = new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();
        enemigo.setPosicion(new Posicion(0, 1));

        Jinete aliado = new Jinete();
        aliado.setPosicion(new Posicion(1, 0));
        aliado.cambiarAlianza();
        aliado.recibirDanio(95);

        aliado.cambiarAlianza();
        curandero.usarHabilidadEn(aliado);

        // Cambio las alianzas
        enemigo.cambiarAlianza();
        aliado.cambiarAlianza();

        enemigo.usarHabilidadEn(aliado);
        enemigo.usarHabilidadEn(aliado);
        assertFalse(aliado.estaViva());

        aliado = new Jinete();
        aliado.setPosicion(new Posicion(1, 0));
        aliado.cambiarAlianza();
        aliado.recibirDanio(94);

        // Cambio las alianzas
        aliado.cambiarAlianza();
        curandero.usarHabilidadEn(aliado);

        // Cambio las alianzas
        aliado.cambiarAlianza();

        enemigo.usarHabilidadEn(aliado);
        enemigo.usarHabilidadEn(aliado);
        assertTrue(aliado.estaViva());
    }

    //Pruebas de Tablero

    /*
    @Test
    public void test06TableroAlColocarUnaPiezaDejaDeEstarVacio() throws CasilleroException, FueraDelTableroException {
        Tablero tablero = new Tablero();
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        tablero.posicionar(piezaConcreta, 0, 0);
        assertFalse(tablero.estaVacio());
    }
*/

    @Test
    public void test07TableroAlColocarUnaPiezaEnUnCasilleroOcupadoSeLanzaCasilleroOcupadoException() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Pieza otraPiezaConcreta = new SoldadoDeInfanteria();
        tablero.posicionar(otraPiezaConcreta, new Posicion(0, 0));
        // Act - Assert
        assertThrows(ColocarEnCasilleroOcupadoException.class,
                () -> {
                    tablero.posicionar(piezaConcreta, new Posicion(0, 0));
                });
    }

    @Test
    public void test08TableroAlColocarUnaPiezaEnUnCasilleroEnemigoSeLanzaCasilleroEnemigoException() {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        // Act - Assert
        assertThrows(ColocarEnCasilleroEnemigoException.class,
                () -> {
                    tablero.posicionar(piezaConcreta, new Posicion(0, 10));
                });
    }
/*
    @Test
    public void test09TableroRecienCreadoEstaVacio() {
        Tablero tablero = new Tablero();
        boolean tableroVacio = false;

        assertTrue(tableroVacio);
    }
*/

    //Prueba de Jugador

    @Test
    public void test10JugadorSinPuntosAgregaUnaPiezaDeberiaLanzarCantidadDePuntosInsuficientesException() {
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
    public void test11JugadorSinPiezasPierdeLaPartida() throws CantidadDePuntosInsuficientesException, HabilidadFueraDeAlcanceException, AtaqueAAliadoException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        pieza.setPosicion(new Posicion(0, 0));
        pieza.cambiarAlianza();
        // Act
        jugador.agregarPieza(pieza);
        pieza.recibirDanio(100, new Posicion(1, 0), new AlcanceCercano());
        //Assert
        assertTrue(jugador.perdio());

    }
}