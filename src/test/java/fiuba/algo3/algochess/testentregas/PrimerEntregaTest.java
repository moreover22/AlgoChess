package fiuba.algo3.algochess.testentregas;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.model.jugador.Jugador;
import fiuba.algo3.algochess.model.pieza.*;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.pieza.movimiento.*;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroOcupadoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimerEntregaTest {

    //Pruebas Entidades
    @Test
    public void test00PiezaPuedeMoverseAUnCasilleroDeDistanciaEnCualquierDireccion() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        Pieza pieza = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), pieza);

        tablero.mover(pieza, Direccion.abajo());
        assertEquals(new Posicion(0, 1), pieza.getPosicion());

        tablero.mover(pieza, Direccion.derecha());
        assertEquals(new Posicion(1, 1), pieza.getPosicion());

        tablero.mover(pieza, Direccion.izquierda());
        assertEquals(new Posicion(0, 1), pieza.getPosicion());

        tablero.mover(pieza, Direccion.arriba());
        assertEquals(new Posicion(0, 0), pieza.getPosicion());

        tablero.mover(pieza, Direccion.derechaAbajo());
        assertEquals(new Posicion(1, 1), pieza.getPosicion());
    }

    @Test
    public void test01PiezaNoPuedeMoverseAUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Pieza unaPieza = new SoldadoDeInfanteria();
        Posicion posicionOriginal = new Posicion(0, 0);
        Tablero tablero = new Tablero();
        tablero.posicionar(posicionOriginal, unaPieza);

        Pieza otraPieza = new SoldadoDeInfanteria();
        tablero.posicionar(new Posicion(0, 1), otraPieza);

        // Act
        tablero.mover(unaPieza, Direccion.abajo());

        // Assert
        assertEquals(posicionOriginal, unaPieza.getPosicion());
    }

    @Test
    public void test02SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza soldado = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), soldado);

        Pieza  enemigo = new Jinete();
        enemigo.cambiarAlianza();
        tablero.posicionar(new Posicion(1, 0), enemigo);

        soldado.usarHabilidadEn(tablero, enemigo);
        assertEquals(enemigo.getVida(),90);
    }

    @Test
    public void test03JineteAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza enemigo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        tablero.posicionar(new Posicion(0, 0), enemigo);
        enemigo.cambiarAlianza();


        Pieza jinete = new Jinete();
        tablero.posicionar(new Posicion(1, 0), jinete);
        jinete.usarHabilidadEn(tablero, enemigo);
        assertEquals(enemigo.getVida(),95);

    }

    @Test
    public void test04CatapultaAliadoAtacaAUnaPiezaEnemigaYSeVerificaQueSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza catapulta = new Catapulta();
        Tablero tablero = new Tablero();
        tablero.posicionar(new Posicion(0, 0), catapulta);
        Pieza enemigo = new Jinete();
        enemigo.cambiarAlianza();
        tablero.cambiarAlianza();

        tablero.posicionar(new Posicion(10, 0), enemigo);

        catapulta.usarHabilidadEn(tablero, enemigo);
        assertEquals(enemigo.getVida(),80);
    }

    @Test
    public void test05CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza  curandero = new Curandero();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), curandero);

        Pieza  enemigo = new SoldadoDeInfanteria();
        // enemigo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 1), enemigo);

        Pieza aliado = new Jinete();
        tablero.posicionar(new Posicion(1, 0), aliado);
        aliado.cambiarAlianza();

        enemigo.usarHabilidadEn(tablero,aliado);
        enemigo.usarHabilidadEn(tablero,aliado);

        aliado.cambiarAlianza();
        curandero.usarHabilidadEn(tablero, aliado);

        assertEquals(aliado.getVida(),95);
    }

    //Pruebas de Tablero

    @Test
    public void test06SeColocaUnaPiezaAliadaEnUnCasilleroDelSectorAliadoVacioConExito() throws CasilleroException, FueraDelTableroException {
        //Arrange
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Posicion posicion =  new Posicion(0, 0);
        Tablero tablero = new Tablero();

        //Act
        tablero.posicionar(posicion, piezaConcreta);

        //Assert
        assertEquals(posicion, piezaConcreta.getPosicion());
    }

    @Test
    public void test07TableroNoSePuedeColocarUnaPiezaEnUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Pieza otraPiezaConcreta = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        tablero.posicionar(new Posicion(0, 0), piezaConcreta);
        // Act - Assert
        assertThrows(PosicionarEnCasilleroOcupadoException.class,
                () -> {
                    tablero.posicionar(new Posicion(0, 0), otraPiezaConcreta);
                });
    }

    @Test
    public void test08TableroAlColocarUnaPiezaEnUnCasilleroEnemigoSeLanzaCasilleroEnemigoException() {
        // Arrange
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        // Act - Assert
        assertThrows(PosicionarEnCasilleroEnemigoException.class,
                () -> {

                    tablero.posicionar(new Posicion(10, 0), piezaConcreta);
                });
    }

    @Test
    public void test09TableroSeCreaECorrectamente() {
        Tablero tablero = new Tablero();
        boolean tableroVacio = false;

//        assertTrue(tableroVacio);

        assertNotNull(tablero);
    }



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
    public void test11JugadorSinPiezasPierdeLaPartida() throws CantidadDePuntosInsuficientesException, HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Jugador jugador = new Jugador(20);
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza enemigo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 0), enemigo);
        pieza.cambiarAlianza();
        // Act
        jugador.agregarPieza(pieza);
        while(pieza.estaViva()) {
            enemigo.usarHabilidadEn(tablero, pieza);
        }
        //Assert
        assertTrue(jugador.perdio());
    }
}





