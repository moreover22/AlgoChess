package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.alcance.Alcance;
import fiuba.algo3.algochess.pieza.alcance.AlcanceCercano;
import fiuba.algo3.algochess.pieza.alcance.AlcanceLejano;
import fiuba.algo3.algochess.pieza.alcance.AlcanceMedio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AlcanceTest {

    @Test
    public void test00AlcanceCercanoReconoceQueLlegaAUnaDistanciaDeDos(){
        //Arrange
        Alcance alcance = new AlcanceCercano();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(1,4);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void test01AlcanceCercanoReconoceQueNoLlegaAUnaDistanciaDe4(){
        //Arrange
        Alcance alcance = new AlcanceCercano();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(1,6);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void test02AlcanceMedioReconoceQueLlegaAUnaDistanciaDe4(){
        //Arrange
        Alcance alcance = new AlcanceMedio();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(5,2);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void test03AlcanceMedioReconoceQueNoLlegaAUnaDistanciaDe1(){
        //Arrange
        Alcance alcance = new AlcanceMedio();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(2,2);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void test04AlcanceLejanoReconoceQueLlegaAUnaDistanciaDe8(){
        //Arrange
        Alcance alcance = new AlcanceLejano();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(9,2);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertTrue(resultado);
    }

    @Test
    public void test05AlcanceLejanoReconoceQueNoLlegaAUnaDistanciaDe3(){
        //Arrange
        Alcance alcance = new AlcanceCercano();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(4,2);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertFalse(resultado);
    }

    @Test
    public void test06AlcanceInmediatoReconoceQueLlegaAUnaDistanciaDe1() {
        //Arrange
        Alcance alcance = new AlcanceCercano();
        Posicion origen = new Posicion(1, 2);
        Posicion destino = new Posicion(2, 2);
        //Act
        boolean resultado = alcance.llegoA(origen, destino);
        //Assert
        assertTrue(resultado);

    }

    @Test
    public void test06AlcanceLejanoReconoceQueNoLlegaAUnaDistanciaMayorA1(){
        //Arrange
        Alcance alcance = new AlcanceCercano();
        Posicion origen = new Posicion(1,2);
        Posicion destino = new Posicion(4,2);
        //Act
        boolean resultado = alcance.llegoA(origen,destino);
        //Assert
        assertFalse(resultado);
    }

}






























