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
    public void testPiezaRecienCreadaEstaViva() {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        //Act-Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaRecibeDanioMenorASuVidaYNoMuere() throws AtaqueAAliadoException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        pieza.cambiarAlianza();
        //Act
        pieza.recibirDanio(50);
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaRecibeDanioIgualASuVidaYMuere() throws AtaqueAAliadoException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        pieza.cambiarAlianza();
        //Act
        pieza.recibirDanio(100);
        //Assert
        assertFalse(pieza.estaViva());
    }

    @Test
    public void testPiezaCon100DeVidaRecibe50DeDanioSeCura20DeVidaYVuelveARecibir50DeDanioYNoMuere() throws AtaqueAAliadoException, CuracionAEnemigoException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.cambiarAlianza();
        pieza.recibirDanio(50);
        pieza.cambiarAlianza();
        pieza.recibirCuracion(20);
        pieza.cambiarAlianza();
        pieza.recibirDanio(50);
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaCon100DeVidaSeCura50DeVidaYMuereAlRecibir120DeDanio() throws CuracionAEnemigoException, AtaqueAAliadoException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirCuracion(50);
        pieza.cambiarAlianza();
        pieza.recibirDanio(120);
        //Assert
        assertFalse(pieza.estaViva());
    }

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadYMataAEnemigoCon10DeVida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        objetivo.recibirDanio(90);
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadYNoMataAEnemigoCon11Vida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        objetivo.recibirDanio(89);
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void testJineteUsaHabilidadYMataAEnemigoCon5Vida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Jinete();
        Pieza objetivo = new Jinete();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        objetivo.recibirDanio(95);
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void testJineteUsaHabilidadYNoMataAEnemigoCon6Vida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Jinete();
        Pieza objetivo = new Jinete();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        objetivo.recibirDanio(94);
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void testCatapultaUsaHabilidadYMataAEnemigoCon20Vida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Catapulta();
        Pieza objetivo = new Catapulta();
        Tablero tablero = new Tablero(50, 50);
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.cambiarAlianza();

        tablero.posicionar(new Posicion(40, 20), objetivo);
        objetivo.recibirDanio(30);
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void testCatapultaUsaHabilidadYNoMataAEnemigoCon21Vida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Catapulta();
        Pieza objetivo = new Catapulta();
        Tablero tablero = new Tablero(50, 50);
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(40, 20), objetivo);
        objetivo.recibirDanio(29);
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void testCuranderoUsaHabilidadYSalvaAALiadoCon1DeVida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        objetivo.cambiarAlianza();
        objetivo.recibirDanio(74);
        objetivo.cambiarAlianza();
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        objetivo.cambiarAlianza();
        objetivo.recibirDanio(15);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void testCuranderoUsaHabilidadYNoSalvaAALiadoCon1DeVida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), pieza);
        tablero.posicionar(new Posicion(1, 1), objetivo);
        objetivo.cambiarAlianza();
        objetivo.recibirDanio(74);
        objetivo.cambiarAlianza();
        //Act
        pieza.usarHabilidadEn(tablero, objetivo);
        objetivo.cambiarAlianza();
        objetivo.recibirDanio(16);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void test01PiezaNoPuedeMoverseAUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
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
    public void test02PiezaPuedeMoverseAUnCasilleroDeDistanciaEnCualquierDireccion() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
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
    public void test03SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        SoldadoDeInfanteria soldado = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), soldado);

        Jinete enemigo = new Jinete();
        enemigo.cambiarAlianza();

        tablero.posicionar(new Posicion(1, 0), enemigo);

        enemigo.recibirDanio(90);
        soldado.usarHabilidadEn(tablero, enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.cambiarAlianza();

        tablero.posicionar(new Posicion(1, 1), enemigo);
        enemigo.recibirDanio(89);
        soldado.usarHabilidadEn(tablero, enemigo);

        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test04JineteAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        SoldadoDeInfanteria enemigo =  new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), enemigo);
        enemigo.cambiarAlianza();

        Jinete jinete = new Jinete();

        tablero.posicionar(new Posicion(1, 0), jinete);
        enemigo.recibirDanio(95);
        jinete.usarHabilidadEn(tablero, enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();

        tablero.posicionar(new Posicion(1, 1), enemigo);
        enemigo.recibirDanio(94);
        jinete.usarHabilidadEn(tablero, enemigo);
        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test05CatapultaAliadoAtacaAUnaPiezaEnemigaYSeVerificaQueSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Catapulta catapulta = new Catapulta();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), catapulta);
        Jinete enemigo = new Jinete();
        enemigo.cambiarAlianza();

        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(10, 0), enemigo);

        enemigo.recibirDanio(80);
        catapulta.usarHabilidadEn(tablero, enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.cambiarAlianza();

        tablero.posicionar(new Posicion(10, 0), enemigo);
        enemigo.recibirDanio(79);

        catapulta.usarHabilidadEn(tablero, enemigo);
        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test06CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Curandero curandero =  new Curandero();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(0, 0), curandero);

        SoldadoDeInfanteria enemigo =  new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();

        tablero.posicionar(new Posicion(0, 1), enemigo);

        Jinete aliado = new Jinete();

        tablero.posicionar(new Posicion(1, 0), aliado);
        aliado.cambiarAlianza();
        aliado.recibirDanio(95);

        aliado.cambiarAlianza();
        curandero.usarHabilidadEn(tablero, aliado);

        // Cambio las alianzas
        enemigo.cambiarAlianza();
        aliado.cambiarAlianza();

        enemigo.usarHabilidadEn(tablero, aliado);
        enemigo.usarHabilidadEn(tablero, aliado);
        assertFalse(aliado.estaViva());

        aliado = new Jinete();

        tablero.posicionar(new Posicion(1, 0), aliado);
        aliado.cambiarAlianza();
        aliado.recibirDanio(94);

        // Cambio las alianzas
        aliado.cambiarAlianza();
        curandero.usarHabilidadEn(tablero, aliado);

        // Cambio las alianzas
        aliado.cambiarAlianza();

        enemigo.usarHabilidadEn(tablero, aliado);
        enemigo.usarHabilidadEn(tablero, aliado);
        assertTrue(aliado.estaViva());
    }

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
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
    public void testjineteUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
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
    public void testCatapultaUsaHabilidadAEnemigoQueNoEstaALargaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
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
    public void testCuranderoUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
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
    public void testPiezaCuranderaCuraAUnaPiezaEnemigaDeberiaLanzarCuracionAEnemigoException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
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
    public void testPiezaAtacaAPiezaAliadaDeberiaLanzarAtaqueAAliadoException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
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
    public void testCatapultaNoPuedeMoverse() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza catapulta = new Catapulta();

        tablero.posicionar(new Posicion(0, 0), catapulta);
        // Act - Assert excepcion
        assertThrows(MovimientoFueraDeAlcanceException.class,
                () -> {
                    tablero.mover(catapulta, Direccion.derechaAbajo());
                });
        // Assert
        assertEquals(new Posicion(0, 0), catapulta.getPosicion());
    }

}