package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PiezaTest {
    @Test
    public void testPiezaRecienCreadaEstaViva(){
        //Arrenge-Act
        Pieza pieza = new SoldadoDeInfanteria();
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaRecibeDanioPeroNoMuere(){
        //Arrenge
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirDanio(50);
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaRecibeDanioYMuere(){
        //Arrenge
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirDanio(100);
        //Assert
        assertFalse(pieza.estaViva());
    }

    @Test
    public void testPiezaRecibeDanioIgualASuVidaYMuere(){
        //Arrenge
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirDanio(100);
        //Assert
        assertFalse(pieza.estaViva());
    }

    @Test
    public void testPiezaRecibeDanioMenorASuVida(){
        //Arrenge
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirDanio(80);
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaCon100DeVidaRecibe50DanioSeCura20DeVidaYVueveARecibir50DeDanioYNoMuere(){
        //Arrenge
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirDanio(50);
        pieza.recibirCuracion(20);
        pieza.recibirDanio(50);
        //Assert
        assertTrue(pieza.estaViva());
    }

    @Test
    public void testPiezaCon100DeVidaSeCura50DeVidaYMuereAlRecibir120DeDanio(){
        //Arrenge
        Pieza pieza = new SoldadoDeInfanteria();
        //Act
        pieza.recibirCuracion(50);
        pieza.recibirDanio(120);
        //Assert
        assertFalse(pieza.estaViva());
    }

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadYMataAEnemigoCon10DeVida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(1,1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(90);
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertFalse(objetivo.estaViva());

    }

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadYNoMataAEnemigoCon11Vida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(1,1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(89);
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertTrue(objetivo.estaViva());

    }
    @Test
    public void testJineteUsaHabilidadYMataAEnemigoCon5Vida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new Jinete();
        Pieza objetivo = new Jinete();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(1,1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(95);
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertFalse(objetivo.estaViva());

    }

    @Test
    public void testJineteUsaHabilidadYNoMataAEnemigoCon6Vida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new Jinete();
        Pieza objetivo = new Jinete();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(1,1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(94);
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertTrue(objetivo.estaViva());

    }

    @Test
    public void testCatapultaUsaHabilidadYMataAEnemigoCon20Vida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new Catapulta();
        Pieza objetivo = new Catapulta();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(20,40);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(30);
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertFalse(objetivo.estaViva());

    }

    @Test
    public void testCatapultaUsaHabilidadYNoMataAEnemigoCon21Vida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new Catapulta();
        Pieza objetivo = new Catapulta();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(20,40);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(29);
        pieza.usarHabilidadEn(objetivo);
        //Assert
        assertTrue(objetivo.estaViva());

    }

    @Test
    public void testCuranderoUsaHabilidadYSalvaAALiadoCon1DeVida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(1,1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(74);
        pieza.usarHabilidadEn(objetivo);
        objetivo.recibirDanio(15);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void testCuranderoUsaHabilidadYNoSalvaAALiadoCon1DeVida()throws HabilidadFueraDeAlcanceException{
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Posicion ubicacionPieza = new Posicion(0,0);
        Posicion ubicacionObjetivo = new Posicion(1,1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act
        objetivo.recibirDanio(74);
        pieza.usarHabilidadEn(objetivo);
        objetivo.recibirDanio(16);
        //Assert
        assertFalse(objetivo.estaViva());
    }
}
