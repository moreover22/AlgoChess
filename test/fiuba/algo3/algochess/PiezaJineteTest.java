package fiuba.algo3.algochess;

import fiuba.algo3.algochess.pieza.Jinete;
import fiuba.algo3.algochess.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiezaJineteTest {
    @Test
    public void testJineteConSoldadoDeInfanteriaAliadoCercanoPuedeAtacarADistanciaMediaConArcoYFlechaAEnemigo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldado = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        jinete.posicionar(tablero, new Posicion(0, 9));
        soldado.posicionar(tablero, new Posicion(1, 9));
        tablero.cambiarAlianza();
        soldadoEnemigo.posicionar(tablero, new Posicion(0, 12));
        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigo);
        // Assert
        assertEquals(85, soldadoEnemigo.getVida());
    }
    @Test
    public void testJineteConSoldadoDeInfanteriaAliadoCercanoNoPuedeAtacarADistanciaCercana() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldado = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        jinete.posicionar(tablero, new Posicion(0, 9));
        soldado.posicionar(tablero, new Posicion(1, 9));
        tablero.cambiarAlianza();
        soldadoEnemigo.posicionar(tablero, new Posicion(0, 10));
        // Act - Assert exception
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    jinete.usarHabilidadEn(tablero, soldadoEnemigo);
                });
        // Assert
        assertEquals(100, soldadoEnemigo.getVida());
    }

    @Test
    public void testJineteSinEnemigoCercanoPuedeAtacarADistanciaMediaConArcoYFlechaAEnemigo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        jinete.posicionar(tablero, new Posicion(0, 9));
        tablero.cambiarAlianza();
        soldadoEnemigo.posicionar(tablero, new Posicion(0, 12));
        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigo);
        // Assert
        assertEquals(85, soldadoEnemigo.getVida());
    }
    @Test
    public void testJineteSinAliadosCercanosYConEnemigosCercanoPuedeAtacarADistanciaCercanaConEspada() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
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
    public void testJineteMataAEnemigoCercanoArmaCambiaAArcoYFlechaYPuedeAtacarAEnemigoADistanciaLejana() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Jinete jinete = new Jinete();
        SoldadoDeInfanteria soldadoEnemigoCercano = new SoldadoDeInfanteria();
        SoldadoDeInfanteria soldadoEnemigoMediano = new SoldadoDeInfanteria();
        soldadoEnemigoCercano.cambiarAlianza();
        soldadoEnemigoMediano.cambiarAlianza();
        // Arrange - Posiciono
        jinete.posicionar(tablero, new Posicion(0, 9));
        tablero.cambiarAlianza();
        soldadoEnemigoCercano.posicionar(tablero, new Posicion(0, 10));
        soldadoEnemigoMediano.posicionar(tablero, new Posicion(0, 12));
        while(soldadoEnemigoCercano.estaViva()) {
            jinete.usarHabilidadEn(tablero, soldadoEnemigoCercano);
        }
        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigoMediano);
        // Assert
        assertEquals(85, soldadoEnemigoMediano.getVida());
    }
}
