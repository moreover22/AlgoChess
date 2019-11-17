package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.*;
import fiuba.algo3.algochess.pieza.habilidad.AtaqueAAliadoException;
import fiuba.algo3.algochess.pieza.habilidad.CuracionAEnemigoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.movimiento.*;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        objetivo.recibirDanio(90);
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        objetivo.recibirDanio(89);
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        objetivo.recibirDanio(95);
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        objetivo.recibirDanio(94);
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        tablero.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(20, 40));
        objetivo.recibirDanio(30);
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        tablero.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(20, 40));
        objetivo.recibirDanio(29);
        //Act
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void testCuranderoUsaHabilidadYSalvaAALiadoCon1DeVida() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Tablero tablero = new Tablero();
        pieza.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        objetivo.cambiarAlianza();
        objetivo.recibirDanio(74);
        objetivo.cambiarAlianza();
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        objetivo.cambiarAlianza();
        objetivo.recibirDanio(74);
        objetivo.cambiarAlianza();
        //Act
        pieza.usarHabilidadEn(objetivo);
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
        pieza1.posicionar(tablero, new Posicion(0, 0));
        pieza2.posicionar(tablero, new Posicion(0, 1));
        // Act
        pieza2.mover(tablero, Direccion.arriba());
        // Assert
        assertEquals(new Posicion(0, 1), pieza2.getPosicion());
    }

    @Test
    public void test02PiezaPuedeMoverseAUnCasilleroDeDistanciaEnCualquierDireccion() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
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
    }


    @Test
    public void test03SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        SoldadoDeInfanteria soldado = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        soldado.posicionar(tablero, new Posicion(0, 0));

        Jinete enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.posicionar(tablero, new Posicion(1, 0));

        enemigo.recibirDanio(90);
        soldado.usarHabilidadEn(enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.posicionar(new Tablero(), new Posicion(1, 0));
        enemigo.recibirDanio(89);
        soldado.usarHabilidadEn(enemigo);

        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test04JineteAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        SoldadoDeInfanteria enemigo =  new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        enemigo.posicionar(tablero, new Posicion(0, 0));
        enemigo.cambiarAlianza();

        Jinete jinete = new Jinete();
        jinete.posicionar(tablero, new Posicion(1, 0));
        enemigo.recibirDanio(95);
        jinete.usarHabilidadEn(enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();
        enemigo.posicionar(new Tablero(), new Posicion(0, 0));
        enemigo.recibirDanio(94);
        jinete.usarHabilidadEn(enemigo);
        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test05CatapultaAliadoAtacaAUnaPiezaEnemigaYSeVerificaQueSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Catapulta catapulta = new Catapulta();
        Tablero tablero = new Tablero();
        catapulta.posicionar(tablero, new Posicion(0, 0));
        Jinete enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.posicionar(tablero, new Posicion(10, 0));

        enemigo.recibirDanio(80);
        catapulta.usarHabilidadEn(enemigo);
        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.cambiarAlianza();
        enemigo.posicionar(new Tablero(), new Posicion(10, 0));
        enemigo.recibirDanio(79);

        catapulta.usarHabilidadEn(enemigo);
        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test06CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        Curandero curandero =  new Curandero();
        Tablero tablero = new Tablero();
        curandero.posicionar(tablero, new Posicion(0, 0));

        SoldadoDeInfanteria enemigo =  new SoldadoDeInfanteria();
        enemigo.cambiarAlianza();
        enemigo.posicionar(tablero, new Posicion(0, 1));

        Jinete aliado = new Jinete();
        aliado.posicionar(tablero, new Posicion(1, 0));
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
        aliado.posicionar(new Tablero(), new Posicion(1, 0));
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

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);
        pieza.posicionar(tablero, ubicacionPieza);
        objetivo.posicionar(tablero, ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, ubicacionPieza);
        objetivo.posicionar(tablero, ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, ubicacionPieza);
        objetivo.posicionar(tablero, ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo);
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
        pieza.posicionar(tablero, ubicacionPieza);
        objetivo.posicionar(tablero, ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo);
                });
    }

    @Test
    public void testPiezaCuranderaCuraAUnaPiezaEnemigaDeberiaLanzarCuracionAEnemigoException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Pieza curandero = new Curandero();
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        curandero.posicionar(tablero, new Posicion(0, 0));
        objetivo.posicionar(tablero, new Posicion(1, 1));
        // Act - Assert
        assertThrows(CuracionAEnemigoException.class,
                () -> {
                    curandero.usarHabilidadEn(objetivo);
                });
    }

    @Test
    public void testPiezaAtacaAPiezaAliadaDeberiaLanzarAtaqueAAliadoException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        // Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza otraPieza = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        pieza.posicionar(tablero, new Posicion(0, 0));
        otraPieza.posicionar(tablero, new Posicion(1, 1));
        // Act - Assert
        assertThrows(AtaqueAAliadoException.class,
                () -> {
                    pieza.usarHabilidadEn(otraPieza);
                });
    }


}
