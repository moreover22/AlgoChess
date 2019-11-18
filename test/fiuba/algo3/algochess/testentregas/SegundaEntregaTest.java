package fiuba.algo3.algochess.testentregas;

import fiuba.algo3.algochess.Posicion;
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
}
