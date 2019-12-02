package fiuba.algo3.algochess;

import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Jinete;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.model.pieza.habilidad.CuracionACatapultaException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadConObjetivoInvalidoException;
import fiuba.algo3.algochess.model.pieza.habilidad.HabilidadFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;
import fiuba.algo3.algochess.model.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiezaJineteTest {
    @Test
    public void testJineteConSoldadoDeInfanteriaAliadoCercanoPuedeAtacarADistanciaMediaConArcoYFlechaAEnemigo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, CuracionACatapultaException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza jinete = new Jinete();
        Pieza soldado = new SoldadoDeInfanteria();
        Pieza soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        tablero.posicionar(new Posicion(9, 0), jinete);
        tablero.posicionar(new Posicion(9, 1), soldado);

        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(12, 0), soldadoEnemigo);

        // Act
        System.out.println(jinete.parsear());
        jinete.usarHabilidadEn(tablero, soldadoEnemigo);

        // Assert
        assertEquals(85, soldadoEnemigo.getVida());
    }
    @Test
    public void testJineteConSoldadoDeInfanteriaAliadoCercanoNoPuedeAtacarADistanciaCercana() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza jinete = new Jinete();
        Pieza soldado = new SoldadoDeInfanteria();
        Pieza soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        tablero.posicionar(new Posicion(9, 0), jinete);
        tablero.posicionar(new Posicion(9, 1), soldado);
        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(10, 0), soldadoEnemigo);
        // Act - Assert exception
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    jinete.usarHabilidadEn(tablero, soldadoEnemigo);
                });
        // Assert
        assertEquals(100, soldadoEnemigo.getVida());
    }

    @Test
    public void testJineteSinEnemigoCercanoPuedeAtacarADistanciaMediaConArcoYFlechaAEnemigo() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, CuracionACatapultaException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza jinete = new Jinete();
        Pieza soldadoEnemigo = new SoldadoDeInfanteria();
        soldadoEnemigo.cambiarAlianza();
        // Arrange - Posiciono
        tablero.posicionar(new Posicion(9, 0), jinete);
        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(12, 0), soldadoEnemigo);
        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigo);
        // Assert
        assertEquals(85, soldadoEnemigo.getVida());
    }
    @Test
    public void testJineteSinAliadosCercanosYConEnemigosCercanoPuedeAtacarADistanciaCercanaConEspada() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, CuracionACatapultaException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza jinete = new Jinete();
        Pieza soldadoEnemigo = new SoldadoDeInfanteria();
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
    public void testJineteMataAEnemigoCercanoArmaCambiaAArcoYFlechaYPuedeAtacarAEnemigoADistanciaLejana() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException, HabilidadConObjetivoInvalidoException, HabilidadFueraDeAlcanceException, CuracionACatapultaException {
        // Arrange
        Tablero tablero = new Tablero();
        Pieza jinete = new Jinete();
        Pieza soldadoEnemigoCercano = new SoldadoDeInfanteria();
        Pieza soldadoEnemigoMediano = new SoldadoDeInfanteria();
        soldadoEnemigoCercano.cambiarAlianza();
        soldadoEnemigoMediano.cambiarAlianza();
        // Arrange - Posiciono
        tablero.posicionar(new Posicion(9, 0), jinete);
        tablero.cambiarAlianza();
        tablero.posicionar(new Posicion(10, 0), soldadoEnemigoCercano);
        tablero.posicionar(new Posicion(12, 0), soldadoEnemigoMediano);
        while(soldadoEnemigoCercano.estaViva()) {
            jinete.usarHabilidadEn(tablero, soldadoEnemigoCercano);
        }
        // Act
        jinete.usarHabilidadEn(tablero, soldadoEnemigoMediano);
        // Assert
        assertEquals(85, soldadoEnemigoMediano.getVida());
    }
}