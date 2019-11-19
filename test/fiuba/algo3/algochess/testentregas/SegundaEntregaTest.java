package fiuba.algo3.algochess.testentregas;

import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.pieza.*;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SegundaEntregaTest {

    @Test
    public void test16UnJineteSinAliadosYConEnemigoEnDistanciaCortaAtacaConSuEspadaAlEnemigo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        jinete.posicionar(tablero, new Posicion(0, 9));
        tablero.cambiarAlianza();
        soldadoEnemigo.posicionar(tablero, new Posicion(0, 10));
        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigo);
        // Assert
        assertEquals(95, soldadoEnemigo.getVida());
    }

    @Test
    public void test17UnJineteSinAliadosEnDistanciaCortaYUnEnemigoEnDistanciaCortaYOtroEnemigoEnDistanciaMediaJineteTrataDeAtacarADistanciaMediaYNoPuede() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        SoldadoDeInfanteria otroSoldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        otroSoldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        jinete.posicionar(tablero, new Posicion(0, 9));
        tablero.cambiarAlianza();
        soldadoEnemigo.posicionar(tablero, new Posicion(0, 10));
        otroSoldadoEnemigo.posicionar(tablero, new Posicion(0, 12));
        // Act - Assert para exception
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    jinete.usarHabilidadEn(tablero, otroSoldadoEnemigo);
                });
        // Assert
        assertEquals(100, otroSoldadoEnemigo.getVida());
    }
    @Test
    public void test13ElBatallonSeMueveCorrectamente() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
        List<Pieza> listPiezas = new ArrayList<Pieza>();
        listPiezas.add(soldado1);
        listPiezas.add(soldado2);
        listPiezas.add(soldado3);
        Batallon batallon = new Batallon(listPiezas);
        Posicion posFinal1 = new Posicion(1,2);
        Posicion posFinal2 = new Posicion(1,3);
        Posicion posFinal3 = new Posicion(1,4);
        Direccion direccion = Direccion.derecha();
        // Act - Posiciono y muevo
        soldado1.posicionar(tablero, new Posicion(0, 2));
        soldado2.posicionar(tablero, new Posicion(0, 3));
        soldado3.posicionar(tablero, new Posicion(0, 4));

        batallon.mover(tablero,direccion);
        // Assert
        assertEquals(soldado1.getPosicion(),posFinal1);
        assertEquals(soldado2.getPosicion(),posFinal2);
        assertEquals(soldado3.getPosicion(),posFinal3);
        }
    @Test
    public void test14ElBatallonSeMueveSoloSeMuevenDosSoldadosDebidoAUnObstaculo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, MovimientoFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
        Curandero curandero = new Curandero();
        List<Pieza> listPiezas = new ArrayList<Pieza>();
        listPiezas.add(soldado1);
        listPiezas.add(soldado2);
        listPiezas.add(soldado3);
        Batallon batallon = new Batallon(listPiezas);
        Posicion posFinal1 = new Posicion(1,2);
        Posicion posFinal2 = new Posicion(0,3);
        Posicion posFinal3 = new Posicion(1,4);
        Direccion direccion = Direccion.derecha();

        // Act - Posiciono y muevo
        soldado1.posicionar(tablero, new Posicion(0, 2));
        soldado2.posicionar(tablero, new Posicion(0, 3));
        soldado3.posicionar(tablero, new Posicion(0, 4));
        curandero.posicionar(tablero,new Posicion(1, 3));

        batallon.mover(tablero,direccion);
        // Assert
        assertEquals(soldado1.getPosicion(),posFinal1);
        assertEquals(soldado2.getPosicion(),posFinal2);
        assertEquals(soldado3.getPosicion(),posFinal3);
    }






}
