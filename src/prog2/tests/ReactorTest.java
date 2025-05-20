package prog2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.model.components.Reactor;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class ReactorTest {

    private Reactor reactor;
    private PaginaIncidencies paginaIncidencies;

    @BeforeEach
    public void setUp() {
        reactor = new Reactor();
        paginaIncidencies = new PaginaIncidencies(1);
    }

    @Test
    public void testInicialActivat() {
        assertTrue(reactor.getActivat());
    }

    @Test
    public void testDesactivaIActiva() throws CentralUBException {
        reactor.desactiva();
        assertFalse(reactor.getActivat());

        reactor.activa();
        assertTrue(reactor.getActivat());
    }

    @Test
    public void testCalculaOutputActivat() {
        reactor.setTemperaturaActual(200);
        float output = reactor.calculaOutput(50);
        assertEquals(200 + 500, output);
    }

    @Test
    public void testCalculaOutputDesactivat() {
        reactor.setTemperaturaActual(300);
        reactor.desactiva();
        float output = reactor.calculaOutput(70);
        assertEquals(300, output);
    }

    @Test
    public void testCostOperatiu() {
        assertEquals(35, reactor.getCostOperatiu());
        reactor.desactiva();
        assertEquals(0, reactor.getCostOperatiu());
    }

    @Test
    public void testRevisaSupera1000() throws CentralUBException {
        reactor.setTemperaturaActual(1200);
        reactor.revisa(paginaIncidencies);
        assertFalse(reactor.getActivat());
        assertTrue(reactor.getForaDeServei());
    }
    // hauria de desactivarse i quedarse fora de servei

    @Test
    public void testRevisaRecuperaSiBaixaTemperatura() throws CentralUBException {
        reactor.setTemperaturaActual(1200);
        reactor.revisa(paginaIncidencies);

        reactor.setTemperaturaActual(500);
        reactor.revisa(paginaIncidencies);

        assertTrue(reactor.getActivat());
    }
}
