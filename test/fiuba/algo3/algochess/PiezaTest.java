package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.*;
import fiuba.algo3.algochess.pieza.movimiento.*;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.casillero.CasilleroException;
import fiuba.algo3.algochess.tablero.casillero.ColocarEnCasilleroOcupadoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


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
    @Test
    public void test01PiezaNoPuedeMoverseAUnCasilleroOcupado() throws CasilleroException, FueraDelTableroException {

        AlgoChess chess = new AlgoChess();
        Pieza pieza1 = new SoldadoDeInfanteria();
        chess.colocar(pieza1,new Posicion(0,0));
        Pieza pieza2 = new SoldadoDeInfanteria();
        chess.colocar(pieza2, new Posicion(0,1));

        assertThrows(ColocarEnCasilleroOcupadoException.class,
                ()->{
                    chess.mover(pieza2,new DireccionArriba());
                });
    }

    @Test
    public void test02PiezaPuedeMoverseAUnCasilleroDeDistancia() throws CasilleroException, FueraDelTableroException, MovimientoFueraDeAlcanceException {

        AlgoChess chess = new AlgoChess();
        Pieza pieza = new SoldadoDeInfanteria();

        chess.colocar(pieza,new Posicion(0,0));

        chess.mover(pieza,new DireccionAbajo());

        assertEquals(pieza.getPosicion().getX(), 0);
        assertEquals(pieza.getPosicion().getY(), 1);

        chess.mover(pieza,new DireccionDerecha());

        assertEquals(pieza.getPosicion().getX(), 1);
        assertEquals(pieza.getPosicion().getY(), 1);

        chess.mover(pieza,new DireccionIzquierda());

        assertEquals(pieza.getPosicion().getX(), 0);
        assertEquals(pieza.getPosicion().getY(), 1);

        chess.mover(pieza,new DireccionArriba());

        assertEquals(pieza.getPosicion().getX(), 0);
        assertEquals(pieza.getPosicion().getY(), 0);
    }

    /*@Test
    public void test03PiezaNoSePuedeMoverAUnCasilleroLibreQueNoSeaAdyacente(){
        AlgoChess chess = new AlgoChess();
        Pieza pieza = new SoldadoDeInfanteria();


        chess.colocar(pieza,new Posicion(0,0));

    }*/
    @Test
    public void test03SoldadoDeInfanteriaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException {

        SoldadoDeInfanteria soldado =  new SoldadoDeInfanteria();

        soldado.setPosicion(new Posicion(0,0));

        Jinete enemigo=new Jinete();

        enemigo.setPosicion(new Posicion(1,0));

        enemigo.recibirDanio(90);

        soldado.usarHabilidadEn(enemigo);

        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();

        enemigo.setPosicion(new Posicion(1,0));

        enemigo.recibirDanio(89);

        soldado.usarHabilidadEn(enemigo);

        assertTrue(enemigo.estaViva());
    }

    @Test
    public void test04JineteAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException {
        SoldadoDeInfanteria enemigo =  new SoldadoDeInfanteria();

        enemigo.setPosicion(new Posicion(0,0));

        Jinete jinete =new Jinete();

        jinete.setPosicion(new Posicion(1,0));

        enemigo.recibirDanio(95);

        jinete.usarHabilidadEn(enemigo);

        assertFalse(enemigo.estaViva());

        enemigo = new SoldadoDeInfanteria();

        enemigo.setPosicion(new Posicion(0,0));

        enemigo.recibirDanio(94);

        jinete.usarHabilidadEn(enemigo);

        assertTrue(enemigo.estaViva());
    }


    // 5. Una catapulta aliada ataca a una pieza enemiga y se verifica que se resta la vida correspondiente.
    @Test
    public void test05CatapultaAliadoAtacaAUnaPiezaEnemigaSeLeRestaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException {
        Catapulta catapulta =  new Catapulta();
        catapulta.setPosicion(new Posicion(0,0));
        Jinete enemigo =new Jinete();
        enemigo.setPosicion(new Posicion(10,0));

        enemigo.recibirDanio(80);

        catapulta.usarHabilidadEn(enemigo);

        assertFalse(enemigo.estaViva());

        enemigo = new Jinete();
        enemigo.setPosicion(new Posicion(10,0));
        enemigo.recibirDanio(79);

        catapulta.usarHabilidadEn(enemigo);

        assertTrue(enemigo.estaViva());
    }
    @Test
    public void test06CuranderoAliadoCuraAUnaPiezaAliadaSeLeSumaLaVidaCorrespondiente() throws HabilidadFueraDeAlcanceException {
        Curandero curandero =  new Curandero();

        curandero.setPosicion(new Posicion(0,0));

        SoldadoDeInfanteria enemigo =  new SoldadoDeInfanteria();

        enemigo.setPosicion(new Posicion(0,1));

        Jinete aliado =new Jinete();

        aliado.setPosicion(new Posicion(1,0));

        aliado.recibirDanio(95);

        curandero.usarHabilidadEn(aliado);

        enemigo.usarHabilidadEn(aliado);
        enemigo.usarHabilidadEn(aliado);

        assertFalse(aliado.estaViva());

        aliado = new Jinete();

        aliado.setPosicion(new Posicion(1,0));

        aliado.recibirDanio(94);

        curandero.usarHabilidadEn(aliado);

        enemigo.usarHabilidadEn(aliado);
        enemigo.usarHabilidadEn(aliado);

        assertTrue(aliado.estaViva());
    }

    @Test
    public void testSoldadoDeInfateriaUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() {
        //Arrange
        Pieza pieza = new SoldadoDeInfanteria();
        Pieza objetivo = new SoldadoDeInfanteria();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo );
                });
    }

    @Test
    public void testjineteUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() {
        //Arrange
        Pieza pieza = new Jinete();
        Pieza objetivo = new Jinete();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo );
                });
    }

    @Test
    public void testCatapultaUsaHabilidadAEnemigoQueNoEstaALargaDistanciayLanzaHabilidadFueraDeAlcanceException() {
        //Arrange
        Pieza pieza = new Catapulta();
        Pieza objetivo = new Catapulta();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(1, 1);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo );
                });
    }

    @Test
    public void testCuranderoUsaHabilidadAEnemigoQueNoEstaACortaDistanciayLanzaHabilidadFueraDeAlcanceException() {
        //Arrange
        Pieza pieza = new Curandero();
        Pieza objetivo = new Curandero();
        Posicion ubicacionPieza = new Posicion(0, 0);
        Posicion ubicacionObjetivo = new Posicion(12, 7);
        pieza.setPosicion(ubicacionPieza);
        objetivo.setPosicion(ubicacionObjetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    pieza.usarHabilidadEn(objetivo);
                });
    }
}
