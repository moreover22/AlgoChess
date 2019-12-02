package fiuba.algo3.algochess;


import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.model.pieza.habilidad.*;
import fiuba.algo3.algochess.model.pieza.habilidad.armas.*;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmaTest {

    @Test
    public void test00EspadaPesadaHaceElDanioCorrespondiente() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionAEnemigoException {
        //Arrange
        Arma espadaPesada = new EspadaPesada();
        Posicion posOrigen = new Posicion(0, 0);
        Tablero tablero = new Tablero();
        Pieza objetivo = new SoldadoDeInfanteria();
        Habilidad ataque = new Ataque(espadaPesada);
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(2, 0), objetivo);
        //Act
        espadaPesada.atacarA(objetivo, posOrigen,ataque);
        //Assert
        assertEquals(objetivo.getVida(),90);
    }
/*
    @Test
    public void test01EspadaPesadaNoMataAUnaPiezaQueEstaACortoAlcanceQueTiene11DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaPesada = new EspadaPesada();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(2, 0), objetivo);
        objetivo.recibirDanio(89);
        //Act
        espadaPesada.atacarA(objetivo, posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }
*/
    @Test
    public void test02EspadaPesadaAtacarAPiezaQueNoEstaACortoAlcanceLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaPesada = new EspadaPesada();
        Habilidad ataque = new Ataque(espadaPesada);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();

        tablero.posicionar(new Posicion(7, 8), objetivo);

        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    espadaPesada.atacarA(objetivo, posOrigen,ataque);
                });
    }

    @Test
    public void test03EspadaLivianaHaceElDanioCorrespondiente() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionAEnemigoException {
        //Arrange
        Arma espadaLiviana = new EspadaLiviana();
        Habilidad ataque = new Ataque(espadaLiviana);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(2, 0), objetivo);
        //Act
        espadaLiviana.atacarA(objetivo, posOrigen,ataque);
        //Assert
        assertEquals(objetivo.getVida(),95);
    }

/*
    @Test
    public void test04EspadaLivianaNoMataAUnaPiezaQueEstaACortoAlcanceQueTiene6DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaLiviana = new EspadaLiviana();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        tablero.posicionar(new Posicion(2, 0), objetivo);
        objetivo.recibirDanio(94);
        //Act
        espadaLiviana.atacarA(objetivo, posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }
*/
    @Test
    public void test05EspadaLivianaAtacarAPiezaQueNoEstaACortoAlcanceLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaLiviana = new EspadaLiviana();
        Habilidad ataque = new Ataque(espadaLiviana);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(7, 8), objetivo);

        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    espadaLiviana.atacarA(objetivo, posOrigen,ataque);
                });
    }

    @Test
    public void test06ArcoHaceElDanioCorrespondiente() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionAEnemigoException {
        //Arrange
        Arma arco = new ArcoYFlecha();
        Habilidad ataque = new Ataque(arco);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();


        tablero.posicionar(new Posicion(4, 4), objetivo);

        //Act
        arco.atacarA(objetivo, posOrigen,ataque);
        //Assert
        assertEquals(objetivo.getVida(),85);
    }

    /*
    @Test
    public void test07ArcoNoMataAUnaPiezaQueEstaAAlcanceMedioQueTiene16DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma arco = new ArcoYFlecha();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(4, 4), objetivo);
        objetivo.recibirDanio(84);
        //Act
        arco.atacarA(objetivo,posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }
*/

    @Test
    public void test08ArcoAtacarAPiezaQueNoEstaAAlcanceMedioLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma arco = new ArcoYFlecha();
        Habilidad ataque = new Ataque(arco);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(1, 1), objetivo);
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    arco.atacarA(objetivo, posOrigen,ataque);
                });
    }

    @Test
    public void test09ProyectilHAceElDanioCorrespondiente() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException, CuracionAEnemigoException {
        //Arrange
        Arma proyectil= new Proyectil();
        Habilidad ataque = new Ataque(proyectil);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        tablero.cambiarAlianza();

        tablero.posicionar(new Posicion(10, 10), objetivo);
        //Act
        proyectil.atacarA(objetivo, posOrigen,ataque);
        //Assert
        assertEquals(objetivo.getVida(),80);
    }
/*
    @Test
    public void test10ProyectilNoMataAUnaPiezaQueEstaAAlcanceLejanoQueTiene21DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma proyectil = new Proyectil();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        tablero.cambiarAlianza();

        tablero.posicionar(new Posicion(10, 10), objetivo);
        objetivo.recibirDanio(79);
        //Act
        proyectil.atacarA(objetivo, posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }
*/
    @Test
    public void test11ProyectilAtacarAPiezaQueNoEstaAAlcanceLejanoLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma proyectil = new Proyectil();
        Habilidad ataque = new Ataque(proyectil);
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();

        tablero.posicionar(new Posicion(1, 1), objetivo);

        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    proyectil.atacarA(objetivo, posOrigen,ataque);
                });
    }
}