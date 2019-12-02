package fiuba.algo3.algochess;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.*;
import fiuba.algo3.algochess.model.pieza.habilidad.*;
import fiuba.algo3.algochess.model.pieza.movimiento.*;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PiezaTest {

    @Test
    public void test00PiezaRecienCreadaEstaViva() {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        //Act-Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void test01PiezaRecibeDanioMenorASuVidaYNoMuere() throws HabilidadConObjetivoInvalidoException, FueraDelTableroException, HabilidadFueraDeAlcanceException, PosicionarEnCasilleroEnemigoException, CuracionACatapultaException {
        //Arrange
        Tablero tablero = new Tablero();
        Pieza atacante = new SoldadoDeInfanteria();
        Pieza pieza = new SoldadoDeInfanteria();
        pieza.cambiarAlianza();
        tablero.posicionar(new Posicion(1, 0), pieza);
        tablero.posicionar(new Posicion(0, 0), atacante);
        //Act
        atacante.usarHabilidadEn(tablero,pieza);
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void test02PiezaNoPuedeMoverseAUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza pieza1 = new SoldadoDeInfanteria();
        Pieza pieza2 = new SoldadoDeInfanteria();

        tablero.posicionar(new Posicion(0, 0), pieza1);
        tablero.posicionar(new Posicion(0, 1), pieza2);
        // Act
        tablero.mover(pieza2, Direccion.arriba());
        // Assert
        assertEquals(new Posicion(0, 1), pieza2.getPosicion());
    }

    @Test
    public void test03PiezaPuedeMoverseAUnCasilleroDeDistanciaEnCualquierDireccion() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
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

        tablero.mover(pieza, Direccion.derechaArriba());
        assertEquals(new Posicion(2, 0), pieza.getPosicion());

        tablero.mover(pieza, Direccion.izquierdaAbajo());
        assertEquals(new Posicion(1, 1), pieza.getPosicion());

        tablero.mover(pieza, Direccion.izquierdaArriba());
        assertEquals(new Posicion(0, 0), pieza.getPosicion());
    }


    @Test
    public void test04SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionACatapultaException {
        Pieza soldado = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), soldado);

        Pieza enemigo = new Jinete();
        enemigo.cambiarAlianza();
        tablero.posicionar(new Posicion(1, 0), enemigo);
        
        soldado.usarHabilidadEn(tablero,enemigo);
        
        assertEquals(enemigo.getVida(),90);
        
    }
    

    @Test
    public void test05CatapultaAliadoAtacaAUnaPiezaEnemigaYSeVerificaQueSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionACatapultaException {
        Pieza catapulta = new Catapulta();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), catapulta);
        Pieza enemigo = new Jinete();
        enemigo.cambiarAlianza();

        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(10, 0), enemigo);
        
        catapulta.usarHabilidadEn(tablero,enemigo);
        
        assertEquals(enemigo.getVida(),80);
    }

    @Test
    public void test06CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionACatapultaException {
        Pieza curandero = new Curandero();
        Tablero tablero = new Tablero();
        tablero.posicionar(new Posicion(0, 0), curandero);

        Pieza enemigo = new SoldadoDeInfanteria();
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

    @Test
    public void test07SoldadoDeInfateriaUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);

        tablero.posicionar(ubicacionPieza, pieza);
        tablero.cambiarAlianza();
        tablero.posicionar(ubicacionObjetivo, objetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(tablero, objetivo);
                });
    }

    @Test
    public void test08jineteUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Jinete();
        Pieza objetivo = new Jinete();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);

        tablero.posicionar(ubicacionPieza, pieza);
        tablero.cambiarAlianza();
        tablero.posicionar(ubicacionObjetivo, objetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(tablero, objetivo);
                });
    }

    @Test
    public void test09CatapultaUsaHabilidadAEnemigoQueNoEstaALargaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Catapulta();
        Pieza objetivo = new Catapulta();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(1, 1);

        tablero.posicionar(ubicacionPieza, pieza);

        tablero.posicionar(ubicacionObjetivo, objetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(tablero, objetivo);
                });
    }

    @Test
    public void test10CuranderoUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Tablero tablero = new Tablero();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);

        tablero.posicionar(ubicacionPieza, pieza);

        tablero.cambiarAlianza();
        tablero.posicionar(ubicacionObjetivo, objetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(tablero, objetivo);
                });
    }

    @Test
    public void test11PiezaCuranderaCuraAUnaPiezaEnemigaDeberiaLanzarCuracionAEnemigoException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Pieza curandero = new Curandero();
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), curandero);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        // Act - Assert
        assertThrows(CuracionAEnemigoException.class,
                () -> {
                    curandero.usarHabilidadEn(tablero, objetivo);
                });
    }

    @Test
    public void test12PiezaAtacaAPiezaAliadaDeberiaLanzarAtaqueAAliadoException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza otraPieza = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), otraPieza);
        // Act - Assert
        assertThrows(AtaqueAAliadoException.class,
                () -> {
                    pieza.usarHabilidadEn(tablero, otraPieza);
                });
    }

    @Test
    public void test13CatapultaNoPuedeMoverse() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza catapulta = new Catapulta();

        tablero.posicionar(new Posicion(0, 0), catapulta);
        // Act - Assert excepcion
        assertThrows(MovimientoFueraDeAlcanceException.class,
                () -> {
                    tablero.mover(catapulta , Direccion.derechaAbajo());
                });
        // Assert
        assertEquals(new Posicion(0, 0), catapulta.getPosicion());
    }

    @Test
    public void test14CatapultaAtacaCorrectmanteAUngrupoDeUnidades() throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, CuracionACatapultaException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza catapulta = new Catapulta();
        Pieza aliado1 = new SoldadoDeInfanteria();
        Pieza enemigo1 = new SoldadoDeInfanteria();
        Pieza aliado2 = new Jinete();
        Pieza enemigo2 = new Catapulta();

        tablero.posicionar(new Posicion(0, 0), catapulta);
        tablero.posicionar(new Posicion(9,5),aliado1);
        tablero.posicionar(new Posicion(9,6),enemigo1);
        tablero.posicionar(new Posicion(8,6),aliado2);
        tablero.posicionar(new Posicion(7,6),enemigo2);
        enemigo1.cambiarAlianza();
        enemigo2.cambiarAlianza();

        // Act - Assert excepcion
       catapulta.usarHabilidadEn(tablero,enemigo1);
        // Assert
        assertEquals(enemigo1.getVida(),80);
        assertEquals(aliado1.getVida(),80);
        assertEquals(aliado2.getVida(),80);
        assertEquals(enemigo2.getVida(),30);
    }

    @Test
    public void test15CatapultaNoPuedeSerCurada() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza catapulta = new Catapulta();
        Pieza curandero = new Curandero();

        tablero.posicionar(new Posicion(0, 0), catapulta);
        tablero.posicionar(new Posicion(0, 1), curandero);

        // Act - Assert excepcion
        assertThrows(CuracionACatapultaException.class,
                () -> {
                    curandero.usarHabilidadEn(tablero,catapulta);
                });
    }

}



