package fiuba.algo3.algochess.testentregas;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.*;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SegundaEntregaTest {
    @Test
    public void test13ElBatallonSeMueveCorrectamente() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
        Posicion posFinal1 = new Posicion(1,2);
        Posicion posFinal2 = new Posicion(1,3);
        Posicion posFinal3 = new Posicion(1,4);
        Direccion direccion = Direccion.derecha();
        // Act - Posiciono y muevo
        tablero.posicionar(new Posicion(0, 2), soldado1);
        tablero.posicionar(new Posicion(0, 3), soldado2);
        tablero.posicionar(new Posicion(0, 4), soldado3);

        tablero.mover(soldado3, direccion);
        // Assert
        assertEquals(soldado1.getPosicion(), posFinal1);
        assertEquals(soldado2.getPosicion(), posFinal2);
        assertEquals(soldado3.getPosicion(), posFinal3);
    }

    @Test
    public void test14ElBatallonSeMueveSoloSeMuevenDosSoldadosDebidoAUnObstaculo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
        Curandero curandero = new Curandero();
        Posicion posFinal1 = new Posicion(2,2);
        Posicion posFinal2 = new Posicion(1,3);
        Posicion posFinal3 = new Posicion(2,4);
        Direccion direccion = Direccion.derecha();

        // Act - Posiciono y muevo
        tablero.posicionar(new Posicion(1, 2), soldado1);
        tablero.posicionar(new Posicion(1, 3), soldado2);
        tablero.posicionar(new Posicion(1, 4), soldado3);
        tablero.posicionar(new Posicion(2, 3), curandero);

        tablero.mover(soldado2, direccion);
        // Assert
        assertEquals(soldado1.getPosicion(),posFinal1);
        assertEquals(soldado2.getPosicion(),posFinal2);
        assertEquals(soldado3.getPosicion(),posFinal3);
    }

    @Test
    public void test15ElBatallonSeDisuelveCorrectamente() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();

        tablero.posicionar(new Posicion(1, 2), soldado1);
        tablero.posicionar(new Posicion(1, 3), soldado2);
        tablero.posicionar(new Posicion(1, 4), soldado3);

        Curandero curandero = new Curandero();
        tablero.posicionar(new Posicion(2, 3), curandero);

        Posicion posFinal1 = new Posicion(3, 2);
        Posicion posFinal2 = new Posicion(1, 3);
        Posicion posFinal3 = new Posicion(2, 4);

        Direccion direccion = Direccion.derecha();


        tablero.mover(soldado1, direccion);

        // Act
        tablero.mover(soldado1, direccion);

        // Assert
        assertEquals(soldado1.getPosicion(), posFinal1);
        assertEquals(soldado2.getPosicion(), posFinal2);
        assertEquals(soldado3.getPosicion(), posFinal3);
    }

    @Test
    public void test16Habiendo4SoldadosContiguosSeMuevenUnicamente3() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado4 = new SoldadoDeInfanteria();
        Posicion posFinal1 = new Posicion(2,2);
        Posicion posFinal2 = new Posicion(2,3);
        Posicion posFinal3 = new Posicion(2,4);
        Posicion posFinal4 = new Posicion(1,5);
        Direccion direccion = Direccion.derecha();
        // Act - Posiciono y muevo
        tablero.posicionar(new Posicion(1, 2), soldado1);
        tablero.posicionar(new Posicion(1, 3), soldado2);
        tablero.posicionar(new Posicion(1, 4), soldado3);
        tablero.posicionar(new Posicion(1, 5), soldado4);

        tablero.mover(soldado2,direccion);
        // Assert
        assertEquals(soldado1.getPosicion(), posFinal1);
        assertEquals(soldado2.getPosicion(), posFinal2);
        assertEquals(soldado3.getPosicion(), posFinal3);
        assertEquals(soldado4.getPosicion(), posFinal4);
    }

    @Test
    public void test17UnJineteSinAliadosYConEnemigoEnDistanciaCortaAtacaConSuEspadaAlEnemigo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        tablero.posicionar(new Posicion(9, 0), jinete);
        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(10, 0), soldadoEnemigo);

        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigo);
        // Assert
        assertEquals(95, soldadoEnemigo.getVida());
    }

    @Test
    public void test18UnJineteSinAliadosEnDistanciaCortaYUnEnemigoEnDistanciaCortaYOtroEnemigoEnDistanciaMediaJineteTrataDeAtacarADistanciaMediaYNoPuede() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        SoldadoDeInfanteria otroSoldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        otroSoldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        tablero.posicionar(new Posicion(0, 9), jinete);
        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(10, 0), soldadoEnemigo);
        tablero.posicionar(new Posicion(12, 0), otroSoldadoEnemigo);
        // Act - Assert para exception
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    jinete.usarHabilidadEn(tablero, otroSoldadoEnemigo);
                });
        // Assert
        assertEquals(100, otroSoldadoEnemigo.getVida());
    }
}