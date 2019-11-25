package fiuba.algo3.algochess.testentregas;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.jugador.CantidadDePuntosInsuficientesException;
import fiuba.algo3.algochess.jugador.Jugador;
import fiuba.algo3.algochess.pieza.*;
import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.movimiento.*;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroOcupadoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimerEntregaTest {

    //Pruebas Entidades
    @Test
    public void test00PiezaPuedeMoverseAUnCasilleroDeDistanciaEnCualquierDireccion() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        Pieza pieza = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        pieza.posicionar(tablero, new Posicion(0, 0));

        pieza.mover(tablero, Direccion.abajo());
        assertEquals(new Posicion(0, 1), pieza.getPosicion());

        pieza.mover(tablero, Direccion.derecha());
        assertEquals(new Posicion(1, 1), pieza.getPosicion());

        pieza.mover(tablero, Direccion.izquierda());
        assertEquals(new Posicion(0, 1), pieza.getPosicion());

        pieza.mover(tablero, Direccion.arriba());
        assertEquals(new Posicion(0, 0), pieza.getPosicion());

        pieza.mover(tablero, Direccion.derechaAbajo());
        assertEquals(new Posicion(1, 1), pieza.getPosicion());

    }

    @Test
    public void test01PiezaNoPuedeMoverseAUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Pieza unaPieza = new SoldadoDeInfanteria();
        Posicion posicionOriginal = new Posicion(0, 0);
        Tablero tablero = new Tablero();

        unaPieza.posicionar(tablero, posicionOriginal);

        Pieza otraPieza = new SoldadoDeInfanteria();
        otraPieza.posicionar(tablero, new Posicion(0, 1));

        // Act
        unaPieza.mover(tablero, Direccion.abajo());

        // Assert
        assertEquals(posicionOriginal, unaPieza.getPosicion());
    }

    @Test
    public void test02SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza soldado = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        soldado.posicionar(tablero, new Posicion(0, 0));

        Pieza  enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.posicionar(tablero, new Posicion(1, 0));

        soldado.usarHabilidadEn(tablero, enemigo);
        assertEquals(enemigo.getVida(),90);

    }

    @Test
    public void test03JineteAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza enemigo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        enemigo.posicionar(tablero, new Posicion(0, 0));
        enemigo.cambiarAlianza();

        Pieza  jinete = new Jinete();
        jinete.posicionar(tablero, new Posicion(1, 0));
        jinete.usarHabilidadEn(tablero, enemigo);
        assertEquals(enemigo.getVida(),95);

    }

    @Test
    public void test04CatapultaAliadoAtacaAUnaPiezaEnemigaYSeVerificaQueSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza catapulta = new Catapulta();
        Tablero tablero = new Tablero();
        catapulta.posicionar(tablero, new Posicion(0, 0));
        Pieza  enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.posicionar(tablero, new Posicion(10, 0));

        catapulta.usarHabilidadEn(tablero, enemigo);
        assertEquals(enemigo.getVida(),80);

    }

    @Test
    public void test05CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Pieza  curandero = new Curandero();
        Tablero tablero = new Tablero();
        curandero.posicionar(tablero, new Posicion(0, 0));

        Pieza  enemigo = new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();
        enemigo.posicionar(tablero, new Posicion(0, 1));

        Pieza  aliado = new Jinete();
        aliado.posicionar(tablero, new Posicion(1, 0));

        enemigo.usarHabilidadEn(tablero,aliado);
        enemigo.usarHabilidadEn(tablero,aliado);
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
        piezaConcreta.posicionar(tablero, posicion);
        //Assert
        assertEquals(posicion, piezaConcreta.getPosicion());
    }

    @Test
    public void test07TableroNoSePuedeColocarUnaPiezaEnUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException {
        // Arrange
        Pieza piezaConcreta = new SoldadoDeInfanteria();
        Pieza otraPiezaConcreta = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        piezaConcreta.posicionar(tablero, new Posicion(0, 0));
        // Act - Assert
        assertThrows(PosicionarEnCasilleroOcupadoException.class,
                () -> {
                    otraPiezaConcreta.posicionar(tablero, new Posicion(0, 0));
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
                    piezaConcreta.posicionar(tablero, new Posicion(0, 10));
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        enemigo.posicionar(tablero,new Posicion(1,0));
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





